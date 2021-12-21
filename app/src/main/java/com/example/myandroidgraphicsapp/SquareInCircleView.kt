package com.example.myandroidgraphicsapp

import android.content.Context
import android.graphics.*
import android.view.View

class SquareInCircleView(context: Context) : View(context) {
    private var paintGreen: Paint = Paint()
    private var paintRed: Paint = Paint()
    private var polygonPath: Path = Path()
    private var paintBlue: Paint = Paint()
    private var paintBlack: Paint = Paint()
    private var polygonWithLinearGradient: Path = Path()
    private var polygonWithLinearGradientPaint: Paint = Paint()
    private lateinit var linearGradient: LinearGradient
    private var polygonPoints: Array<Point> = arrayOf(
        Point(50, 300), Point(150, 400),
        Point(180, 340), Point
            (240, 420), Point
            (300, 200)
    )

    init {
        with(paintGreen) {
            style = Paint.Style.STROKE
            color = Color.GREEN
            strokeWidth = 5F
            isAntiAlias = true
        }
        with(paintRed) {
            style = Paint.Style.STROKE
            color = Color.RED
            strokeWidth = 5F
            isAntiAlias = true
        }
        with(paintBlue) {
            style = Paint.Style.FILL
            color = Color.BLUE
            isAntiAlias = true
        }
        with(paintBlack) {
            style = Paint.Style.STROKE
            color = Color.BLACK
            strokeWidth = 5F
            isAntiAlias = true
        }
        with(polygonPath) {
            moveTo(50F, 300F)
            lineTo(160F, 280F)
            lineTo(300F, 380F)
            lineTo(380F, 370F)
            lineTo(280F, 450F)
            lineTo(100F, 390F)
            close()
        }

        linearGradient =
            LinearGradient(50F, 300F, 300F, 200F, Color.BLUE, Color.YELLOW, Shader.TileMode.MIRROR)
        with(polygonWithLinearGradientPaint) {
            style = Paint.Style.FILL
            shader = linearGradient
        }
        with(polygonWithLinearGradient) {
            moveTo(50F, 300F)
            lineTo(150F, 400F)
            lineTo(180F, 340F)
            lineTo(240F, 420F)
            lineTo(300F, 200F)
            close()
        }
        setBackgroundColor(Color.GRAY)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRect(500F, 500F, 700F, 700F, paintGreen)
        canvas?.drawCircle(600F, 600F, 145F, paintRed)
        //canvas?.drawPath(polygonPath, paintBlue)
        // canvas?.drawPath(polygonPath, paintBlack)
        //canvas?.drawCircle(250F, 280F, 200F, paintRed)
        canvas?.drawPath(polygonWithLinearGradient, polygonWithLinearGradientPaint)
        updatePolygon(translate(polygonPoints, 80, 100))
        canvas?.drawPath(polygonWithLinearGradient, polygonWithLinearGradientPaint)
    }

    private fun updatePolygon(points: Array<Point>) {
        polygonWithLinearGradient.reset()
        with(polygonWithLinearGradient) {
            moveTo(points[0].x.toFloat(), points[0].y.toFloat())
            for (i in points.indices) {
                lineTo(points[i].x.toFloat(), points[i].y.toFloat())
            }
            close()
        }
    }

    private fun affineTransformation(
        vertices: Array<Point>,
        matrix: Array<Array<Int>>
    ): Array<Point> {

        val result = Array<Point>(vertices.size) { Point() }
        for (i in 0 until vertices.size) {
            val t = matrix[0][0] * vertices[i].x + matrix[0][1] * vertices[i].y + matrix[0][2]
            val u = matrix[1][0] * vertices[i].x + matrix[1][1] * vertices[i].y + matrix[1][2]
            result[i] = Point(t.toInt(), u.toInt())
        }
        return result
    }

    private fun translate(input: Array<Point>, px: Int, py: Int): Array<Point> {
        val matrix = Array(3) { Array<Int>(3) { 0 } }
        matrix[0][0] = 1
        matrix[0][1] = 0
        matrix[0][2] = px
        matrix[1][0] = 0
        matrix[1][1] = 1
        matrix[1][2] = py
        return affineTransformation(input, matrix)
    }
}