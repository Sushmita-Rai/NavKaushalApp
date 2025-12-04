package com.example.navkaushal

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameEdit = findViewById<EditText>(R.id.etUsername)
        val emailEdit = findViewById<EditText>(R.id.etEmail)
        val continueBtn = findViewById<Button>(R.id.btnContinue)

        continueBtn.setOnClickListener {
            val username = usernameEdit.text.toString().trim()
            val email = emailEdit.text.toString().trim()

            if (username.isEmpty()) {
                usernameEdit.error = "Please enter username"
                usernameEdit.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                emailEdit.error = "Please enter email"
                emailEdit.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailEdit.error = "Enter a valid email"
                emailEdit.requestFocus()
                return@setOnClickListener
            }

            // ✅ Start FeedActivity
            val intent = Intent(this, FeedActivity::class.java)
            intent.putExtra(FeedActivity.EXTRA_USERNAME, username)
            intent.putExtra(FeedActivity.EXTRA_EMAIL, email)
            startActivity(intent)

            // ❌ Do NOT call finish() for now (keeps login in back stack)
        }
    }
}
