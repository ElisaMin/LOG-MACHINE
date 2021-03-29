package me.heizi.log_machine.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import me.heizi.log_machine.TheDatabase
import me.heizi.log_machine.persistence.entities.Action
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.persistence.entities.Project
import me.heizi.log_machine.persistence.entities.Tags
import androidx.room.Database as RoomDB

/**
 * Default the database
 *
 * [TheDatabase]的实现
 */
@RoomDB(
    entities = [
        Action::class,
        Log::class,
        Project::class,
        Tags.Project::class,
        Tags.Log::class,
        Tags.Tag::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DefaultDatabase:RoomDatabase(), TheDatabase {
    companion object {
        operator fun invoke(context:Context): DefaultDatabase =
            Room.databaseBuilder(context, DefaultDatabase::class.java,"theDB").build()
    }
}