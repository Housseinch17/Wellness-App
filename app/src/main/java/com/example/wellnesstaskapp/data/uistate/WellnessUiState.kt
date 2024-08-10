package com.example.wellnesstaskapp.data.uistate

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import com.example.wellnesstaskapp.data.DataSource
import com.example.wellnesstaskapp.data.model.WellnessItem

data class WellnessUiState(
    val wellnessList: MutableList<WellnessItem> = DataSource.list.toMutableStateList(),
    val checkedList: MutableList<WellnessItem> = mutableStateListOf(),
    val count: Int = 0,
)
