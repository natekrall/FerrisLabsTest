package com.example.test.data

import androidx.lifecycle.LiveData

class ParticipantRepository(private val participantDao: ParticipantDao) {
    val readAllData: LiveData<List<Participant>> = participantDao.readAllData()

    suspend fun addParticipant(participant: Participant){
        participantDao.addParticipant(participant)
    }
}