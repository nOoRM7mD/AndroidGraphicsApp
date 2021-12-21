package com.example.myandroidgraphicsapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class CircleView(context: Context) : View(context) {
    private var paintGreen: Paint = Paint()
    private var paintRed: Paint = Paint()

    init {
        with(paintGreen) {
            style = Paint.Style.FILL
            color = Color.GREEN
            isAntiAlias = true
        }
        with(paintRed){
            style = Paint.Style.FILL
            color = Color.RED
            isAntiAlias = true
        }
        setBackgroundColor(Color.GRAY)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(300F, 300F, 250F, paintGreen)
        canvas?.drawCircle(500F, 450F,  50F, paintRed)

    }
}