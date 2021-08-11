package id.timtam.core.abstraction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<Holder : RecyclerView.ViewHolder, Data> :
    RecyclerView.Adapter<Holder>() {

    protected val listData = ArrayList<Data>()

    protected abstract val holderInflater: (LayoutInflater, ViewGroup, Boolean) -> Holder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return holderInflater.invoke(LayoutInflater.from(parent.context), parent, false)
    }

    override fun getItemCount(): Int = listData.size

    fun appendData(new: List<Data>) {
        listData.addAll(new)
        notifyDataSetChanged()
    }

    fun clear() {
        listData.clear()
        notifyDataSetChanged()
    }

}