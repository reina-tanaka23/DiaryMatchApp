package com.example.helloandroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helloandroid.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        auth = Firebase.auth

        binding.toEditDiaryBtn.setOnClickListener {
            val intent = Intent(application, EditDiaryActivity::class.java)
            startActivity(intent)
        }

        binding.toShowDataBtn.setOnClickListener {
            val intent = Intent(application, ShowDiaryActivity::class.java)
            startActivity(intent)
        }

        binding.toregisterIdBtn.setOnClickListener {
            val intent = Intent(application, ManageUserIdActiity::class.java)
            startActivity(intent)
        }
    }

    // ログイン
    fun doLogin(view: View?) {
        val email = binding.mailText.text.toString() + ""
        val password: String = binding.passText.text.toString() + ""
        Toast.makeText(this@MainActivity, "Login start.", Toast.LENGTH_SHORT).show()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // ログイン成功
                    Toast.makeText(this@MainActivity, "Logined!!", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // ログイン失敗
                    Toast.makeText(this@MainActivity, "Authentication failed.", Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                }
            }
    }

    // ユーザー作成
    fun createAccount(view: View?) {
        val email = binding.mailText.text.toString() + ""
        val password: String = binding.passText.text.toString() + ""

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // ユーザー登録成功
                    Log.d("DEBUG", "createUserWithEmail:success")
                    val user = auth.currentUser
//                    updateUI(user)
                } else {
                    // ユーザー登録失敗
                    Log.d("DEBUG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
//                    updateUI(null)
                }
            }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
//        updateUI(currentUser)
    }
}