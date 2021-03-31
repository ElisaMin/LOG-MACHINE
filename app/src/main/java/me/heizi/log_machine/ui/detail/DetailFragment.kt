package me.heizi.log_machine.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import me.heizi.log_machine.BindingListAdapter
import me.heizi.log_machine.databinding.DetailFragmentBinding
import me.heizi.log_machine.databinding.LogDetailItemBinding
import me.heizi.log_machine.persistence.entities.Log

/**
 * Detail fragment
 *
 * 展示项目的详细
 */
class DetailFragment : BottomSheetDialogFragment() {
    private val binding by lazy { DetailFragmentBinding.inflate(layoutInflater) }
    private val adapter by lazy { Adapter { log, s -> viewModel.updateLogTitle(log.id,s) } }
    private val viewModel by viewModels<DetailViewModel>()
    private val projectId by lazy { arguments?.getInt("id") }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            lifecycleScope.launch {
                viewModel.start(projectId!!)
            }
        }
        //block
        while (lock) {}
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.logsAdapter = adapter
        binding.viewModel = viewModel
        binding.setEndIconOnClick { lifecycleScope.launch(IO) {
            Log(projectId = projectId!!,text = viewModel.inputText.value,description = "")
                .let { viewModel.saveLog(it) }
                .let {
                if (!it) Snackbar.make(binding.root,"失败",Snackbar.LENGTH_LONG).show()
            }
        } }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = binding.root
}

class Adapter(
    val onTypeTyped:(Log,String)->Unit,
): BindingListAdapter<Log,LogDetailItemBinding> ({o,n-> o.id == n.id}) {
    override fun getBinding(parent: ViewGroup, layoutInflater: LayoutInflater) = LogDetailItemBinding.inflate(layoutInflater,parent,false)
    override fun bind(binding: LogDetailItemBinding, position: Int) {
        val item = getItem(position)
        binding.log = item
        binding.f = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) onTypeTyped(item,(v as EditText).editableText.toString())
        }
    }


}