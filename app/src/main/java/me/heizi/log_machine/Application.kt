package me.heizi.log_machine

import android.app.Application
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.heizi.log_machine.persistence.DefaultDatabase

/**
 *空 Application
 */
@HiltAndroidApp
@AndroidEntryPoint
class Application : Application() {
    @DependencyInject.Database
    lateinit var database: DefaultDatabase

    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch(IO) {
            database.launched()
        }
    }

}