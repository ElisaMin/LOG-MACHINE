package me.heizi.log_machine.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import me.heizi.log_machine.R
import me.heizi.log_machine.databinding.HomeFragmentBinding

/**
 * Home fragment
 *
 * 主页展示
 */
class HomeFragment : Fragment(R.layout.home_fragment) {
    val binding by lazy { HomeFragmentBinding.bind(requireView()) }
    val viewModel by viewModels<HomeViewModel>()

}