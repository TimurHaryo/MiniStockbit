package id.timtam.ministockbit.ui.toptier.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import id.timtam.core.abstraction.RecyclerViewAdapter
import id.timtam.core.abstraction.ViewHolder
import id.timtam.ministockbit.databinding.ItemTierVolumeBinding
import id.timtam.ministockbit.domain.model.TopTierVolume

class TopTierAdapter : RecyclerViewAdapter<TopTierAdapter.TopTierViewHolder, TopTierVolume>() {
    private var onClick: ((TopTierVolume) -> Unit)? = null

    override val holderInflater: (LayoutInflater, ViewGroup, Boolean) -> TopTierViewHolder
        get() = { layoutInflater, viewGroup, b ->
            TopTierViewHolder(ItemTierVolumeBinding.inflate(layoutInflater, viewGroup, b))
        }

    override fun onBindViewHolder(holder: TopTierViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    fun setOnTierClickListener(action: (TopTierVolume) -> Unit) {
        this.onClick = action
    }

    inner class TopTierViewHolder(itemView: ItemTierVolumeBinding) :
        ViewHolder<ItemTierVolumeBinding, TopTierVolume>(itemView) {

        override fun bind(data: TopTierVolume) {
            with(binding) {
                tvTierName.text = data.name
                tvTierFullName.text = data.fullName
                tvTierPrice.text = data.price

                root.setOnClickListener {
                    onClick?.invoke(data)
                }
            }
        }
    }
}