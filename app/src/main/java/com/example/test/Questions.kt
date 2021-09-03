package com.example.test

import android.widget.EditText

class Question(q: String, c: String, p: String) {
    var question: String = q
    var cAnswer: String = c
    var pAnswer: String = p
}
class Questions {
    private val questionList = mutableListOf<Question>()
    private var totalQ = 0
    private var currentQ = 0

    fun getTotalQ(): Int{
        return totalQ
    }

    fun previousQuestion(answer: String): String{
        storeAnswer(currentQ-1, answer)
        if(currentQ == 1) return questionList[0].question
        return questionList[--currentQ-1].question
    }


    fun nextQuestion(answer: String, aEdit: EditText): String{
        storeAnswer(currentQ-1, answer)
        if(currentQ < totalQ){
            aEdit.setText(questionList[currentQ].pAnswer)
            return questionList[++currentQ-1].question

        }
        return newQuestion()
    }

    fun newQuestion(): String{
        val range = (100 .. 999)
        val list = listOf("+", "-")
        val num1 = range.random()
        val operand = list.random()
        val num2 = range.random()
        val answer = if(operand == "+"){
            num1 + num2
        };else {
            num1 - num2
        }
        val question = "$num1 $operand $num2"
        questionList.add(Question(question, answer.toString(), ""))
        totalQ ++
        currentQ ++
        return question
    }

    private fun storeAnswer(id:Int, answer: String){
        questionList[id].pAnswer = answer
    }

    fun retrieveAnswer(): String{
        return questionList[currentQ-1].pAnswer
    }

    fun correctlyAnswered(): Int{
        val answersIterator = questionList.iterator()
        var correct = 0
        while(answersIterator.hasNext()){
            val temp = answersIterator.next()
            if(temp.cAnswer == temp.pAnswer){
                correct++
            }
        }
        return correct
    }

    fun finalStore(answer: String){
        questionList[currentQ-1].pAnswer = answer
    }
}


