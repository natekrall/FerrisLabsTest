package com.example.test.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "participant_table")
data class Participant (
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val participant : String,
    val project: String,
    val trial: String,
    val correctlyAnswered: Int,
    val incorrectlyAnswered: Int,
    val totalAnswered: Int,
    val accuracyRate: Float,
    val errorRate: Float
)