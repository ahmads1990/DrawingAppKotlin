package com.example.drawingappkotlin.shapes

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path


class Line(private val path: Path, private val paint: Paint) {
    fun drawLine(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }
}
