package com.example.wellnesstaskapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wellnesstaskapp.data.model.WellnessItem

@Composable
fun WellNessScreen(
    modifier: Modifier,
    count: Int,
    addTask: () -> Unit,
    resetState: () -> Unit,
    wellnessList: List<WellnessItem>,
    checkedList: List<WellnessItem>,
    onChecking: (WellnessItem) -> Unit,
    onRemove: (WellnessItem) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        WellnessText(modifier = Modifier.fillMaxWidth(), count = count)
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Button(
                onClick = addTask,
                colors = ButtonDefaults.buttonColors(Color.Blue)
            ) {
                Text(
                    text = "Add one", lineHeight = 14.sp,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }
            Button(onClick = resetState, colors = ButtonDefaults.buttonColors(Color.Blue)) {
                Text(
                    text = "Reset", lineHeight = 14.sp,
                    style = MaterialTheme.typography.titleMedium.copy(color = Color.White)
                )
            }
        }
        WellnessTaskList(
            modifier = Modifier.fillMaxWidth(),
            wellnessList = wellnessList,
            checkedList = checkedList,
            onChecking = { onChecking(it) },
            onRemove = { onRemove(it) }
        )
    }
}

@Composable
fun WellnessText(modifier: Modifier, count: Int) {
    var text = "You've had $count glass"
    if (count > 0) {
        if (count > 1)
            text += "es"
        Text(text = text, modifier = modifier, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun WellnessTaskList(
    modifier: Modifier,
    wellnessList: List<WellnessItem>,
    checkedList: List<WellnessItem>,
    onChecking: (WellnessItem) -> Unit,
    onRemove: (WellnessItem) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        items(wellnessList) { wellnessItem ->
            WellnessTask(
                modifier = Modifier.fillMaxWidth(),
                wellnessItem = wellnessItem,
                checkedList = checkedList,
                onChecking = {
                    onChecking(wellnessItem)
                },
                onRemove = { onRemove(wellnessItem) })
        }
    }
}

@Composable
fun WellnessTask(
    modifier: Modifier,
    wellnessItem: WellnessItem,
    checkedList: List<WellnessItem>,
    onChecking: (WellnessItem) -> Unit,
    onRemove: (WellnessItem) -> Unit,
) {
    val isChecked = checkedList.contains(wellnessItem)
    Row(
        modifier = modifier,
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = "${wellnessItem.taskLabel} ${wellnessItem.number}"
        )
        Checkbox(
            checked = isChecked, onCheckedChange = {
                onChecking(wellnessItem)
            }, modifier = Modifier
                .size(40.dp)
                .padding(start = 10.dp)
        )
        IconButton(
            onClick = { onRemove(wellnessItem) },
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = "Remove",
            )
        }
    }
}