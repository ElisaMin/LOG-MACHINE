package me.heizi.log_machine

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

object Utils {
}

private const val TAG = "Utils"
class BindingHolder<B:ViewBinding>(val binding:B): RecyclerView.ViewHolder(binding.root)
abstract class BindingListAdapter<T:Any,B:ViewBinding> (areItemSame:(oldItem:T,newItem:T)->Boolean) : ListAdapter<T,BindingHolder<B>> (ItemCallback.itemCallback(areItemSame)) {
    abstract fun getBinding(parent: ViewGroup,layoutInflater: LayoutInflater):B
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingHolder<B> {
        return BindingHolder(getBinding(parent, LayoutInflater.from(parent.context)))
    }

    abstract fun bind(binding:B,position: Int)
    final override fun onBindViewHolder(holder: BindingHolder<B>, position: Int) {
        bind(holder.binding,position)
    }

}
abstract class ItemCallback<T:Any>:DiffUtil.ItemCallback<T>() {
    companion object {
        fun <T:Any> itemCallback(areItemSame:(oldItem:T,newItem:T)->Boolean) = object :ItemCallback<T>() {
            override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = areItemSame(oldItem, newItem)
        }
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean { return oldItem == newItem }

}