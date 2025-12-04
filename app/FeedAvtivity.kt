
class FeedAvtivity : AppCompatActivity() {

    companion object {
        const val EXTRA_USERNAME = "extra_username"
        const val EXTRA_EMAIL = "extra_email"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed) // make sure you have activity_feed.xml

        val username = intent.getStringExtra(EXTRA_USERNAME)
        val email = intent.getStringExtra(EXTRA_EMAIL)

        // Example: display in TextViews
        val usernameTextView: TextView = findViewById(R.id.usernameTextView)
        val emailTextView: TextView = findViewById(R.id.emailTextView)

        usernameTextView.text = username
        emailTextView.text = email
    }
}
