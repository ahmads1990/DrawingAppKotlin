package com.example.drawingappkotlin.shapes

import android.graphics.Canvas
import android.graphics.Paint


class Arrow(
    private val startX: Float,
    private val startY: Float,
    private val endX: Float,
    private val endY: Float, //paint information
    var paint: Paint
) {
    //edge left and right points
    //x->[0] y->[1]
    private val edgeRight: FloatArray
    private val edgeLeft: FloatArray
    private fun initEdges() {
        //x1,y1 start point and x2,y2 end point
        //x3=x2+L2L1[(x1−x2)cosθ+(y1−y2)sinθ]
        //y3=y2+L2L1[(y1−y2)cosθ−(x1−x2)sinθ]
        //x4=x2+L2L1[(x1−x2)cosθ−(y1−y2)sinθ]
        //y4=y2+L2L1[(y1−y2)cosθ+(x1−x2)sinθ]

        //left edge
        edgeLeft[0] =
            endX + arrowEdgeLen * ((startX - endX) * Math.cos(angle.toDouble()) + (startY - endY) * Math.cos(
                angle.toDouble()
            )).toFloat()
        edgeLeft[1] =
            endY + arrowEdgeLen * ((startY - endY) * Math.cos(angle.toDouble()) - (startX - endX) * Math.cos(
                angle.toDouble()
            )).toFloat()

        //right edge
        edgeRight[0] =
            endX + arrowEdgeLen * ((startX - endX) * Math.cos(angle.toDouble()) - (startY - endY) * Math.cos(
                angle.toDouble()
            )).toFloat()
        edgeRight[1] =
            endY + arrowEdgeLen * ((startY - endY) * Math.cos(angle.toDouble()) + (startX - endX) * Math.cos(
                angle.toDouble()
            )).toFloat()
    }

    fun drawArrow(canvas: Canvas) {
        //draw arrow body
        canvas.drawLine(startX, startY, endX, endY, paint)
        //draw edges
        canvas.drawLine(endX, endY, edgeLeft[0], edgeLeft[1], paint)
        canvas.drawLine(endX, endY, edgeRight[0], edgeRight[1], paint)
    }

    companion object {
        //length of the edge = 0.4 the length of the arrow
        private const val arrowEdgeLen = 0.4f
        private const val angle = 30f
    }

    init {
        edgeLeft = floatArrayOf(0f, 0f)
        edgeRight = floatArrayOf(0f, 0f)
        initEdges()
    }
}
