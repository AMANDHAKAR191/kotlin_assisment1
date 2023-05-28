package com.example.talkmate

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import com.example.talkmate.ui.theme.TAlkMAteTheme

class MainActivity : ComponentActivity() {
    var openDialog by mutableStateOf(false)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TAlkMAteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    loginUI()
                    ShowDialog()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun loginUI() {
        val constraints = ConstraintSet {

            val imageProfile = createRefFor("imageProfile")
            val textTitle = createRefFor("textTitle")
            val textUsername = createRefFor("textUsername")
            val textFieldUserName = createRefFor("textFieldUserName")
            val textPassword = createRefFor("textPassword")
            val textOr = createRefFor("textOr")
            val textFieldPassword = createRefFor("textFieldPassword")
            val buttonCreateAccount = createRefFor("buttonCreateAccount")
            val buttonLogin = createRefFor("buttonLogin")
            val buttonSignInWithGoogle = createRefFor("buttonSignInWithGoogle")

            constrain(imageProfile) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                height = Dimension.value(100.dp)
                width = Dimension.value(100.dp)
            }
            constrain(textTitle) {
                top.linkTo(imageProfile.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
                height = Dimension.wrapContent
            }
            constrain(textUsername) {
                top.linkTo(textTitle.bottom)
                start.linkTo(parent.start)
                height = Dimension.wrapContent
            }
            constrain(textFieldUserName) {
                top.linkTo(textUsername.bottom)
                start.linkTo(textUsername.start)
                end.linkTo(parent.end)
            }
            constrain(textPassword) {
                top.linkTo(textFieldUserName.bottom)
                start.linkTo(parent.start)
                height = Dimension.wrapContent
            }
            constrain(textFieldPassword) {
                top.linkTo(textPassword.bottom)
                start.linkTo(textPassword.start)
                end.linkTo(parent.end)
            }
            constrain(buttonLogin) {
                top.linkTo(textFieldPassword.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
            constrain(textOr) {
                top.linkTo(buttonLogin.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.wrapContent
            }
            constrain(buttonSignInWithGoogle) {
                top.linkTo(textOr.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
            constrain(buttonCreateAccount) {
                top.linkTo(buttonSignInWithGoogle.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }

        }
        ConstraintLayout(
            constraints, modifier = Modifier
                .fillMaxSize()
                .padding(top = 50.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.keys_app_icon),
                contentDescription = "keys",
                modifier = Modifier.layoutId("imageProfile")
            )
            Text(
                text = "Login", modifier = Modifier
                    .layoutId("textTitle")
                    .padding(top = 30.dp)
            )
            Text(
                text = "Email id",
                modifier = Modifier
                    .layoutId("textUsername")
                    .padding(top = 50.dp, start = 50.dp)
            )
            var userName by remember {
                mutableStateOf("")
            }
            TextField(value = userName, onValueChange = { userName = it }, leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "person")
            }, label = {
                Text(text = "xyz@gmail.com")
            }, modifier = Modifier
                .layoutId("textFieldUserName")
                .padding(top = 10.dp)
            )
            Text(
                text = "Password",
                modifier = Modifier
                    .layoutId("textPassword")
                    .padding(top = 10.dp, start = 50.dp)
            )
            var password by remember {
                mutableStateOf("")
            }
            var passwordVisibility by remember { mutableStateOf(false) }
            TextField(
                value = password,
                onValueChange = { password = it },
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Lock, contentDescription = "person")
                },
                label = {
                    Text(text = "Enter Password")
                },
                modifier = Modifier
                    .layoutId("textFieldPassword")
                    .padding(top = 10.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            )
            val context = LocalContext.current
            OutlinedButton(
                onClick = { /*TODO*/ Toast.makeText(context, "Create Account", Toast.LENGTH_SHORT)
                    .show()
                },
                modifier = Modifier
                    .layoutId("buttonCreateAccount")
                    .padding(start = 50.dp, end = 50.dp, top = 20.dp)
            ) {
                Text(text = "Create Account")
            }

            Button(
                onClick = {
                    if (validateData(userName, password)) {
                        val intent = Intent(this@MainActivity, MainActivity2::class.java)
                        intent.putExtra("UserName", userName)
                        intent.putExtra("Password", password)
                        startActivity(intent)
                    } else {
                        openDialog = true;
                    }
                },
                modifier = Modifier
                    .layoutId("buttonLogin")
                    .padding(start = 50.dp, end = 50.dp, top = 20.dp)
            ) {
                Text(text = "Login")
            }

            Text(
                text = "or",
                modifier = Modifier
                    .layoutId("textOr")
                    .padding(top = 10.dp)
            )
            OutlinedButton(
                onClick = {
                    Toast.makeText(context, "Signing with Google", Toast.LENGTH_SHORT)
                        .show()
                },
                modifier = Modifier
                    .layoutId("buttonSignInWithGoogle")
                    .padding(start = 50.dp, end = 50.dp, top = 20.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_google_logo),
                    contentDescription = "google logo",
                    modifier = Modifier.height(36.dp)
                )
                Text(text = "Sign In")
            }
        }
    }

    @Composable
    fun ShowDialog() {
        if (openDialog) {
            AlertDialog(
                onDismissRequest = { openDialog = false },
                confirmButton = {
                    Button(onClick = { openDialog = false }) {
                        Text(text = "ok")
                    }
                },
                title = { Text(text = "Alert") },
                text = { Text(text = "Username or password is incorrect", color = Color.Red) },
                shape = AlertDialogDefaults.shape,
                properties = DialogProperties()
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        TAlkMAteTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ) {
                loginUI()
            }
        }
    }

    fun validateData(userName: String, password: String): Boolean {
        return if (userName.isEmpty() || password.isEmpty()) false else false
    }
}

