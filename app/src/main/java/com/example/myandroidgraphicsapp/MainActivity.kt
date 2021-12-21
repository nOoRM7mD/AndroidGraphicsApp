package com.example.myandroidgraphicsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    lateinit var glView: CircleView
    lateinit var squareInCircleView: MyView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        glView = CircleView(this)
        squareInCircleView = MyView(this)
        setContentView(squareInCircleView)
    }
}