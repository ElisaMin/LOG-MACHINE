package me.heizi.log_machine.ui.append

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.heizi.log_machine.R
import me.heizi.log_machine.databinding.AppendFragmentBinding

/**
 * Append fragment
 *
 * 添加记录
 */
class AppendFragment : BottomSheetDialogFragment() {
    val binding by lazy { AppendFragmentBinding.bind(requireView()) }
    val viewModel by viewModels<AppendViewModel>()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.append_fragment,container,false)



}