package br.com.fiap.menteleve.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CheckIn::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checkInDao(): CheckInDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "checkin_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
