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
    var _isLogging:Int = 0,
    val generate_time:String = Date().time.toString(),
    var defaultValue:String? = null,
    var color:Int = 0x666666
) {
    var isLogging
        get() = _isLogging == 1
        set(value) {
            _isLogging =  if (value) 1 else 0
        }
}

