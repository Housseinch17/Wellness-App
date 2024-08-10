package com.example.wellnesstaskapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.wellnesstaskapp.ui.screens.WellNessScreen
import com.example.wellnesstaskapp.ui.theme.WellnessTaskAppTheme
import com.example.wellnesstaskapp.ui.viewmodels.WellnessViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WellnessTaskAppTheme {
                val wellnessViewModel: WellnessViewModel = hiltViewModel()
                val wellnessUiState by wellnessViewModel.wellnessUiState.collectAsStateWithLifecycle()


                WellNessScreen(modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                    count = wellnessUiState.count,
                    addTask = wellnessViewModel::addTask,
                    resetState = wellnessViewModel::reset,
                    wellnessList = wellnessUiState.wellnessList,
                    checkedList = wellnessUiState.checkedList,
                    onChecking = { wellnessViewModel.onCheckBoxClicking(it) },
                    onRemove = { wellnessViewModel.removeTask(it) })

            }
        }
    }
}