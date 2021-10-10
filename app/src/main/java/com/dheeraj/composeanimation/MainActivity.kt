package com.dheeraj.composeanimation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dheeraj.composeanimation.ui.theme.ComposeAnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeAnimationTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    AnimateUI()
                }
            }
        }
    }

    @Composable
    fun AnimateUI() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExecuteAnimation()
        }
    }

    @Composable
    fun ExecuteAnimation() {
        val buttonState = remember { mutableStateOf(ButtonState.IDLE) }

        val transition = updateTransition(targetState = buttonState, label = "finalAnimation")

        val imageSize by transition.animateDp(label = "SizeAnimation") { state ->
            when (state.value) {
                ButtonState.IDLE -> 75.dp
                ButtonState.PRESSED -> 275.dp
            }
        }

        val buttonText = when (buttonState.value) {
            ButtonState.IDLE -> "Click to Expand"
            ButtonState.PRESSED -> "Click to Shrink"
        }

        val textSize = when (buttonState.value) {
            ButtonState.IDLE -> 14.sp
            ButtonState.PRESSED -> 20.sp
        }

        val textMaxLines = when (buttonState.value) {
            ButtonState.IDLE -> 1
            ButtonState.PRESSED -> Int.MAX_VALUE
        }

        Column(modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = {
                    buttonState.value = when (buttonState.value) {
                        ButtonState.IDLE -> ButtonState.PRESSED
                        ButtonState.PRESSED -> ButtonState.IDLE
                    }
                },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = buttonText, color = Color.White)

            }
            Spacer(modifier = Modifier.size(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_mindorks),
                contentDescription = "Sample Image",
                modifier = Modifier
                    .size(imageSize)
                    .fillMaxWidth()
                    .clip(CircleShape),
                alignment = Alignment.Center
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = "Android Online Course for Professionals!\nLearn from the Best in the Industry!\n\n" +
                        "Learn about Dagger, Kotlin, Architectural Components, LifeCycle, LiveData, ViewModel, RxJava" +
                        ", Room Database, Networking with Retrofit, MVVM architecture, Unit Testing, kotlin Coroutines..and many more!\n" +
                        "\n" +
                        "Join Our Course Now!",
                fontSize = textSize,
                maxLines = textMaxLines
            )
        }


    }

    @Preview
    @Composable
    fun DefaultPreview() {
        ComposeAnimationTheme {
            AnimateUI()
        }
    }
}

enum class ButtonState {
    IDLE, PRESSED
}

