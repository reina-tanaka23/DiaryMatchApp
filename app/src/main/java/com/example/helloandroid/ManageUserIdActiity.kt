package com.example.helloandroid

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.helloandroid.databinding.ActivityMainBinding
import com.example.helloandroid.databinding.ActivityManageUserIdActiityBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ManageUserIdActiity : AppCompatActivity() {
    private lateinit var binding: ActivityManageUserIdActiityBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageUserIdActiityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    // SharedPreferenceに自身のユーザーID登録
    fun registerMyUserId(view: View) {

        val myUserIdData: SharedPreferences = getSharedPreferences("MyUserId", Context.MODE_PRIVATE)
        val editor = myUserIdData.edit()
        val id = binding.ownUserIdEdit.text.toString()
        println("userId:$id")

        editor.putString("userId", id)
        editor.commit()

//        val userId = hashMapOf(
//            "id" to id
//        )
//        db.collection("User")
//            .add(userId)
//            .addOnSuccessListener { documentReference ->
//                Log.d("DEBUG", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.d("DEBUG", "Error adding document", e)
//            }
    }

    // 友達のユーザーID登録
    fun registerFriendId(view: View) {

        val friendUserIds: SharedPreferences = getSharedPreferences("FriendUserIds", Context.MODE_PRIVATE)
        val editor = friendUserIds.edit()
        val id = binding.friendUserIdEdit.text.toString()

        editor.putString("friendUserId", id)
        editor.commit()

//        val userId = hashMapOf(
//            "id" to id
//        )
//
//        db.collection("User")
//            .add(userId)
//            .addOnSuccessListener { documentReference ->
//                Log.d("DEBUG", "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.d("DEBUG", "Error adding document", e)
//            }
    }
}