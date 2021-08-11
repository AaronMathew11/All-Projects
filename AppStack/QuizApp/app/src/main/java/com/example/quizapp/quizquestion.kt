package com.example.quizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quizquestion.*

class quizquestion : AppCompatActivity() ,View.OnClickListener{

    private var mcurrentpos : Int =1
    private var mquestionslist : ArrayList<questions>? =null
    private var mselectedoptpos : Int =0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quizquestion)

        mquestionslist= constants.getquestions()
        setquestions()

        tv_optionone.setOnClickListener(this)
        tv_optiontwo.setOnClickListener(this)
        tv_optionthree.setOnClickListener(this)
        tv_optionfour.setOnClickListener(this)
        btn_submit.setOnClickListener(this)


}
    private fun setquestions(){

        val question = mquestionslist!![mcurrentpos-1]

        defaultoptionview()

        if(mcurrentpos==mquestionslist!!.size){
            btn_submit.text = "FINISH"
        }
        else{
            btn_submit.text="SUBMIT"
        }

        progressBar.progress=mcurrentpos
        tv_progress.text="$mcurrentpos"+"/"+progressBar.max
        tv_question.text=question!!.question
        tv_image.setImageResource(question.image)
        tv_optionone.text=question.optionone
        tv_optiontwo.text=question.optiontwo
        tv_optionthree.text=question.optionthree
        tv_optionfour.text=question.optionfour
    }
    private fun defaultoptionview(){
        val options = ArrayList<TextView>()
        options.add(0,tv_optionone)
        options.add(1,tv_optiontwo)
        options.add(2,tv_optionthree)
        options.add(3,tv_optionfour)
        for(option in options){
            option.setTextColor(Color.parseColor("#FFFFFF"))
            option.typeface = Typeface.DEFAULT
            option.background=ContextCompat.getDrawable(this,R.drawable.optionborder)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_optionone->{
                selectedoptionview(tv_optionone,1)
            }
            R.id.tv_optiontwo->{
                selectedoptionview(tv_optiontwo,2)
            }
            R.id.tv_optionthree->{
                selectedoptionview(tv_optionthree,3)
            }
            R.id.tv_optionfour->{
                selectedoptionview(tv_optionfour,4)
            }
            R.id.btn_submit->{
                if(mselectedoptpos == 0){
                    mcurrentpos ++

                    when{
                        mcurrentpos <= mquestionslist!!.size->{
                            setquestions()
                        }else ->{
                            Toast.makeText(this,"You have successfully completed the Quiz",Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    val question = mquestionslist?.get(mcurrentpos-1)
                    if(question!!.correctans != mselectedoptpos){
                        answerview(mselectedoptpos,R.drawable.optionborderwrong)
                    }
                    answerview(question.correctans,R.drawable.optionbordercorrect)

                    if (mcurrentpos== mquestionslist!!.size){
                        btn_submit.text ="See Results"
                    }
                    else{
                        btn_submit.text="Next Question"
                    }
                    mselectedoptpos =0

                }
            }
        }
    }

    private fun answerview(ans:Int,drawableview: Int){
        when(ans){
            1 ->{
                tv_optionone.background=ContextCompat.getDrawable(this,drawableview)
            }
            2 ->{
                tv_optionone.background=ContextCompat.getDrawable(this,drawableview)
            }
            3 ->{
                tv_optionone.background=ContextCompat.getDrawable(this,drawableview)
            }
            4 ->{
                tv_optionone.background=ContextCompat.getDrawable(this,drawableview)
            }
        }
    }

    private fun selectedoptionview(tv: TextView, selectedoptionnum: Int){
        defaultoptionview()
        mselectedoptpos = selectedoptionnum
        tv.setTextColor(Color.parseColor("#313131"))
        tv.setTypeface(tv.typeface,Typeface.BOLD)
        tv.background=ContextCompat.getDrawable(this,R.drawable.optionborderselected)

    }

}