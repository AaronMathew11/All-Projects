package com.example.drawingapplication

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs){

    private var mdrawpath : CustomPath?=null
    private var mcanvasbitmap : Bitmap?= null
    private var mdrawpaint : Paint?= null
    private var mcanvaspaint : Paint?= null
    private var mbrushsize : Float = 0.toFloat()
    private var color= Color.BLACK
    private var canvas: Canvas?= null

    init{
        setupdrawing
    }


    internal inner class CustomPath (var color: Int, var brushthickness : Float): Path(){


    }
}