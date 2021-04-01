package me.heizi.log_machine.ui.home

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import me.heizi.log_machine.R
import me.heizi.log_machine.databinding.HomeFragmentBinding
import me.heizi.log_machine.persistence.entities.Project

/**
 * Home fragment
 *
 * 主页展示
 */
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment) {


    private val viewModel by viewModels<HomeViewModel>()
    private val binding by lazy { HomeFragmentBinding.bind(requireView()) }
    private val adapter by lazy {HomeAdapter(viewModel.viewModelScope,::getTag,::onItemClick)  }

    private suspend fun getTag(project: Project) = emptyList<String>()
    private fun onItemClick(project: Project) {
        Log.i(TAG, "onItemOnClick: $project :clicked")
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundleOf("id" to project.id))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
        lifecycleScope.launch(Dispatchers.Default) {
            viewModel.start()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.lifecycleOwner = this
        binding.setAddFabOnClick {
            findNavController().navigate(R.id.action_homeFragment_to_appendFragment)
        }
    }

}