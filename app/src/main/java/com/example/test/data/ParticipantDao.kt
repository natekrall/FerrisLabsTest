package com.example.test.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ParticipantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addParticipant(participant: Participant)

    @Delete
    fun delete(participant: Participant)
    
    @Query("SELECT * From participant_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Participant>>
    
}