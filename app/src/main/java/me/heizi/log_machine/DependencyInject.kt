package me.heizi.log_machine

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import me.heizi.log_machine.persistence.DefaultDatabase
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Dependency inject
 *
 * 和类名一致
 */
@Module
object DependencyInject {



    /** 注入[me.heizi.log_machine.DefaultDatabase] */
    @Qualifier
    annotation class Database

    /**
     * Application
     *
     * Singleton object Providers
     */
    @Module
    class Application {
        @Database
        @Provides
        @Singleton
        fun getDatabase(
            @ApplicationContext context: Context
        ) = DefaultDatabase(context)
    }

}