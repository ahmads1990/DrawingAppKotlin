package com.example.drawingappkotlin.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF


class Rectangle(aX: Float, aY: Float, cX: Float, cY: Float, var paint: Paint) {
    //rect abcd
    //a b
    //c d
    var rectangle: RectF
    fun drawRect(canvas: Canvas) {
        canvas.drawRect(rectangle, paint)
    }

    init {
        rectangle = RectF(aX, aY, cX, cY)
    }
}
