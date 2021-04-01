package me.heizi.log_machine

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.heizi.log_machine.repositories.ActionRepository
import javax.inject.Inject

/**
 *空 Application
 */
@HiltAndroidApp
class Application : Application() {
    companion object {
        const val TAG = "Application"
    }
    @Inject lateinit var repository: ActionRepository

    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch(IO) {
            repository.launched()
        }
    }

}