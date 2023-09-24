package br.com.confchat.mobile.domain

import br.com.confchat.mobile.data.network.repository.AuthApiRepository
import br.com.confchat.mobile.data.network.repository.UserApiRepository
import br.com.confchat.mobile.di.StanceModule
import br.com.confchat.mobile.veiwmodel.model.Register
import org.junit.Test
import java.util.Calendar
import java.util.Date

class AuthDomainRepositoryTest {
    private lateinit var domain: AuthDomainRepository

    init {
        StanceModule.urlTest = "https://api.confchat.com.br/"
        val api = StanceModule.getRetrofit(null)
        domain = AuthDomainRepository(
            AuthApiRepository(api),
            UserApiRepository(api),
            null
        )
    }

    @Test
    fun registerTest() {
        var response = domain.register(
            Register(
                birthDay= "2002-01-29",
                email="teste@teste.com",
                login="teste.dev",
                name="teste teste teste",
                password="TEsteP@ssword1"
            )
        )
        println(response)
        assert(true)
    }
    @Test
    fun checkVerificationCodeTest(){
        var response = domain.checkVerificationCode("542802")
        assert(!response)
    }
    @Test
    fun checkDateInitIsAffterNowTestError(){
        val dateNow = Date()
        var response = domain.checkDateUpdateToken(dateNow.time)
        assert(response)
    }
    @Test
    fun checkDateInitIsAffterNowTestSuccess(){
        val dateNow = Date()
        val calendar = Calendar.getInstance()
        calendar.time = dateNow
        calendar.add(Calendar.DAY_OF_MONTH,-26)
        var response = domain.checkDateUpdateToken(calendar.time.time)
        assert(!response)
    }
}