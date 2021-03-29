package me.heizi.log_machine.ui.databinding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("android:recyclerview_list")
fun <T:Any> bindListRecyclerView(recyclerView: RecyclerView,list:List<T>)= when(val adapter = recyclerView.adapter) {
    is ListAdapter<*,*> -> (adapter as ListAdapter<T,*>).submitList(list)
    else -> throw IllegalStateException("非ListAdapter!!!!")
}
@BindingAdapter("android:tags_chip")
fun tags(chipGroup: ChipGroup,data:Collection<String>) = data.forEach {
    Chip(chipGroup.context)
        .apply { text = it }
        .let(chipGroup::addView)
}