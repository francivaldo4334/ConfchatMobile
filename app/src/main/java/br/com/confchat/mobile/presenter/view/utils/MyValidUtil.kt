package br.com.confchat.mobile.presenter.view.utils

import android.annotation.SuppressLint
import java.text.DateFormat
import java.time.LocalDate
import java.util.regex.Pattern

class MyValidUtil {
    fun validLogin(login: String?): Boolean {
        val pattern: Pattern = Pattern.compile("^[0-9a-z.]{8,}$")
        return if (login == null) false else pattern.matcher(login).matches()
    }

    fun validPassword(password: String?): Boolean {
        val pattern: Pattern =
            Pattern.compile("^(?=.*[0-9])(?=.*[!@#$%^&*])[0-9a-zA-Z!@#$%^&* ]{15,}$")
        return if (password == null) false else pattern.matcher(password).matches()
    }

    @SuppressLint("NewApi")
    fun validBirthDay(birthDay: LocalDate): Boolean {
        val minimumAge = LocalDate.now().minusYears(18)
        return if (birthDay.isBefore(minimumAge)) {
            true
        } else false
    }

    fun validName(name: String?): Boolean {
        val pattern: Pattern = Pattern.compile("^[a-zA-Z]+\\s[a-zA-Z\\s]+$")
        return if (name == null) false else pattern.matcher(name).matches()
    }

    fun validEmail(email: String?): Boolean {
        val pattern: Pattern = Pattern.compile("^[0-9a-zA-Z.]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9.]+$")
        return if (email == null) false else pattern.matcher(email).matches()
    }

    fun validDate(birthDay: String): Boolean{
        val pattern: Pattern = Pattern.compile("^[0-9]{2}[0-9]{2}[0-9]{4}$")
        return if (birthDay == null) false else pattern.matcher(birthDay).matches()
    }
}