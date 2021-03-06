package com.example.androidapp.model

import com.google.firebase.database.PropertyName

data class ProjectModel (
    @PropertyName("ProjectName")
    var ProjectName: String = "",
    @PropertyName("email")
    var email: String = "",
    @PropertyName("about")
    var about: String = "",
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