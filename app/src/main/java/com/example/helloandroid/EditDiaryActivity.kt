package com.example.helloandroid

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.helloandroid.databinding.ActivityEditDiaryBinding
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditDiaryBinding

    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    // データ追加
    fun addData(view: View?) {
        // SharedPreferenceに保存している自分のユーザーIDも、一緒に登録する。
        val myUserIdData: SharedPreferences = getSharedPreferences("MyUserId", Context.MODE_PRIVATE)
        val myUserId = myUserIdData.getString("userId", "")

        val title = binding.diaryTitleEdit.text.toString()
        println("title: $title")
        val content = binding.diaryContentEdit.text.toString()
        println("content: $content")

        val user = hashMapOf(
            "userId" to myUserId,
            "title" to title,
            "content" to content
        )

        // Add a new document with a generated ID
        db.collection("User").document(myUserId!!).collection("Diary")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d("DEBUG", "DocumentSnapshot added with ID: ${documentReference}")
            }
            .addOnFailureListener { e ->
                Log.d("DEBUG", "Error adding document", e)
            }

    }
}