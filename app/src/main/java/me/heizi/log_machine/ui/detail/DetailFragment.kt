package me.heizi.log_machine.ui.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import me.heizi.log_machine.R
import me.heizi.log_machine.databinding.DetailFragmentBinding

/**
 * Detail fragment
 *
 * 展示项目的详细
 */
class DetailFragment : Fragment(R.layout.detail_fragment) {
    val binding by lazy { DetailFragmentBinding.bind(requireView()) }
    val viewModel by viewModels<DetailViewModel>()
}