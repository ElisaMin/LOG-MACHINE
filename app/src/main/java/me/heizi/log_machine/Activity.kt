package me.heizi.log_machine

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

/**
 * Activity 如xml所示,Fragment容器
 */
@AndroidEntryPoint
class Activity : AppCompatActivity() {

    val viewModel by viewModels<SharedViewModel>()
    companion object {
        val Fragment.parent get() = this.requireActivity() as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
    }


    override fun onDestroy() {
        super.onDestroy()
        //记录摧毁事件
        lifecycleScope.launch(IO) {
            viewModel.repository.destroyingActivity()
        }


    }
}