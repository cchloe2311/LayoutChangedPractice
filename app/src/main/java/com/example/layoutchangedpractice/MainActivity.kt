package com.example.layoutchangedpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeSize(v : View) {
        Log.d("MainActivity log", "changeSize called")
        test_view1.layoutParams = LinearLayout.LayoutParams(400, 400)
    }
}
