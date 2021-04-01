package me.heizi.log_machine.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.heizi.log_machine.databinding.DetailFragmentBinding
import me.heizi.log_machine.persistence.entities.Log

/**
 * Detail fragment
 *
 * 展示项目的详细
 */
@AndroidEntryPoint
class DetailFragment : BottomSheetDialogFragment() {
    private val binding by lazy { DetailFragmentBinding.inflate(layoutInflater) }
    private val adapter by lazy { Adapter { log, s -> viewModel.updateLogTitle(log.id,s) } }
    private val viewModel by viewModels<DetailViewModel>()
    private val projectId by lazy { arguments?.getInt("id") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
        adapter
        var lock = true
        //获取ID
        if (projectId==null || projectId!!<=-1)  AlertDialog
            .Builder(requireContext())
            .setCancelable(false)
            .setTitle("未知错误")
            .setMessage("id不存在! id:$projectId")
            .setCancelable(false)
            .setNegativeButton("退出") {_,_->
                findNavController().navigateUp()
            }.show()
        else {
            lock = false
        }
        //block
        while (lock) {}
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.logsAdapter = adapter
        binding.lifecycleOwner = this
        binding.setEndIconOnClick { v-> lifecycleScope.launch(IO) {
            Log(projectId = projectId!!,text = viewModel.inputText.value,description = "")
                .let { viewModel.saveLog(it) }
                .let {
                    (if (!it) "失败" else withContext(Main){
                        binding.root.hasFocus()
                        binding.root.hasWindowFocus()
                        v.clearFocus()
                        viewModel.inputText.emit("")
                        "成功"
                    }).let { msg:String->
                        Snackbar.make(binding.root,msg,Snackbar.LENGTH_LONG).show()
                    }
                }
        } }
    }

    override fun onStart() {
        super.onStart()
        lifecycleScope.launch(Default) {
            viewModel.start(projectId!!)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root
}

