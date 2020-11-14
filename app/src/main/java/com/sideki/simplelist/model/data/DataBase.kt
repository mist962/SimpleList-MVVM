package com.sideki.simplelist.model.data

import android.content.Context
import androidx.room.*
import com.sideki.simplelist.model.entity.Note
import com.sideki.simplelist.utilities.BitmapUtils

@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(BitmapUtils::class)
abstract class DataBase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDataBase(context: Context): DataBase {
            val tempInstace = INSTANCE

            if (tempInstace != null) {
                return tempInstace
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

}