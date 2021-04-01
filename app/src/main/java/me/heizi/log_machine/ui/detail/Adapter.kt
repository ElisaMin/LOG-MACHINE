package me.heizi.log_machine.ui.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import me.heizi.log_machine.Application.Companion.TAG
import me.heizi.log_machine.BindingListAdapter
import me.heizi.log_machine.databinding.LogDetailItemBinding
import me.heizi.log_machine.persistence.entities.Log

class Adapter(
    val onTypeTyped:(Log, String)->Unit,
): BindingListAdapter<Log, LogDetailItemBinding>({ o, n-> o.id == n.id}) {
    override fun getBinding(parent: ViewGroup, layoutInflater: LayoutInflater) =
        LogDetailItemBinding.inflate(layoutInflater, parent, false)
    override fun bind(binding: LogDetailItemBinding, position: Int) {
        val item = getItem(position)
        binding.log = item
        binding.f = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) onTypeTyped(item,(v as EditText).editableText.toString())
        }
    }

    override fun submitList(list: MutableList<Log>?) {
        super.submitList(list)
        android.util.Log.d(TAG, "submitList: called")
    }

}