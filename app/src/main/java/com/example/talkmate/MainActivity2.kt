package com.example.talkmate

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.talkmate.ui.theme.TAlkMAteTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TAlkMAteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    getIntentData();
                }
            }
        }
    }
    @Composable
    private fun getIntentData() {
        val userName = intent.getStringExtra("UserName")
        val password = intent.getStringExtra("Password")
        if (!userName.equals("") && !password.equals("")) {
            updateUI(userName.toString(), password.toString())
        }
    }
    @Composable
    private fun updateUI(userName: String, password: String) {
        val context = LocalContext.current
        Column() {
            Text(text = userName, Modifier.clickable { Toast.makeText(context, "text clicked", Toast.LENGTH_SHORT) })
            Text(text = password)
        }
    }

    @Composable
    fun Greeting(name: String, modifier: Modifier = Modifier) {
        Text(
            text = "$name!",
            modifier = modifier
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        TAlkMAteTheme {
            Greeting("Android")
        }
    }
}

