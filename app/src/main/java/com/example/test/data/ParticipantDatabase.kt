package com.example.test.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Participant::class], version = 1, exportSchema = false)
abstract class ParticipantDatabase: RoomDatabase() {
    abstract fun participantDao(): ParticipantDao

    companion object{
        @Volatile
        private var INSTANCE: ParticipantDatabase? = null

        fun getDatabase(context: Context): ParticipantDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ParticipantDatabase::class.java,
                    "participant_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}