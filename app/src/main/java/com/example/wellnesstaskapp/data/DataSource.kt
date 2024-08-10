package com.example.wellnesstaskapp.data

import com.example.wellnesstaskapp.data.model.WellnessItem

object DataSource {
    val list: List<WellnessItem> = List(10) {
        WellnessItem(number = it)
    }
}