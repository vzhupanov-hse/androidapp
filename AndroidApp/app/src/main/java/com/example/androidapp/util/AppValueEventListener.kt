package com.example.androidapp.util

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class AppValueEventListener (val onSuccess:(DataSnapshot) -> Unit) : ValueEventListener {
    override fun onCancelled(p0: DatabaseError) {}
    override fun onDataChange(p0: DataSnapshot) { onSuccess(p0) }
}