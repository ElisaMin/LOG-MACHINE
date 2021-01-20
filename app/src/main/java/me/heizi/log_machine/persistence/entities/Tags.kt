package me.heizi.log_machine.persistence.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

interface Tags {



    @Entity(tableName = "tags")
    data class Tag(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        var name:String
    )
    @Entity(
        tableName = "tag_project",
        foreignKeys =[
            ForeignKey (
                entity = Log::class,
                parentColumns = ["id"],
                childColumns = ["project_id"]
            )
        ]
    )
    data class Project(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val project_id: Int
    )
    @Entity(
        tableName = "tag_log",
        foreignKeys =[
            ForeignKey (
                entity = Log::class,
                parentColumns = ["id"],
                childColumns = ["log_id"]
            )
        ]
    )
    data class Tog(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        val log_id: Int
    )
}