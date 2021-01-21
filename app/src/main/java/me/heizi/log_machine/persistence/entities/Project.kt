package me.heizi.log_machine.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * 存放记录项目的地方
 */
@Entity(tableName = "projects")
data class Project (
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    val name:String,
    val description:String,
    var rank:Double,
    @ColumnInfo(name = "logging")
    var _isLogging:Int,
    val generate_time:String = Date().time.toString(),
    var defaultValue:String? = null,
    var color:String = "666666"
)

