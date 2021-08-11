package id.timtam.core.abstraction

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class ViewHolder<out Type, in Data>(itemView: ViewBinding) :
    RecyclerView.ViewHolder(itemView.root) {

    @Suppress("UNCHECKED_CAST")
    private val _binding: Type = itemView as Type

    protected val binding: Type
        get() {
            try {
                return _binding
            } catch (t: Throwable) {
                throw TypeCastException(t.message.toString())
            }
        }

    abstract fun bind(data: Data)

    object None
}