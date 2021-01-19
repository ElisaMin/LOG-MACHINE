package me.heizi.log_machine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Activity 如xml所示,Fragment容器
 */
class Activity : AppCompatActivity() {

    companion object {
        val Fragment.parent get() = this.requireActivity() as Activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_container)
    }

    /**
     * On destroy
     * TODO 记录表格
     */
    override fun onDestroy() {
        super.onDestroy()

    }
}