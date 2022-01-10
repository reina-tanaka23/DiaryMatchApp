package com.example.DiaryMatchingApp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.DiaryMatchingApp.databinding.ActivityShowDiaryBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ShowDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShowDiaryBinding
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowDiaryBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    // データ読み取り
    fun getData(view: View?) {
        // 友達のIDリストを取得
        val friendIdData: SharedPreferences = getSharedPreferences("FriendUserIds", Context.MODE_PRIVATE)
        val friendIds = friendIdData.getString("friendUserId", "")

        db.collection("User").document(friendIds!!).collection("Diary")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d("DEBUG", "${document.id} => ${document.data}")
                    binding.allDiaryText.text = document.data.toString()
                }
            }
            .addOnFailureListener { exception ->
                Log.d("DEBUG", "Error getting documents.", exception)
            }
    }
}