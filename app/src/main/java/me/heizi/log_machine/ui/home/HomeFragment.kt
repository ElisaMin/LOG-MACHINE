package me.heizi.log_machine.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import me.heizi.log_machine.R
import me.heizi.log_machine.databinding.HomeFragmentBinding
import me.heizi.log_machine.persistence.entities.Project

/**
 * Home fragment
 *
 * 主页展示
 */
class HomeFragment : Fragment(R.layout.home_fragment) {


    private val viewModel by viewModels<HomeViewModel>()
    private val binding by lazy { HomeFragmentBinding.bind(requireView()) }
    private val adapter by lazy {HomeAdapter(viewModel.viewModelScope,::getTag,::onItemClick)  }

    private suspend fun getTag(project: Project) = emptyList<String>()
    private fun onItemClick(project: Project) {
        Log.i(TAG, "onItemOnClick: $project :clicked")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.adapter = adapter
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.setAddFabOnClick {
            findNavController().navigate()
        }
    }

}