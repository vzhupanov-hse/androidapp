package com.example.androidapp.util

import com.example.androidapp.db.*
import com.example.androidapp.model.UserInteres
import com.example.androidapp.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

//инициализация полей для работы с базой данных
fun initFirebase() {
    AUTH = FirebaseAuth.getInstance()
    CURRENT_UID = AUTH.currentUser?.uid.toString()
    REF_DATABASE_ROOT = FirebaseDatabase.getInstance().reference
    REF_STORAGE_ROOT = FirebaseStorage.getInstance().reference
    USER = UserModel()
}

//авторизация пользователя
inline fun initUser(crossinline function: () -> Unit) {
    REF_DATABASE_ROOT.child(NODE_USERS).child(CURRENT_UID)
        .addListenerForSingleValueEvent(AppValueEventListener {
            USER = it.getValue(UserModel::class.java) ?: UserModel()
            if (USER.email.isEmpty()) {
                USER.email = CURRENT_UID
            }
            function()
        })
    }

//получение информации об интересах пользователя из базы данных
fun DataSnapshot.getUserInteresModel(): UserInteres =
        this.getValue(UserInteres::class.java) ?: UserInteres()