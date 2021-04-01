package me.heizi.log_machine

import me.heizi.log_machine.persistence.dao.ActionLogging
import me.heizi.log_machine.persistence.dao.TagDao
import me.heizi.log_machine.repositories.LogsRepository
import me.heizi.log_machine.repositories.ProjectionRepository

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
    fun asTagDao():TagDao

    val projects:ProjectionRepository
    val logs:LogsRepository
}