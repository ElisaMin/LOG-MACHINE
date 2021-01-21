package me.heizi.log_machine.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList


/**
 * Action
 *
 * 默认隐私收集
 */
@Entity(tableName = "actions")
data class Action(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
//    @ColumnInfo(typeAffinity = ColumnInfo.TEXT)
    var time:String = Date().time.toString(),
    @ColumnInfo(name = "tags")
    var _tags:String = "",
    @ColumnInfo(name = "action")
    var _action:Int = -3,
    var target:Int = -1
) {


    enum class Actions(val id:Int) {
        SomethingWrongs(-3),
        OnlyActivityDestroy(-2),
        SharedViewModelDestroy(-1),
        ApplicationLaunched(0),
        ProjectLaunched(1),
        LogCalled(2);
        companion object {
            fun findByID(id: Int):Actions = when(id) {
                2->LogCalled
                1->ProjectLaunched
                0->ApplicationLaunched
                -1->SharedViewModelDestroy
                -2->OnlyActivityDestroy
                else -> SomethingWrongs

            }
        }
    }

    var action:Actions
        get() = Actions.findByID(_action)
        set(value) { _action = value.id }

    var tags:List<Int>
        get() = ArrayList<Int>().also {list ->
            _tags.split(';',ignoreCase = true).forEach {
                it
                    .runCatching { it.toInt() }
                    .onSuccess(list::add)
                    .onFailure {
                        TODO("记录并永远不能打开")
                    }
            }
        }
        set(value) {
            _tags = StringBuilder().apply {
                value.forEach {
                    append(it)
                    append(";")
                }
            }.toString()

        }
}