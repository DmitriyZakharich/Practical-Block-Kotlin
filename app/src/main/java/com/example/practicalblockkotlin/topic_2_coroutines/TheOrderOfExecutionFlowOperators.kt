package com.example.practicalblockkotlin.topic_2_coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
    Задача по flow
    Напротив каждой строки написать диспатчер и порядок в котором вызывается цепочка
 */

class TheOrderOfExecutionFlowOperators {

    fun myFlow() {
        CoroutineScope(context = Dispatchers.Main).launch() {
            doAction("doAction")  //  1   Dispatchers.Main.immediate

            flowOf("Hey")   //  3  Dispatchers.Default
                .onEach { doAction("onEach") }  //  4  Dispatchers.Default
                .map { it.length }  //  5   Dispatchers.Default
                .onStart { doAction("onStart") } // 6  Dispatchers.Default
                .flowOn(Dispatchers.Default)    //  2 Main.immediate -> Default

                .flatMapMerge { //  8  Dispatchers.IO
                    doAction("flatMapMerge")  //    9   Dispatchers.IO
                    flowOf(1)   //  11  Dispatchers.Main
                        .flowOn(Dispatchers.Main)   //  10  IO -> Main
                        .onEach { doAction("onEach") }  //  12  Dispatchers.IO
                }
                .flowOn(Dispatchers.IO) //  7   Default -> IO
                .collect {  //  12  Dispatchers.Main.immediate
                    doAction("collect")  // 13  Dispatchers.Main.immediate
                }
        }
    }

    private fun doAction(s: String) {
        println(s)
    }
}