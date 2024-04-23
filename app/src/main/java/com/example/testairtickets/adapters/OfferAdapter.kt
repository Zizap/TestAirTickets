package com.example.testairtickets.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testairtickets.databinding.CarouselItemBinding
import com.example.testairtickets.models.offer.Offer
import java.text.NumberFormat


class OfferAdapter : RecyclerView.Adapter<OfferAdapter.OfferHolder>() {

    private var productList = ArrayList<Offer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferHolder {
        val binding: CarouselItemBinding = CarouselItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return OfferHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: OfferHolder, position: Int) {
        holder.bind(
            productList[position],
        )
    }

    fun setList(list: List<Offer>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

    inner class OfferHolder(private val binding: CarouselItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            offer: Offer
        ) {
            binding.nametv.text = offer.title
            binding.towntw.text = offer.town
            binding.pricetv.text = NumberFormat.getInstance().format(offer.offerPrice.value)
            binding.imageView.setImageResource(offer.image ?: com.example.data.R.drawable.offer1)
        }
    }
}
