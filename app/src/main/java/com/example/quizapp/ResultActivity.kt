package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val ans: String? =intent.getStringExtra(constant.CORRECT_ANSWERS)

        val un:String? =intent.getStringExtra(constant.USER_NAME)
        tvScoreResult.text = "you have $ans out of 10"
        tvUsername.text = un
    }
}