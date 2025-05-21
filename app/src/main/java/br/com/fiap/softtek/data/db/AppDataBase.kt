package br.com.fiap.menteleve.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.fiap.softtek.data.dao.AvaliacaoDao
import br.com.fiap.softtek.data.model.CheckIn
import br.com.fiap.softtek.data.model.Avaliacao
import br.com.fiap.menteleve.data.CheckInDao

@Database(entities = [CheckIn::class, Avaliacao::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun checkInDao(): CheckInDao
    abstract fun avaliacaoDao(): AvaliacaoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "checkin_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
