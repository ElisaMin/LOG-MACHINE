package me.heizi.log_machine.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import me.heizi.log_machine.persistence.entities.Log as LogEntity

/**
 * Tags
 *
 * 其实这个应该仍在DAO里面的
 */
interface Tags {


    /**
     * Tag
     *
     * 单纯记录Tag的KV表
     */
    @Entity(tableName = "tags")
    data class Tag(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        var name:String
    )

    /**
     * Project
     *
     * 项目用到的Tag来自
     */
    @Entity(
        tableName = "tags_project",
        foreignKeys =[
            ForeignKey (
                entity = LogEntity::class,
                parentColumns = ["id"],
                childColumns = ["project_id"]
            )
        ]
    )
    data class Project(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        @ColumnInfo(name = "project_id",index = true)
        val projectId :Int,
    )

    /**
     * Tog
     *
     * 记录用到的Tag来自
     */
    @Entity(
        tableName = "tags_log",
        foreignKeys =[
            ForeignKey (
                entity = LogEntity::class,
                parentColumns = ["id"],
                childColumns = ["log_id"]
            )
        ]
    )
    data class Log(
        @PrimaryKey(autoGenerate = true)
        val id : Int,
        @ColumnInfo(name = "log_id",index = true)
        val logId: Int
    )
}