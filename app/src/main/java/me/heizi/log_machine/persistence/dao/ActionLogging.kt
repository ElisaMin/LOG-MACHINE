package me.heizi.log_machine.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import me.heizi.log_machine.persistence.entities.Action
import me.heizi.log_machine.persistence.entities.Action.Actions.*
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.persistence.entities.Project

@Dao
interface ActionLogging {

    @Insert
    fun insert(action: Action): Int

    fun insert(action: Action.Actions,parameter:Any?=null){
        when(action) {
            OnlyActivityDestroy,
            SharedViewModelDestroy,
            ApplicationLaunched ->{
                Action(
                    _action = action.id
                ).let(::insert)
            }
            ProjectLaunched -> {
                val project = parameter as Project
                Action(
                    _action = action.id,
                    target = project.id
                ).apply {
                    tags = TODO("id;id;id")

                }.let(::insert)
            }
            LogCalled -> {
                val log = parameter as Log
                Action(
                    _action = action.id,
                    target = log.id
                ).apply {
                    tags = TODO("id;id;id")

                }.let(::insert)
            }
            else -> throw IllegalArgumentException("shouldn't send ${action.name} on logging a action")
        }
    }
}