package br.com.confchat.mobile.domain

import br.com.confchat.mobile.data.network.repository.AuthApiRepository
import br.com.confchat.mobile.data.network.repository.UserApiRepository
import br.com.confchat.mobile.di.StanceModule
import br.com.confchat.mobile.veiwmodel.model.Register
import org.junit.Test

class AuthDomainRepositoryTest {
    private lateinit var domain: AuthDomainRepository

    init {
        val api = StanceModule.getRetrofit()
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
}