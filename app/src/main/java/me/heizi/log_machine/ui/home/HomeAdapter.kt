package me.heizi.log_machine.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import me.heizi.log_machine.BindingHolder
import me.heizi.log_machine.ItemCallback.Companion.itemCallback
import me.heizi.log_machine.databinding.ProjectHomeItemBinding
import me.heizi.log_machine.persistence.entities.Project

class HomeAdapter(
    private val scope:CoroutineScope,
    private val getTags: suspend (Project) ->List<String>,
    private val onItemClick:(Project) ->Unit
):ListAdapter<Project,BindingHolder<ProjectHomeItemBinding>>(
    itemCallback { oldItem, newItem -> oldItem.id == newItem.id }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindingHolder<ProjectHomeItemBinding> { return ProjectHomeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false).let(::BindingHolder) }
    override fun onBindViewHolder(holder: BindingHolder<ProjectHomeItemBinding>, position: Int) = getItem(position).let {
        val tags = scope.async(IO) {getTags(it)}
        holder.binding.root.setOnClickListener {_-> onItemClick(it) }
        holder.binding.proj = it
        scope.launch(IO) { tags.await().let { tags-> launch(Main) {
            holder.binding.tags = tags
        } } }
        Unit
    }
}