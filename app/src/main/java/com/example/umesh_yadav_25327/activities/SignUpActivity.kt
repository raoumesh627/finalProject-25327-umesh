package com.example.umesh_yadav_25327.activities

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umesh_yadav_25327.R
import com.example.umesh_yadav_25327.models.UserModel
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.Objects

class SignUpActivity : AppCompatActivity() {
    var name: EditText? = null
    var email: EditText? = null
    var password: EditText? = null
    var signup: MaterialButton? = null
    var database: FirebaseDatabase? = null
    var auth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        name = findViewById(R.id.edtSignUpFullName)
        password = findViewById(R.id.edtSignUpPassword)
        email = findViewById(R.id.edtSignUpEmail)
        signup = findViewById(R.id.btnSignUp)
        signup?.setOnClickListener(View.OnClickListener { createUser() })
    }

    private fun createUser() {
        val userName = name!!.text.toString()
        val userEmail = email!!.text.toString()
        val userPassword = password!!.text.toString()
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Name is empty.", Toast.LENGTH_SHORT).show()
        }
        if (TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Email is empty.", Toast.LENGTH_SHORT).show()
        }
        if (TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Password is empty.", Toast.LENGTH_SHORT).show()
        }
        auth!!.createUserWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userModel = UserModel(userName, userEmail, userPassword)
                    val id = Objects.requireNonNull(task.result.user)!!.uid
                    database!!.reference.child("Users").child(id).setValue(userModel)
                    Toast.makeText(
                        this@SignUpActivity,
                        "Registration Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
                } else {
                    Toast.makeText(
                        this@SignUpActivity,
                        "Error:" + task.exception,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}