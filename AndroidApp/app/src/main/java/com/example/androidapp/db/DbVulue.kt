package com.example.androidapp.db

import com.example.androidapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference

//поля для работы с базой данных
lateinit var AUTH: FirebaseAuth
lateinit var CURRENT_UID: String
lateinit var REF_DATABASE_ROOT: DatabaseReference
lateinit var REF_STORAGE_ROOT: StorageReference
lateinit var USER: UserModel

//константные имена нодов базы данных
const val NODE_USERS = "users"
const val CHILD_ID = "id"
const val CHILD_EMAIL = "email"
const val CHILD_NAME = "name"
const val CHILD_SURNAME = "surname"
const val CHILD_ABOUT = "about"
const val NODE_INTERES = "interes"
const val CHILD_ANDROID = "Android"
const val CHILD_CSS = "CSS"
const val CHILD_CPLUS = "CPlus"
const val CHILD_CSHARP = "CSharp"
const val CHILD_HTML = "HTML"
const val CHILD_WEB = "Web"
const val CHILD_PYTHON = "Python"
const val CHILD_JAVASCRIPT = "JavaScript"
const val CHILD_JAVA = "Java"
const val CHILD_KOTLIN = "Kotlin"

