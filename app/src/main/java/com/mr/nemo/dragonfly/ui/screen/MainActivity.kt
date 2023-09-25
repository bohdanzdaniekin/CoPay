package com.mr.nemo.dragonfly.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mr.nemo.dragonfly.ui.screen.auth.signin.SignInScreen
import com.mr.nemo.dragonfly.ui.theme.DragonFlyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragonFlyTheme {
                SignInScreen()
            }
        }
    }
}
