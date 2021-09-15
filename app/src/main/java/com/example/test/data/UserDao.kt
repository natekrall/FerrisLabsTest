package com.example.test.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ParticipantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addParticipant(user: Participant)
    
    @Query("SELECT * FROM participant_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Participant>>
}