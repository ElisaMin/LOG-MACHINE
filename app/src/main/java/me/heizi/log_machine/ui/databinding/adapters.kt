package me.heizi.log_machine.ui.databinding

import android.util.Log
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputLayout


private const val TAG = "adapters"
@BindingAdapter("android:recyclerview_list")
fun <T:Any> bindListRecyclerView(recyclerView: RecyclerView,list:List<T>)= when(val adapter = recyclerView.adapter) {
    is ListAdapter<*,*> -> (adapter as ListAdapter<T,*>).submitList(list)
    null -> Log.d(TAG, "bindListRecyclerView: is null")
    else -> throw IllegalStateException("非ListAdapter!!!!")
}
@BindingAdapter("android:tags_chip")
fun tags(chipGroup: ChipGroup,data:Collection<String>?) = data?.forEach {
    Chip(chipGroup.context)
        .apply { text = it }
        .let(chipGroup::addView)
}
@BindingAdapter("android:focusChanged")
fun focusChanged(view:View,onFocusChangeListener: View.OnFocusChangeListener) {
    view.onFocusChangeListener = onFocusChangeListener
}
@BindingAdapter("android:endIconClicked")
fun inputEndIcon(textfied: TextInputLayout, onClickListener: View.OnClickListener) {
    textfied.setEndIconOnClickListener(onClickListener)
}
@BindingAdapter("android:adapter")
fun rvAdapter(recyclerView: RecyclerView,adapter: RecyclerView.Adapter<*>) {
    recyclerView.adapter = adapter
    Log.d(TAG, "rvAdapter: ${recyclerView.adapter == null}")
}