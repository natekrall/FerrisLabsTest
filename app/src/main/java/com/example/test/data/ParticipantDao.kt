package com.example.test.data

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ParticipantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addParticipant(participant: Participant)


    @Query("DELETE FROM participant_table")
    fun nukeAll()
    
    @Query("SELECT * From participant_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Participant>>

    @Query("SELECT * FROM participant_table ORDER BY id ASC")
    fun readParticipants(): List<Participant>
}