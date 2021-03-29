package me.heizi.log_machine.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import me.heizi.log_machine.persistence.entities.Action
import me.heizi.log_machine.persistence.entities.Action.Actions.*
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.persistence.entities.Project

/**
 * 隐私记录利器
 */
@Dao
interface ActionLogging {
    /**
     * Find tags that with log
     *
     * 查找log的tags
     */
    @Query("select tag_id from tags_log where log_id =:logId")
    suspend fun findTagsThatWithLog(logId: Int):List<Int>

    /**
     * Find tags that with project
     *
     * 查找project的tags anyway i gonna write something that i you know that
     */
    @Query("select tag_id from tags_project where project_id =:projectId")
    suspend fun findTagsThatWithProject(projectId: Int):List<Int>

    @Insert
    suspend fun insert(action: Action)

    /**
     * 通过[Action.Actions]插入表格
     *
     * @param action
     * @param parameter
     */
    suspend fun insert(action: Action.Actions,parameter:Any?=null){
        when(action) {
            OnlyActivityDestroy,
            SharedViewModelDestroy,
            ApplicationLaunched ->{
                Action(
                    _action = action.id
                )
            }
            ProjectLaunched -> {
                Action(
                    _action = action.id,
                    target = (parameter as Project).id
                ).apply {
                    tags = findTagsThatWithProject(target)
                }
            }
            LogCalled -> {
                Action(
                    _action = action.id,
                    target = (parameter as Log).id
                ).apply {
                    tags = findTagsThatWithLog(target)
                }
            }
            else -> throw IllegalArgumentException("shouldn't send ${action.name} on logging a action")
        }.let {
            insert(it)
        }
    }
}