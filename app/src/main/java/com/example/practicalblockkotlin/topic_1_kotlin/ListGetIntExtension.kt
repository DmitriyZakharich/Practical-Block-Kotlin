package com.example.practicalblockkotlin.topic_1_kotlin

import android.util.Log
import com.example.practicalblockkotlin.TAG

class ListOperator {
    fun execute(list: List<Any>) {
        val result = list.getInt()
        if (result != null) {
            Log.d(TAG, "Int = $result")
        } else {
            Log.d(TAG, "В list нет Int-элемента")
        }
    }
}

fun List<Any>.getInt(): Int? {
    this.forEach {
        if (it is Int) {
            return it
        }
    }
    return null
}