package com.example.drawingappkotlin.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF


class Circle(aX: Float, aY: Float, cX: Float, cY: Float, var paint: Paint) {
    //rect
    var oval: RectF
    fun drawCircle(canvas: Canvas) {
        canvas.drawOval(oval, paint)
    }

    init {
        oval = RectF(aX, aY, cX, cY)
    }
}
