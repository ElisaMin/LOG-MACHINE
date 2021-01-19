package me.heizi.log_machine.ui.append

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import me.heizi.log_machine.R
import me.heizi.log_machine.databinding.AppendFragmentBinding

/**
 * Append fragment
 *
 * 添加记录
 */
class AppendFragment : Fragment(R.layout.append_fragment) {
    val binding by lazy { AppendFragmentBinding.bind(requireView()) }
    val viewModel by viewModels<AppendViewModel>()

}