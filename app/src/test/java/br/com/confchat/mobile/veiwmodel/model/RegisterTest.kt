package br.com.confchat.mobile.veiwmodel.model

import br.com.confchat.mobile.domain.model.toDto
import com.google.gson.Gson
import org.junit.Test

class RegisterTest {
    @Test
    fun toDtoTest(){
        val model = Register(
            "20020129",
            "teste@teste.com",
            "login123",
            "User Name",
            "YouP@ss0rdStrong"
        )
        val dto = model.toDto()
        println("""
            ${Gson().toJson(model)},
            ${Gson().toJson(dto)}
        """.trimIndent())
        assert(true)
    }
}