package com.example.fliprfastfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.fliprfastfood.R
import com.example.fliprfastfood.ViewModel.AuthViewModel
import com.example.fliprfastfood.ViewModel.AuthState
import com.google.firebase.auth.FirebaseAuth
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController


@Composable
fun ProfilePage(modifier: Modifier,
                authViewModel: AuthViewModel,
                navController: NavController) {
    val authState by authViewModel.authState.observeAsState(AuthState.Unauthenticated)
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (authState) {
            is AuthState.Loading -> {
                CircularProgressIndicator() // Display loading indicator while checking auth state
            }
            is AuthState.Authenticated -> {
                // Display user profile information
                user?.let {
                    // Display profile picture (if available)
                    it.photoUrl?.let { url ->
                        Image(
                            painter = rememberImagePainter(url),
                            contentDescription = "Profile Picture",
                            modifier = Modifier.size(120.dp)
                        )
                    } ?: run {
                        // Default image if no profile picture is available
                        Image(
                            painter = painterResource(id = R.drawable.user),
                            contentDescription = "Default Avatar",
                            modifier = Modifier.size(120.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Display email
                    Text(
                        text = it.email ?: "No email available",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Sign Out Button
                    Button(onClick = { authViewModel.signout() }) {
                        Text(text = "Sign Out")
                    }
                }
            }
            is AuthState.Unauthenticated -> {
                // If unauthenticated, prompt user to sign in
                Text(text = "You are not signed in.", style = MaterialTheme.typography.bodyLarge)

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {
                    // Trigger sign-in process
                    authViewModel.login("test@example.com", "password123") // Example sign-in trigger
                }) {
                    Text(text = "Sign In")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    // Trigger sign-up process
                    authViewModel.signup("test@example.com", "password123") // Example sign-up trigger
                }) {
                    Text(text = "Sign Up")
                }
            }
            is AuthState.Error -> {
                // Show error message
                Text(
                    text = (authState as AuthState.Error).message,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            else -> {
                // In case there's an unexpected state (although this should be unnecessary)
                Text(
                    text = "Unexpected state: $authState",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

