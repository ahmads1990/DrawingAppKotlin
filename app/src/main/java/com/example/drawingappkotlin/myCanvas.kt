package com.example.drawingappkotlin

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.example.drawingappkotlin.shapes.Arrow
import com.example.drawingappkotlin.shapes.Circle
import com.example.drawingappkotlin.shapes.Line
import com.example.drawingappkotlin.shapes.Rectangle
import java.util.*

class myCanvas(context: Context?, attrs: AttributeSet?) :
    View(context, attrs) {
    var path: Path

    //shapes
    var lineArrayList: ArrayList<Line>
    var arrowArrayList: ArrayList<Arrow>
    var rectArrayList: ArrayList<Rectangle>
    var circleArrayList: ArrayList<Circle>

    //points for drawing shapes
    var startPoint: PointF
    var endPoint: PointF
    var doneDrawing = false
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var i = 1

        //
        Log.d("mytag", "current " + currentBrushType.toString())
        if (doneDrawing) {
            when (currentBrushType) {
                brushTypes.DOT -> Log.d("mytag", "0")
                brushTypes.LINE -> {
                    Log.d("mytag", "1")
                    arrowArrayList.add(
                        Arrow(
                            startPoint.x, startPoint.y, endPoint.x, endPoint.y, Paint(
                                paint
                            )
                        )
                    )
                }
                brushTypes.RECT -> {
                    Log.d("mytag", "2")
                    rectArrayList.add(
                        Rectangle(
                            startPoint.x, startPoint.y, endPoint.x, endPoint.y, Paint(
                                paint
                            )
                        )
                    )
                }
                brushTypes.CIRCLE -> {
                    Log.d("mytag", "3")
                    circleArrayList.add(
                        Circle(
                            startPoint.x, startPoint.y, endPoint.x, endPoint.y, Paint(
                                paint
                            )
                        )
                    )
                }
                else -> {
                }
            }
        }
        for (line in lineArrayList) {
            //Log.d("mytag1", "draw " + i);
            i++
            line.drawLine(canvas)
        }
        for (arrow in arrowArrayList) {
            arrow.drawArrow(canvas)
        }
        for (rec in rectArrayList) {
            rec.drawRect(canvas)
        }
        for (circle in circleArrayList) {
            circle.drawCircle(canvas)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path = Path()
                //on key down move the path start to x,y
                path.moveTo(x, y)
                startPoint.x = x
                startPoint.y = y


                //add path to arraylist
                if (currentBrushType == brushTypes.DOT) {
                    lineArrayList.add(Line(path, Paint(paint)))
                }
                doneDrawing = false
            }
            MotionEvent.ACTION_MOVE ->                 //on key movement add the new points to path
                path.lineTo(x, y)
            MotionEvent.ACTION_UP -> {
                //on key up the drawing is finished
                //set end point x y
                endPoint.x = x
                endPoint.y = y
                doneDrawing = true
            }
            else -> return false
        }
        invalidate()
        return true
    }

    fun changeBrush(i: Int) {

        //change brush type based on the order of the tab
        when (i) {
            0 -> {
                currentBrushType = brushTypes.DOT
                Log.d("mytag", "changed dot")
            }
            1 -> {
                currentBrushType = brushTypes.LINE
                Log.d("mytag", "changed line")
            }
            2 -> currentBrushType = brushTypes.RECT
            3 -> currentBrushType = brushTypes.CIRCLE
            else -> Log.d("mytag", "default")
        }
        Log.d("mytag", "state " + currentBrushType.toString())
    }

    fun changeColor(i: Int) {
        when (i) {
            0 -> paint.color = Color.RED
            1 -> paint.color = Color.GREEN
            2 -> paint.color = Color.BLUE
            3 -> paint.color = Color.BLACK
            else -> paint.color = Color.BLACK
        }
    }

    enum class brushTypes {
        DOT, LINE, RECT, CIRCLE
    }

    companion object {
        lateinit var currentBrushType: brushTypes
        lateinit var paint: Paint
    }

    init {
        Log.d("mytag", "started here")
        currentBrushType = brushTypes.DOT
        startPoint = PointF()
        endPoint = PointF()
        lineArrayList = ArrayList<Line>()
        arrowArrayList = ArrayList<Arrow>()
        rectArrayList = ArrayList<Rectangle>()
        circleArrayList = ArrayList<Circle>()
        paint = Paint()
        path = Path()
        paint.isAntiAlias = true
        paint.color = Color.RED
        paint.strokeJoin = Paint.Join.ROUND
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 5f
    }
}