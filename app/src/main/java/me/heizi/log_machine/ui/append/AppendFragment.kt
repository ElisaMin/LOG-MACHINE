package me.heizi.log_machine.ui.append

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import me.heizi.log_machine.R
import me.heizi.log_machine.databinding.AppendFragmentBinding

/**
 * Append fragment
 *
 * 添加记录
 */
@AndroidEntryPoint
class AppendFragment : BottomSheetDialogFragment() {
    val binding by lazy { AppendFragmentBinding.bind(requireView()) }
    val viewModel by viewModels<AppendViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.append_fragment,container,false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.setAppend {
            Log.d(TAG, "append: called")
            lifecycleScope.launch(IO) { viewModel.runCatching {
                Log.d(TAG, "append: caching")
                val res = save()
                Log.d(TAG, "onViewCreated: $res")
                launch(Main) {
                    if(res) {
                        findNavController().navigateUp()
                        "成功"
                    } else  {
                        "失败"
                    } .let  { msg->
                        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show()
                    }
                }
            }.onFailure {
                Log.d(TAG, "onViewCreated: exception",it)
                if (it is IllegalArgumentException ) lifecycleScope.launch(Main){
                    Toast.makeText(context,"请检查是否有空",Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "onViewCreated: null point ,showing notice")
                }.start() else {
                    Log.d(TAG, "onViewCreated: exception not match")
                }
            } }
        }
        binding.lifecycleOwner = this
    }


}