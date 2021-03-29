package me.heizi.log_machine

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

object Utils {
}

class BindingHolder<B:ViewBinding>(val binding:B): RecyclerView.ViewHolder(binding.root)
abstract class ItemCallback<T:Any>:DiffUtil.ItemCallback<T>() {
    companion object {
        fun <T:Any> itemCallback(areItemSame:(oldItem:T,newItem:T)->Boolean) = object :ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = areItemSame(oldItem, newItem)
        }
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean { return oldItem == newItem }

}