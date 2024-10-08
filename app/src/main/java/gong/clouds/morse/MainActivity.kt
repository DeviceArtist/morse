package gong.clouds.morse

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.PointerType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import gong.clouds.morse.ui.theme.MorseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MorseTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MorseButton() {

}

@SuppressLint("ReturnFromAwaitPointerEventScope")
@Composable
fun LongPressText(
    onInput: () -> Unit,
    onShortPress: () -> Unit,
    onLongPress: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }
    var frame by remember { mutableIntStateOf(0) }
    var time by remember { mutableIntStateOf(0) }
    var releaseTime by remember { mutableIntStateOf(0) }
    LaunchedEffect(frame) {
        delay(10)
        if (isPressed) {
            time++
            releaseTime = -1
        } else {
            if (time > 10) {
                onLongPress()
                time = 0
                releaseTime = 0
            }
            if (time > 2) {
                onShortPress()
                time = 0
                releaseTime = 0
            }
            if (releaseTime > -1) {
                releaseTime++
                if (releaseTime > 50) {
                    onInput()
                }
            }
        }
        frame++
    }
    Button(
        onClick = {},
        modifier = Modifier
            .pointerInput(Unit) {
                while (true) {
                    val pointer =
                        awaitPointerEventScope { awaitPointerEvent(PointerEventPass.Main) }
                    if (pointer.type == PointerEventType.Press) {
                        isPressed = true
                        time += 1
                    } else if (pointer.type == PointerEventType.Release) {
                        isPressed = false
                    }
                }
            }
    ){
        Text(
            text = "Press $time",
            fontSize = 30.sp,
        )
    }
}


@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var txt by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = txt, fontSize = 50.sp, modifier = modifier
        )
        Text(
            text = code, fontSize = 50.sp, modifier = modifier
        )
        Button(
            modifier = Modifier.padding(10.dp),
            onClick = {
                txt=""
                code=""
            }) {
            Text(
                text = "Clear",
                fontSize = 30.sp
            )
        }
        LongPressText(
            onInput = {
                if (code == ".-") {
                    txt += "A"
                }
                if (code == "-...") {
                    txt += "B"
                }
                if (code == "-.-.") {
                    txt += "C"
                }
                if (code == "-..") {
                    txt += "D"
                }
                if (code == ".") {
                    txt += "E"
                }
                if (code == "..-.") {
                    txt += "F"
                }
                if (code == "--.") {
                    txt += "G"
                }
                if (code == "....") {
                    txt += "H"
                }
                if (code == "..") {
                    txt += "I"
                }
                if (code == ".---") {
                    txt += "J"
                }
                if (code == "-.-") {
                    txt += "K"
                }
                if (code == ".-..") {
                    txt += "L"
                }
                if (code == "--") {
                    txt += "M"
                }
                if (code == "-.") {
                    txt += "N"
                }
                if (code == "---") {
                    txt += "O"
                }
                if (code == ".--.") {
                    txt += "P"
                }
                if (code == "--.-") {
                    txt += "Q"
                }
                if (code == ".-.") {
                    txt += "R"
                }
                if (code == "...") {
                    txt += "S"
                }
                if (code == "-") {
                    txt += "T"
                }
                if (code == "..-") {
                    txt += "U"
                }
                if (code == "...-") {
                    txt += "V"
                }
                if (code == ".--") {
                    txt += "W"
                }
                if (code == "-..-") {
                    txt += "X"
                }
                if (code == "-.--") {
                    txt += "Y"
                }
                if (code == "--..") {
                    txt += "Z"
                }
                code = ""
            },
            onShortPress = {
                code += "."
            },
            onLongPress = {
                code += "-"
            }
        )
    }
}