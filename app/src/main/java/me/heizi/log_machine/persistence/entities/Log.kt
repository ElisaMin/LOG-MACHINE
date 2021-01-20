package me.heizi.log_machine.persistence.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "logs",
    foreignKeys = [
        ForeignKey(
            entity =  Action::class,
            parentColumns = ["id"],
            childColumns = ["project_id"]
        )
    ]
)
data class Log(
    @PrimaryKey
    val id:Int=0,
    private val project_id :Int,
    val generate_time:String = Date().time.toString(),
    var text:String,
    val description:String,
)