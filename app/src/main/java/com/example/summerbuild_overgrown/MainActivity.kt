package com.example.summerbuild_overgrown

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.example.summerbuild_overgrown.TestRunner
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Run backend integration test
        lifecycleScope.launch {
            Log.d("TEST", "MainActivity started test runner")
            TestRunner.runFullSystemTest()
        }

        setContent {
            // Empty UI for now
        }
    }
}