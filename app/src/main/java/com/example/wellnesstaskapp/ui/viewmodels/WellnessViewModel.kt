package com.example.wellnesstaskapp.ui.viewmodels

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.wellnesstaskapp.data.model.WellnessItem
import com.example.wellnesstaskapp.data.uistate.WellnessUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class WellnessViewModel @Inject constructor() : ViewModel() {
    private val _wellnessUiState = MutableStateFlow(WellnessUiState())
    val wellnessUiState = _wellnessUiState.asStateFlow()

    fun reset() {
        _wellnessUiState.value = WellnessUiState()
    }

    fun onCheckBoxClicking(wellnessItem: WellnessItem) {
        _wellnessUiState.update { currentState ->
            val isChecked = currentState.checkedList.contains(wellnessItem)

            val updatedCheckedList = if (isChecked) {
                currentState.checkedList - wellnessItem
            } else {
                currentState.checkedList + wellnessItem
            }

            currentState.copy(
                checkedList = updatedCheckedList.toMutableStateList(),
                count = if (isChecked) currentState.count - 1 else currentState.count + 1
            )
        }
    }


    fun addTask() {
        _wellnessUiState.update {
            val lastItem = it.wellnessList.last()
            it.copy(
                wellnessList = (it.wellnessList + WellnessItem(number = lastItem.number + 1)).toMutableStateList(),
            )
        }
    }

    fun removeTask(wellnessItem: WellnessItem) {
        _wellnessUiState.update {
            val isChecked = it.checkedList.contains(wellnessItem)
            it.copy(
                wellnessList = (it.wellnessList - wellnessItem).toMutableStateList(),
                count = if (it.count > 0 && isChecked) it.count - 1 else it.count
            )
        }
    }
}
