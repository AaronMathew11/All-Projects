package com.example.quizapp

data class questions(
    val id:Int,
    val question: String,
    val image: Int,
    val optionone: String,
    val optiontwo: String,
    val optionthree: String,
    val optionfour: String,
    val correctans: Int
)

