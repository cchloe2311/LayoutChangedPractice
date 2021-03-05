package com.example.layoutchangedpractice

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.widget.LinearLayout

class TestLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        Log.d("TestLayout log", "TestLayout onMeasure Called $id")
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        Log.d("TestLayout log", "TestLayout onLayout Called $id")
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onDraw(canvas: Canvas?) {
        Log.d("TestLayout log", "TestLayout onDraw Called $id")
        super.onDraw(canvas)
    }
}