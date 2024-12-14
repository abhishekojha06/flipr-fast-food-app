package com.example.fliprfastfood.Pages

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fliprfastfood.ViewModel.AuthViewModel
import com.example.fliprfastfood.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
@Composable
fun WelcomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    authViewModel: AuthViewModel
) {
    val auth = FirebaseAuth.getInstance()
    val context = LocalContext.current

    // Set up Google Sign-In
    val googleSignInClient = remember {
        GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(context.getString(R.string.web_client_id)) // Replace with your Web Client ID
                .requestEmail()
                .build()
        )
    }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(Exception::class.java)
            if (account != null) {
                signInWithGoogle(account, auth, navController)
            }
        } catch (e: Exception) {
            // Handle error
            println("Google Sign-In Failed: ${e.message}")
        }
    }

    // Gradient Overlay for Right Half
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFFF5722), Color(0xFFFFC107)),
        startY = 0f,
        endY = 1000f
    )

    // Layout
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Background Image (Left Half)
        Box(modifier = Modifier.fillMaxHeight().width(300.dp)) {
            Image(
                painter = painterResource(id = R.drawable.foodimages), // Replace with your image resource
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Gradient Overlay (Right Half) and Content (overlap image and color)
        Box(
            modifier = Modifier
                .fillMaxSize() // Fill the whole screen
                .background(brush = gradientBrush)
        ) {
            // Content - Overlapping both image and gradient
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.Center), // Center content on the screen
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.imageeight),
                    contentDescription = "Fast Food Logo",
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 16.dp)
                )

                // Title
                Text(
                    text = "Welcome to Fast Food",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                // Subtitle
                Text(
                    text = "Get your favourite meals delivered quickly right to your doorstep",
                    fontSize = 14.sp,
                    color = Color.White,
                    modifier = Modifier.padding(top = 8.dp, bottom = 32.dp),
                    lineHeight = 20.sp
                )

                // Google Sign-In Button
                Button(
                    onClick = {
                        val signInIntent = googleSignInClient.signInIntent
                        launcher.launch(signInIntent)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4))
                ) {
                    Text(
                        text = "Continue with Google",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Sign-In with Email
                TextButton(onClick = { navController.navigate("Signup") }) {
                    Text(
                        text = "Sign Up with Email",
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }
        }
    }
}

// Helper Function: Sign in with Google
private fun signInWithGoogle(account: GoogleSignInAccount, auth: FirebaseAuth, navController: NavController) {
    val credential = GoogleAuthProvider.getCredential(account.idToken, null)
    auth.signInWithCredential(credential)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Navigate to Home on success
                navController.navigate("Home") { popUpTo("welcome") { inclusive = true } }
            } else {
                // Handle errors
                println("Firebase Google Sign-In Failed: ${task.exception?.message}")
            }
        }
}

