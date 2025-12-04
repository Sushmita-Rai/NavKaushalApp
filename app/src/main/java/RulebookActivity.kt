package com.example.navkaushal

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class RulebookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rulebook)

        // ✅ Profile → go to ProfileActivity
        val profileLayout = findViewById<LinearLayout>(R.id.profileLayout)
        profileLayout.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // ✅ Events → go to FeedActivity
        val eventsLayout = findViewById<LinearLayout>(R.id.eventsLayout)
        eventsLayout.setOnClickListener {
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
        }

        // ✅ Tests → (optional) create TestsActivity
        val testsLayout = findViewById<LinearLayout>(R.id.testsLayout)
        testsLayout.setOnClickListener {
            val intent = Intent(this, TestsActivity::class.java)
             startActivity(intent)
        }

        // ✅ Rulebook → already here, so do nothing
        val rulebookLayout = findViewById<LinearLayout>(R.id.rulebookLayout)
        rulebookLayout.setOnClickListener {
            // stay here
        }
    }
}
