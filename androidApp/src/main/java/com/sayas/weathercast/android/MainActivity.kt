package com.sayas.weathercast.android

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.sayas.weathercast.NapierHelper
import com.sayas.weathercast.ShowClientInfo
import com.sayas.weathercast.model.RestResult
import com.sayas.weathercast.model.WeatherUseCase
import io.github.aakira.napier.LogLevel
import io.github.aakira.napier.Napier

class MainActivity : ComponentActivity() {

    private lateinit var greetingState: MutableState<String>
    private lateinit var hasPermission: MutableState<Boolean>
    private lateinit var context: Context
    private var haveAndroidId = false
    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        hasPermission.value = true
        greetingState.value = ShowClientInfo().getInfo(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            if (Napier.isEnable(LogLevel.DEBUG,"Napier Enabled")) {
                NapierHelper().printLogLevels()
            }

            LaunchedEffect(key1 = true) {
                val result = WeatherUseCase().invoke()
                if (result is RestResult.Success) {
                    Napier.d(result.data.toString(), tag = "response")
                }
            }

            InitStates()
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (haveAndroidId || hasPermission.value) {
                        GreetingView(text = greetingState.value)
                    } else {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier.padding(8.dp),
                                text = "Oops! for acquire device id a Permission required!"
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                            Button(onClick = { grantPermissions() }) {
                                Text(text = "Grant Permission")
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    private fun InitStates() {
        context = LocalContext.current
        haveAndroidId = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
        hasPermission = rememberSaveable {
            mutableStateOf(
                ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED
            )
        }
        greetingState = rememberSaveable {
            if (haveAndroidId || hasPermission.value) {
                mutableStateOf(ShowClientInfo().getInfo(context))
            } else {
                mutableStateOf("Not detected!")
            }
        }
    }


    private fun grantPermissions() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P
        ) {
            permissionLauncher.launch(Manifest.permission.READ_PHONE_STATE)
        } else {
            hasPermission.value = true
        }
    }
}

@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
