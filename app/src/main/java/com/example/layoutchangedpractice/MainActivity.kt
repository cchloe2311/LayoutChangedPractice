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

        stub.inflate()
        /**
         * 아예 뷰가 다 그려지기 전에 stub inflate() 시,
         * 아예 xml 내에 해당 뷰를 넣은것과 같은 방식으로 onMeasure, onLayout 호출
         *
         * 따라서, 이렇게 뷰가 그려지기 전에 inflate 할 거 아니면 ViewStub을 사용하는게 오히려 좋지 않은 선택이 될거 같음.
         */

        // test_view1.visibility = View.VISIBLE
        /**
         * [xml 내에 visibility = gone으로 하고 뷰가 그려지기 전에 VISIBLE로 바꾸는 방법]
         * stub.inflate() 동일하게 동작함
         * => 그럼 왜 stub을 써야하지?
         *
         * stub은 inflate되어야 클래스가 로드됨 (memeory에 로드)
         * 하지만 visibility로 설정할 경우엔 레이아웃 내 위치를 잡진 않아도 클래스가 로드되어 memory를 잡아먹음
         */

        /**
         * - 뷰가 그려지기 전에 inflate
         * - 특수한 경우에만 해당 뷰를 보여줌
         * => 이런 상황에서는 확실히 ViewStub이 좋은 선택지가 됨!
         */
    }

    fun changeSize(v : View) {
        Log.d("MainActivity log", "changeSize called")
        test_view1.layoutParams = LinearLayout.LayoutParams(400, 400) // TestView(1) size 변경
        /**
         * View Tree 구조
         * TestLayout
         * ㄴ TestView(1)
         * ㄴ TestView(2)
         *
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

    fun showStub(v : View) {
        /**
         * stub이 inflate되지 않았을 때,
         * ViewStub이 xml에 아예 없는 듯 onMeasure, onLayout이 호출되지 않음
         */
        Log.d("MainActivity log", "showStub called")
        stub.inflate()
        /**
         * [stub이 가장 밑에 위치] stub이 inflate되었을 때,
         *
         * View Tree 구조
         * TestLayout
         * ㄴ TestView(1)
         * ㄴ TestView(2)
         * ㄴ StubView
         *
         * TestLayout onMeasure called
         * StubView onMeasure called
         * TestLayout onMeasure called
         * TestLayout onMeasure called
         *
         * TestLayout onLayout called
         * TestView(1) onLayout called
         * TestView(2) onLayout called
         * StubView onLayout called
         * TestLayout onLayout called
         */

        /**
         * [stub이 두 TestView 사이로 이동] stub이 inflate되었을 때,
         *
         * View Tree 구조
         * TestLayout
         * ㄴ TestView(1)
         * ㄴ StubView
         * ㄴ TestView(2)
         *
         * TestLayout onMeasure called
         * StubView onMeasure called
         * TestLayout onMeasure called
         * TestLayout onMeasure called
         * TestView(2) onMeasure called // ** 추가! LinearLayout(orientation = vertical)이라 StubView inflate 영향이 부모뷰, 그 밑에 있는 뷰까지 전파됨
         * TestLayout onMeasure called
         * TestLayout onMeasure called
         *
         * TestLayout onLayout called
         * TestView(1) onLayout called
         * StubView onLayout called
         * TestView(2) onLayout called
         * TestLayout onLayout called
         */
    }

    fun showGoneView(v: View) {
        test_view1.visibility = View.VISIBLE
        /**
         * [gone으로 설정된 view를 다 그려지고나서 visible으로 설정할 경우]
         *
         * View Tree 구조
         * TestLayout
         * ㄴ TestView(1) (init visibility = gone)
         * ㄴ StubView
         * ㄴ TestView(2)
         *
         * TestLayout onMeasure called
         * TestView(1) onMeasure called
         * TestLayout onMeasure called
         * TestLayout onMeasure called
         * StubView onMeasure called
         * TestLayout onMeasure called
         * TestLayout onMeasure called
         * TestView(2) onMeasure called
         * TestLayout onMeasure called
         * TestLayout onMeasure called
         *
         * TestLayout onLayout called
         * TestView(1) onLayout called
         * TestLayout onLayout called
         * StubView onLayout called
         * TestLayout onLayout called
         * TestView(2) onLayout called
         * TestLayout onLayout called
         */
    }
}
