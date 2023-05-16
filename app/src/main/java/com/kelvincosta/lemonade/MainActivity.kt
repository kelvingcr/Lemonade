package com.kelvincosta.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kelvincosta.lemonade.ui.theme.LemonadeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                StepsLimonade(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                )
            }
        }
    }
}

@Preview()
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        StepsLimonade(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun StepsLimonade(modifier: Modifier = Modifier) {

    val steplist = mutableListOf<LemonSteps>()

    steplist.add(LemonSteps(1, "Tap the lemon tree to select a lemom", R.drawable.lemon_tree))
    steplist.add(LemonSteps(2, "Keep tapping the lemon to squeeze it", R.drawable.lemon_squeeze))
    steplist.add(LemonSteps(3, "Tap the lemonade to drink it", R.drawable.lemon_drink))
    steplist.add(LemonSteps(4, "Tap the empty glass to start again", R.drawable.lemon_restart))

    var lemonSteps by remember { mutableStateOf(1) }

    val step = when (lemonSteps) {
        1 -> steplist[0]
        2 -> steplist[1]
        3 -> steplist[2]
        else -> {
            steplist[3]
        }
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = step.text)

        Spacer(modifier = Modifier.height(16.dp))

        Image(painter = painterResource(id = step.image), contentDescription = "tree",
            modifier = Modifier
                .border(1.dp, MaterialTheme.colorScheme.primary)
                .padding(20.dp)
                .clickable {
                    if (lemonSteps < 4) {
                        lemonSteps++
                    } else {
                        lemonSteps = 1
                    }

                })
    }
}

data class LemonSteps(
    val step: Int,
    val text: String,
    val image: Int
)