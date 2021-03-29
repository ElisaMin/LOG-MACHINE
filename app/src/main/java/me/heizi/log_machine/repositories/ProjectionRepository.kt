package me.heizi.log_machine.repositories

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import me.heizi.log_machine.persistence.entities.Project

@Dao
interface ProjectionRepository {
    @get:Query("select * from projects")
    val all:Flow<List<Project>>
}