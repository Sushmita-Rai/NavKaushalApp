package com.example.navkaushal

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class FeedActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_EMAIL = "extra_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)

        // ✅ Get username & email from MainActivity
        val username = intent.getStringExtra(EXTRA_USERNAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        // ✅ Bottom Navigation Setup
        val profileLayout = findViewById<LinearLayout>(R.id.profileLayout)
        profileLayout.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        val rulebookLayout = findViewById<LinearLayout>(R.id.rulebookLayout)
        rulebookLayout.setOnClickListener {
            startActivity(Intent(this, RulebookActivity::class.java))
        }

        val eventsLayout = findViewById<LinearLayout>(R.id.eventsLayout)
        eventsLayout.setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }

        val testsLayout = findViewById<LinearLayout>(R.id.testsLayout)
        testsLayout.setOnClickListener {
            startActivity(Intent(this, TestsActivity::class.java))
        }
    }
}
