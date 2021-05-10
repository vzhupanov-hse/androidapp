package com.example.androidapp.model

import com.google.firebase.database.PropertyName
import com.google.gson.annotations.SerializedName

//класс для хранения основной информации о пользователе
data class UserModel(
    @PropertyName("id")
    var id: String = "",
    @PropertyName("email")
    var email: String = "",
    @PropertyName("name")
    var name: String = "",
    @PropertyName("surname")
    var surname: String = "",
    @PropertyName("add_info")
    var add_info: String = "",
    @PropertyName("about")
    var about: String = "",
    @SerializedName("interes")
    var interes: UserInteres = UserInteres()
) {
    data class UserInteres(
        @PropertyName("Android")
        var android: String = "",
        @PropertyName("CPlus")
        var cplus: String = "",
        @PropertyName("CSS")
        var css: String = "",
        @PropertyName("CSharp")
        var csharp: String = "",
        @PropertyName("HTML")
        var html: String = "",
        @PropertyName("Web")
        var web: String = "",
        @PropertyName("Python")
        var python: String = "",
        @PropertyName("JavaScript")
        var javascript: String = "",
        @PropertyName("Java")
        var java: String = "",
        @PropertyName("Kotlin")
        var kotlin: String = ""
    )
}