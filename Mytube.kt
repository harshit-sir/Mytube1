package com.example.mytube

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Root layout
        val rootLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16)
        }

        // 1️⃣ Sign In section
        val signInLayout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            val email = EditText(this@MainActivity)
            email.hint = "Email"
            val password = EditText(this@MainActivity)
            password.hint = "Password"
            password.inputType = 129 // TYPE_TEXT_VARIATION_PASSWORD
            val signInButton = Button(this@MainActivity)
            signInButton.text = "Sign In"
            addView(email)
            addView(password)
            addView(signInButton)
        }

        rootLayout.addView(signInLayout)

        // 2️⃣ Search bar
        val searchBar = EditText(this).apply {
            hint = "Search topics..."
        }
        rootLayout.addView(searchBar)

        // 3️⃣ Video list (dummy data)
        val videoList = ListView(this)
        val videos = arrayListOf(
            "Video 1: Kotlin Tutorial",
            "Video 2: Android App Development",
            "Video 3: Magic Tricks",
            "Video 4: Science Experiments",
            "Video 5: Music Videos"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, videos)
        videoList.adapter = adapter
        rootLayout.addView(videoList)

        // 4️⃣ Search functionality
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val filtered = videos.filter { it.contains(s.toString(), true) }
                videoList.adapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, filtered)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // 5️⃣ History button
        val historyButton = Button(this).apply {
            text = "View History"
            setOnClickListener {
                Toast.makeText(this@MainActivity, "History clicked (to be implemented)", Toast.LENGTH_SHORT).show()
            }
        }
        rootLayout.addView(historyButton)

        setContentView(rootLayout)
    }
}
