package me.heizi.log_machine.dependency_inject

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.heizi.log_machine.annotations.Database
import me.heizi.log_machine.persistence.DefaultDatabase
import me.heizi.log_machine.repositories.ActionRepository
import javax.inject.Singleton

/**
 * Singleton scope
 *
 * 伴随Application的生命周期的提供者
 */
@Module
@InstallIn(SingletonComponent::class)
object SingletonScope {
    /**
     * Get database
     *
     * 提供者 没啥好说的 创建一个例子
     */
    @Database
    @Provides
    @Singleton
    fun getDatabase(
        @ApplicationContext context: Context
    ):DefaultDatabase = DefaultDatabase(context)

    /**
     * 获取[ActionRepository]
     */
    @Provides
    @Singleton
    fun getActionRepository(
        @Database database: DefaultDatabase
    ) = ActionRepository(database)




}