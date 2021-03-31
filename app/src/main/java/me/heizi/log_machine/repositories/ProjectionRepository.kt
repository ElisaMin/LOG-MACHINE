package me.heizi.log_machine.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.persistence.entities.Project

@Dao
interface ProjectionRepository {
    @get:Query("select * from projects")
    val all:Flow<List<Project>>
}


@Dao
interface LogsRepository {
    @get:Query("select * from logs")
    val all:Flow<List<Log>>

    @Query("select * from logs where id = :id")
    fun getLogsByID(id:Int):Flow<List<Log>>

    @Query("update logs set description =:msg where id=:id")
    fun updateLogTitle(id:Int,msg:String)

    @Insert
    fun insert(log: Log):Int
}