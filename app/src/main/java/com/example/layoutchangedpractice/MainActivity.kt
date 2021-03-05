package com.example.layoutchangedpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * View Tree 구조
     * TestLayout
     * ㄴ TestView(1)
     * ㄴ TestView(2)
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun changeSize(v : View) {
        Log.d("MainActivity log", "changeSize called")
        test_view1.layoutParams = LinearLayout.LayoutParams(400, 400) // TestView(1) size 변경
        /**
         * TestLayout onMeasure called
         * TestView(1) onMeasure called
         * TestLayout onMeasure called
         * TestView(2) onMeasure called
         * TestLayout onMeasure called
         * TestLayout onMeasure called
         *
         * 자식뷰인 TestView(1)의 사이즈 변경이 TestLayout, TestView(2)에도 영향을 끼침
         */
    }
}
