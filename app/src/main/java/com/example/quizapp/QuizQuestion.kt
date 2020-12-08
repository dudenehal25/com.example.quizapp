package com.example.quizapp

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_quiz_question.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

class QuizQuestion : AppCompatActivity() {
    private val  questions = constant.getQuestions()
    var counter = 0; var score = 0;
    var selectedText:Int = 0
    var optionCorrect = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)
        set()


    }

    fun onCLick(view: View){
        val username:String?

       if ((view as Button).text == "SUBMIT")
       {
           counter++
           sbProgress.progress = counter
           view.text = "NEXT"

           if (selectedText == optionCorrect)
           {
               score++
               tvScore.text = "$score / $counter"
               answerview(optionCorrect , R.drawable.rightanswer)
           }
           else
           {
               tvScore.setText("$score / $counter")
               answerview(optionCorrect , R.drawable.rightanswer)
               answerview(selectedText , R.drawable.wronganswer)
           }

       }
       else if (view.text == "NEXT")
       {
            set()
           view.text = "SUBMIT"
       }
        if (counter > 10){
            username = intent.getStringExtra(constant.USER_NAME)

            val intent = Intent(this , ResultActivity::class.java)
            intent.putExtra(constant.CORRECT_ANSWERS , score.toString())
            intent.putExtra(constant.USER_NAME ,username)
            startActivity(intent)
            finish()

        }
    }

    fun textClick(view: View){
        defaultView()
        view.setBackgroundResource(R.drawable.clicked)
        (view as TextView).typeface = Typeface.DEFAULT_BOLD
        view.setTextColor(Color.parseColor("#363A43"))
        selectedText  = view.getTag().toString().toInt()
    }

    fun answerview(answer:Int , drawableView : Int ){
        when(answer){

            1 -> { tvOption1.background = ContextCompat.getDrawable(this, drawableView) }
            2 -> { tvOption2.background = ContextCompat.getDrawable(this, drawableView) }
            3 -> { tvOption3.background = ContextCompat.getDrawable(this, drawableView) }
            4 -> { tvOption4.background = ContextCompat.getDrawable(this, drawableView) }
        }
    }

    private fun IntRange.random() = Random().nextInt((endInclusive + 1) - start) + start

    private fun defaultView(){
        val options = ArrayList<TextView>()
        options.add(tvOption1)
        options.add(tvOption2)
        options.add(tvOption3)
        options.add(tvOption4)

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface =  Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this , R.drawable.defaultshape)
        }
    }

    fun set(){
        var i = (0..9).random()

        defaultView()
        ivFlag.setImageResource(questions[i].image)

        tvOption1.text =  questions[i].optionOne
        tvOption2.text =  questions[i].optionTwo
        tvOption3.text = questions[i].optionThree
        tvOption4.text = questions[i].optionFour

         optionCorrect = questions[i].correctAnswer

    }
}