package com.example.unitconverter.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.unitconverter.data.local.LocalModelImpl
import com.example.unitconverter.data.repository.RepositoryImpl
import com.example.unitconverter.ui.screens.AppScreen
import com.example.unitconverter.ui.screens.AppViewModel
import com.example.unitconverter.ui.screens.AppViewModelFactory
import com.example.unitconverter.ui.theme.UnitConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val local = LocalModelImpl()
        val repository = RepositoryImpl(local = local)
        val viewModel by viewModels<AppViewModel> {
            AppViewModelFactory(repository)
        }
        setContent {
            UnitConverterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}