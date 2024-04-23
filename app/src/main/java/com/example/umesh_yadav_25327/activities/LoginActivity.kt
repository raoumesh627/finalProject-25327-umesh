package com.example.umesh_yadav_25327.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umesh_yadav_25327.R
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {
    var email: EditText? = null
    var password: EditText? = null
    var login: MaterialButton? = null
    var signup: TextView? = null
    var database: FirebaseDatabase? = null
    var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
        signup = findViewById(R.id.txtSignUp)
        password = findViewById(R.id.edtSignInPassword)
        email = findViewById(R.id.edtSignInEmail)
        login = findViewById(R.id.btnSignIn)
        signup?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@LoginActivity,
                    SignUpActivity::class.java
                )
            )
        })
        login?.setOnClickListener(View.OnClickListener { loginUser() })
    }

    private fun loginUser() {
        val userEmail = email!!.text.toString()
        val userPassword = password!!.text.toString()
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is empty.", Toast.LENGTH_SHORT).show()
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Password is empty.", Toast.LENGTH_SHORT).show()
        }
        auth!!.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@LoginActivity, "Success", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Error:" + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}