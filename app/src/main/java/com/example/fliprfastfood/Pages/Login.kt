package com.example.fliprfastfood.Pages

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fliprfastfood.ViewModel.AuthState
import com.example.fliprfastfood.ViewModel.AuthViewModel


@Composable
fun Login(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate("home")
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_SHORT
            ).show()
            else -> Unit
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Login Page", fontSize = 32.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Email Field with Validation
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
                emailError = if (!email.isValidEmail()) "Invalid email format" else ""
            },
            label = { Text(text = "Email") },
            isError = emailError.isNotEmpty(),
        )

        if (emailError.isNotEmpty()) {
            Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Password Field with Validation
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
                passwordError = if (!password.isValidPassword()) "Password must be more than 8 characters" else ""
            },
            label = { Text(text = "Password") },
            isError = passwordError.isNotEmpty(),
            visualTransformation = PasswordVisualTransformation()
        )

        if (passwordError.isNotEmpty()) {
            Text(
                text = passwordError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Login Button
        Button(
            onClick = {
                if (email.isValidEmail() && password.isValidPassword()) {
                    authViewModel.login(email, password)
                }
            },
            enabled = authState.value != AuthState.Loading
        ) {
            Text(text = "Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Navigate to Sign-Up Page
        TextButton(onClick = {
            navController.navigate("signup")
        }) {
            Text(text = "Don't have an account? Sign Up")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Google and Facebook Login buttons
        Text(text = "Or", modifier = Modifier.padding(vertical = 8.dp))

        Button(
            onClick = { /* Implement Google Login Logic Here */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login with Google")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = { /* Implement Facebook Login Logic Here */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login with Facebook")
        }
    }
}

// Utility class for validation



    fun String.isValidEmail(): Boolean {
        return this.matches(Regex("^[a-zA-Z0-9._-]+@flipr.ai$"))
    }

    fun String.isValidPassword(): Boolean {
        return this.length > 8
    }

