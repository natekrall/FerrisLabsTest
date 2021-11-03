package com.example.test.data

import androidx.lifecycle.LiveData
import com.example.test.ExportData

class ParticipantRepository(private val participantDao: ParticipantDao) {
    val readAllData: LiveData<List<Participant>> = participantDao.readAllData()

    suspend fun addParticipant(participant: Participant){
        participantDao.addParticipant(participant)
    }

    fun nukeAll(){
        participantDao.nukeAll()
    }

    fun readParticipants(proj:String):List<Participant>{
        return participantDao.readParticipants(proj)
    }

}