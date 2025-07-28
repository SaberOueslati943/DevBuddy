package com.saberoueslati.devbuddy.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.saberoueslati.devbuddy.data.dao.TaskDao
import com.saberoueslati.devbuddy.data.local.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class DevBuddyDb : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: DevBuddyDb? = null

        fun getInstance(context: Context): DevBuddyDb {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    DevBuddyDb::class.java,
                    "dev_buddy_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}