package com.example.test.data

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*




@Dao
interface ParticipantDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addParticipant(participant: Participant)

    @Delete
    fun delete(participant: Participant)

    @Query("DELETE FROM participant_table")
    fun nukeAll()
    
    @Query("SELECT * From participant_table ORDER BY id ASC")
    fun readAllData():LiveData<List<Participant>>

    @Query("SELECT * From participant_table WHERE project = :proj ORDER BY id ASC")
    fun readParticipants(proj:String):List<Participant>

    @Query("SELECT * FROM participant_table")
    fun getAll(): Cursor?
}