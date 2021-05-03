package com.example.androidapp.model

import com.google.firebase.database.PropertyName

//класс для хранения основной информации о пользователе
data class UserModel (
    @PropertyName("id")
    var id: String = "",
    @PropertyName("email")
    var email: String = "",
    @PropertyName("name")
    var name: String = "",
    @PropertyName("surname")
    var surname: String = "",
    @PropertyName("about")
    var about: String = ""
)