package com.example.drawingapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs){

        private var mdrawpath : CustomPath?=null
        private var mcanvasbitmap : Bitmap?= null
        private var mdrawpaint : Paint?= null
        private var mcanvaspaint : Paint?= null
        private var mbrushsize : Float = 0.toFloat()
        private var color= Color.WHITE
        private var canvas: Canvas?= null
        private var mpaths = ArrayList<CustomPath>()


    init{
        setupdrawing()
    }

    private fun setupdrawing()
    {
        mdrawpaint= Paint()
        mdrawpath=CustomPath(color,mbrushsize)
        mdrawpaint!!.color=color
        mdrawpaint!!.style=Paint.Style.STROKE
        mdrawpaint!!.strokeJoin=Paint.Join.ROUND
        mdrawpaint!!.strokeCap=Paint.Cap.ROUND
        mcanvaspaint=Paint(Paint.DITHER_FLAG)
        mbrushsize=10.toFloat()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mcanvasbitmap= Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvas= Canvas(mcanvasbitmap!!)
    }

    //change canvas to canvas? if fails

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mcanvasbitmap!!,0f,0f,mcanvaspaint)

        for(path in mpaths){
            mdrawpaint!!.strokeWidth=path.brushthickness
            mdrawpaint!!.color=path.color
            canvas.drawPath(path,mdrawpaint!!)
        }

        if(!mdrawpath!!.isEmpty){
            mdrawpaint!!.strokeWidth=mdrawpath!!.brushthickness
            mdrawpaint!!.color=mdrawpath!!.color
            canvas.drawPath(mdrawpath!!,mdrawpaint!!)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX =  event?.x
        val touchY = event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN -> {
                mdrawpath!!.color=color
                mdrawpath!!.brushthickness=mbrushsize

                mdrawpath!!.reset()
                mdrawpath!!.moveTo(touchX!!,touchY!!)
            }
            MotionEvent.ACTION_MOVE -> {
                mdrawpath!!.lineTo(touchX!!,touchY!!)
            }
            MotionEvent.ACTION_UP -> {
                mpaths.add(mdrawpath!!)
                mdrawpath= CustomPath(color,mbrushsize)
            }
            else -> return false
        }
        invalidate()
        return true
    }

    internal inner class CustomPath (var color: Int, var brushthickness : Float): Path(){


    }
}