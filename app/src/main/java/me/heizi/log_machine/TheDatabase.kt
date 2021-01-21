package me.heizi.log_machine

import me.heizi.log_machine.persistence.dao.ActionLogging

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
}