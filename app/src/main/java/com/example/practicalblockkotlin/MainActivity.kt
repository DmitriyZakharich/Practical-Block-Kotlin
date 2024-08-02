package com.example.practicalblockkotlin

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practicalblockkotlin.ui.theme.PracticalBlockKotlinTheme

const val TAG = "FindIntTag"

class MainActivity : ComponentActivity() {

    private val list: List<Any> = listOf("1", "2", 3f, 'c', 15.0, "123", 4)
    private var delegate by StartAppTimeDelegate()
    private val listOperator = ListOperator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate = true

        enableEdgeToEdge()
        setContent {
            PracticalBlockKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainContent(
                        modifier = Modifier.padding(innerPadding),
                        list = list,
                        listOperator::execute
                    )
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        delegate = false
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    list: List<Any>,
    lambda: (List<Any>) -> Unit
) {
    Button(modifier = modifier, onClick = {
        lambda(list)
    }) {
        Text(
            text = "Найти число в списке",
        )
    }
}