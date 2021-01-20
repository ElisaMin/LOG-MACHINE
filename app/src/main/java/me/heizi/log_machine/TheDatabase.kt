package me.heizi.log_machine

import me.heizi.log_machine.persistence.dao.ActionLogging
import me.heizi.log_machine.persistence.entities.Action
import me.heizi.log_machine.persistence.entities.Log
import me.heizi.log_machine.persistence.entities.Project

/**
 * The database
 *
 * 基本服务的定制者
 */
interface TheDatabase {
    /**
     * As action logging dao
     */
    fun asActionLoggingDao(): ActionLogging


    /**
     * Destroying activity
     *
     * 在Activity摧毁的时候记录本次事件
     */
    suspend fun destroyingActivity() {
        asActionLoggingDao().insert(
            Action.Actions.OnlyActivityDestroy
        )
    }

    /**
     * Checking log
     *
     * 滑动 打开 会出发这个
     */
    suspend fun checkingLog(log: Log){
        asActionLoggingDao().insert(
            Action.Actions.LogCalled,
            parameter = log
        )
    }

    /**
     * Checking project 记录点击事件
     */
    suspend fun checkingProject(project: Project){
        asActionLoggingDao().insert(
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
        asActionLoggingDao().insert(
            Action.Actions.SharedViewModelDestroy
        )

    }

    /**
     * Launched
     *
     * 启动
     */
    suspend fun launched() {
        asActionLoggingDao().insert(
            Action.Actions.ApplicationLaunched
        )
    }
}