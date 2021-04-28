package com.example.adg_task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var str = StringBuilder()
    private lateinit var editText: TextView
    private lateinit var numbut: Array<Button>
    private lateinit var opbut: List<Button>
    private var operator: Operator= Operator.NONE
    private var isOperatorclicked: Boolean=false
    private var operand1:Int =0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializing() }

    private fun initializing() {
        editText = findViewById(R.id.editText)
        opbut = listOf(minus, add, mul, devide,mod)
        for (i in opbut) {
            i.setOnClickListener { opbuttonclicked(i) }
        }
        numbut = arrayOf(but0, but1, but2, but3, but4, but5, but6, but7, but8, but9)
        for (i in numbut) {
            i.setOnClickListener { butclicked(i) }
        }
        equals.setOnClickListener { buttonequalsclicked() }
        buac.setOnClickListener { buttonacclicked() }


    }
    private fun buttonacclicked() {
        editText.text=""
        str.clear() }
    private fun buttonequalsclicked() {
        val operand2= str.toString().toInt()
        var result:Int
        when(operator){
            Operator.ADD-> result=operand1+operand2
            Operator.SUB-> result=operand1-operand2
            Operator.MUL-> result=operand1*operand2
            Operator.DIV-> result=operand1/operand2
            Operator.REM-> result=operand1%operand2
            else -> result=0 }
        str.clear()
        str.append(result.toString())
        editText.text=str
        isOperatorclicked=true }
    private fun opbuttonclicked(btn:Button) {
        if(btn.text=="+")operator=Operator.ADD
        else if(btn.text=="-")operator=Operator.SUB
        else if(btn.text=="x")operator=Operator.MUL
        else if(btn.text=="/")operator=Operator.DIV
        else if(btn.text=="%")operator=Operator.REM
        else operator=Operator.NONE
        isOperatorclicked=true }
    private fun butclicked(btn: Button) {
        if(isOperatorclicked){
            operand1=str.toString().toInt()
            str.clear()
            isOperatorclicked=false }
        str.append(btn.text)
        editText.text = str } }
enum class Operator {ADD,SUB,MUL,DIV,NONE,REM}