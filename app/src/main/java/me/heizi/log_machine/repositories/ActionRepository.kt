package me.heizi.log_machine.repositories

import me.heizi.log_machine.TheDatabase
import me.heizi.log_machine.persistence.dao.ActionLogging
import me.heizi.log_machine.persistence.entities.Action
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.persistence.entities.Project

/**
 * Action repository
 *
 * 隐私收集利器
 */
class ActionRepository(
    private val dao:ActionLogging
) {
    constructor(db:TheDatabase):this(db.asActionLoggingDao())

    /**
     * Destroying activity
     *
     * 在Activity摧毁的时候记录本次事件
     */
    suspend fun destroyingActivity() {
        dao.insert(
            Action.Actions.OnlyActivityDestroy
        )
    }

    /**
     * Checking log
     *
     * 滑动 打开 会出发这个
     */
    suspend fun checkingLog(log: Log){
        dao.insert(
            Action.Actions.LogCalled,
            parameter = log
        )
    }

    /**
     * Checking project 记录点击事件
     */
    suspend fun checkingProject(project: Project){
        dao.insert(
            action =  Action.Actions.ProjectLaunched,
            parameter = project
        )
    }

    /**
     * Destroying view model
     *
     * 如名 正在摧毁ViewModel
     */

    suspend fun destroyingViewModel() {
        dao.insert(
            Action.Actions.SharedViewModelDestroy
        )

    }

    /**
     * Launched
     *
     * 启动
     */
    suspend fun launched() {
        dao.insert(
            Action.Actions.ApplicationLaunched
        )
    }
    
}