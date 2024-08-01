package com.example.practicalblockkotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.practicalblockkotlin.ui.theme.PracticalBlockKotlinTheme
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KProperty

class MainActivity : ComponentActivity() {

    private val startAppTime by StartAppTimeDelegate()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PracticalBlockKotlinTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

class StartAppTimeDelegate {

    private val startTime = System.currentTimeMillis()
    private var isLogging = false

    @OptIn(DelicateCoroutinesApi::class)
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Long {
        isLogging = true
        GlobalScope.launch(Dispatchers.IO) {
            while (isLogging) {
                println(startTime)
                delay(3000)
            }
        }
        return startTime
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PracticalBlockKotlinTheme {
        Greeting("Android")
    }
}