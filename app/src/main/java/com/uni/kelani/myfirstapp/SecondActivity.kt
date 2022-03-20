package com.uni.kelani.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val button= findViewById<Button>(R.id.button_second_activity)
        val textView =findViewById<TextView>(R.id.textview_second_activity)
        button.setOnClickListener {
            textView.text = "Nice Job Isuruni"
        }
    }
}