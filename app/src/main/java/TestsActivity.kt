package com.example.navkaushal

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class TestsActivity : AppCompatActivity() {

    private var expandedContent: LinearLayout? = null
    private val VIDEO_UPLOAD_REQUEST = 101  // request code for picking video

    // Store the actual video files for upload
    private var selectedVideoFile1: File? = null
    private var selectedVideoFile2: File? = null

    private lateinit var videoView1: VideoView
    private lateinit var videoView2: VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        // --- Test 1 expand/collapse ---
        val test1Title = findViewById<TextView>(R.id.test1Title)
        val test1Content = findViewById<LinearLayout>(R.id.test1Content)
        videoView1 = findViewById(R.id.test1VideoView)
        val uploadBtn1 = findViewById<Button>(R.id.test1UploadBtn)

        test1Title.setOnClickListener {
            toggleExpand(test1Content)
        }

        uploadBtn1.setOnClickListener {
            openVideoPicker(1)
        }

        // --- Test 2 expand/collapse ---
        val test2Title = findViewById<TextView>(R.id.test2Title)
        val test2Content = findViewById<LinearLayout>(R.id.test2Content)
        videoView2 = findViewById(R.id.test2VideoView)
        val uploadBtn2 = findViewById<Button>(R.id.test2UploadBtn)

        test2Title.setOnClickListener {
            toggleExpand(test2Content)
        }

        uploadBtn2.setOnClickListener {
            openVideoPicker(2)
        }

        // --- Bottom navigation clicks ---
        findViewById<LinearLayout>(R.id.profileLayout).setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.testsLayout).setOnClickListener {
            findViewById<View>(R.id.testContainer)?.scrollTo(0, 0)
        }
        findViewById<LinearLayout>(R.id.eventsLayout).setOnClickListener {
            startActivity(Intent(this, FeedActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.rulebookLayout).setOnClickListener {
            startActivity(Intent(this, RulebookActivity::class.java))
        }
    }

    // --- Expand/collapse helper ---
    private fun toggleExpand(content: LinearLayout) {
        if (expandedContent != null && expandedContent != content) {
            expandedContent?.visibility = View.GONE
        }
        if (content.visibility == View.GONE) {
            content.visibility = View.VISIBLE
            expandedContent = content
        } else {
            content.visibility = View.GONE
            expandedContent = null
        }
    }

    // --- Open file picker for video ---
    private fun openVideoPicker(testNumber: Int) {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "video/*"
        startActivityForResult(Intent.createChooser(intent, "Select Video"), VIDEO_UPLOAD_REQUEST + testNumber)
    }

    // --- Handle selected video ---
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if ((requestCode == VIDEO_UPLOAD_REQUEST + 1 || requestCode == VIDEO_UPLOAD_REQUEST + 2) && resultCode == RESULT_OK) {
            val videoUri: Uri? = data?.data
            if (videoUri != null) {
                val videoView = if (requestCode == VIDEO_UPLOAD_REQUEST + 1) videoView1 else videoView2
                val selectedFileVar = if (requestCode == VIDEO_UPLOAD_REQUEST + 1) "selectedVideoFile1" else "selectedVideoFile2"

                videoView.visibility = View.VISIBLE
                val mediaController = MediaController(this)
                mediaController.setAnchorView(videoView)
                videoView.setMediaController(mediaController)
                videoView.setVideoURI(videoUri)
                videoView.start()

                // Convert URI to File
                if (requestCode == VIDEO_UPLOAD_REQUEST + 1) {
                    selectedVideoFile1 = uriToFile(videoUri)
                } else {
                    selectedVideoFile2 = uriToFile(videoUri)
                }

                Toast.makeText(this, "Video ready for upload", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to get video", Toast.LENGTH_SHORT).show()
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "Video selection cancelled", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to get video", Toast.LENGTH_SHORT).show()
        }
    }

    // --- Helper function to convert URI to File ---
    private fun uriToFile(uri: Uri): File? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val tempFile = File(cacheDir, "selected_video_${System.currentTimeMillis()}.mp4")
            val outputStream = FileOutputStream(tempFile)
            inputStream?.copyTo(outputStream)
            inputStream?.close()
            outputStream.close()
            tempFile
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // --- Example: Upload file to AI/ML ---
    private fun uploadVideoToAI(testNumber: Int) {
        val file = if (testNumber == 1) selectedVideoFile1 else selectedVideoFile2
        file?.let {
            // Send 'it' to server or ML module
        } ?: run {
            Toast.makeText(this, "No video selected to upload", Toast.LENGTH_SHORT).show()
        }
    }
}

