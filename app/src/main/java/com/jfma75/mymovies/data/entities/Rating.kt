package com.jfma75.mymovies.data.entities

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class Rating(@Id var id: Long = 0, var source: String, var value: String)