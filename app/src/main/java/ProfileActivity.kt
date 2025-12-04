package com.example.navkaushal

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val vitalsRow = findViewById<LinearLayout>(R.id.vitals_row)
        val vitalsDetails = findViewById<LinearLayout>(R.id.vitals_details)
        val vitalsArrow = findViewById<ImageView>(R.id.vitals_arrow)

        vitalsRow.setOnClickListener {
            if (vitalsDetails.visibility == View.GONE) {
                expand(vitalsDetails)
                vitalsArrow.rotation = 90f
            } else {
                collapse(vitalsDetails)
                vitalsArrow.rotation = 0f
            }
        }

        // ✅ Profile click → already on ProfileActivity, so do nothing
        val profileLayout = findViewById<LinearLayout>(R.id.profileLayout)
        profileLayout.setOnClickListener {
            // Stay here
        }

        // ✅ Events click → go to FeedActivity
        val eventsLayout = findViewById<LinearLayout>(R.id.eventsLayout)
        eventsLayout.setOnClickListener {
            val intent = Intent(this, FeedActivity::class.java)
            startActivity(intent)
            finish() // prevent back stack returning to Profile
        }

        // ✅ Rulebook click → open RulebookActivity
        val rulebookLayout = findViewById<LinearLayout>(R.id.rulebookLayout)
        rulebookLayout.setOnClickListener {
            val intent = Intent(this, RulebookActivity::class.java)
            startActivity(intent)
        }
        // ✅ Tests → (optional) create TestsActivity
        val testsLayout = findViewById<LinearLayout>(R.id.testsLayout)
        testsLayout.setOnClickListener {
             val intent = Intent(this, TestsActivity::class.java)
             startActivity(intent)
        }


    }

    // Expand function
    private fun expand(view: View) {
        view.measure(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        val targetHeight = view.measuredHeight
        view.layoutParams.height = 0
        view.visibility = View.VISIBLE
        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                view.layoutParams.height =
                    if (interpolatedTime == 1f) LinearLayout.LayoutParams.WRAP_CONTENT
                    else (targetHeight * interpolatedTime).toInt()
                view.requestLayout()
            }

            override fun willChangeBounds(): Boolean = true
        }
        animation.duration =
            (targetHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(animation)
    }

    // Collapse function
    private fun collapse(view: View) {
        val initialHeight = view.measuredHeight
        val animation = object : Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                if (interpolatedTime == 1f) {
                    view.visibility = View.GONE
                } else {
                    view.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }

            override fun willChangeBounds(): Boolean = true
        }
        animation.duration =
            (initialHeight / view.context.resources.displayMetrics.density).toLong()
        view.startAnimation(animation)
    }
}
