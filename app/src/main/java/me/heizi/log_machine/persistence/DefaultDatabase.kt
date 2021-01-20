package me.heizi.log_machine.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import me.heizi.log_machine.TheDatabase

/**
 * Default the database
 *
 * [TheDatabase]的实现
 */
abstract class DefaultDatabase:RoomDatabase(), TheDatabase {
    companion object {
        operator fun invoke(context:Context): DefaultDatabase =
            Room.databaseBuilder(context, DefaultDatabase::class.java,"theDB").build()
    }
}