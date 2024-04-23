package com.example.testairtickets.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.recyclerview.widget.RecyclerView
import com.example.testairtickets.databinding.CarouselItemBinding
import com.example.testairtickets.databinding.TicketsItemBinding
import com.example.testairtickets.models.tickets.Tickets
import com.example.testairtickets.util.calculateFlightTime
import com.example.testairtickets.util.formatTime
import java.text.NumberFormat


class TicketsAdapter : RecyclerView.Adapter<TicketsAdapter.TicketsHolder>() {

    private var ticketsList = ArrayList<Tickets>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketsHolder {
        val binding: TicketsItemBinding = TicketsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return TicketsHolder(binding)
    }

    override fun getItemCount(): Int {
        return ticketsList.size
    }

    override fun onBindViewHolder(holder: TicketsHolder, position: Int) {
        holder.bind(
            ticketsList[position],
        )
    }

    fun setList(list: List<Tickets>) {
        ticketsList.clear()
        ticketsList.addAll(list)
        notifyDataSetChanged()
    }

    inner class TicketsHolder(private val binding: TicketsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(
            tickets: Tickets
        ) {
            if (tickets.badge != null) {
                binding.badge.text = tickets.badge
                binding.badge.visibility = View.VISIBLE
            }
            binding.ticketsPrice.text = NumberFormat.getInstance().format(tickets.price.value) + " ₽"
            binding.airportArrival.text = tickets.arrival.airport
            binding.airportDeparture.text = tickets.departure.airport
            binding.arrivalTime.text = formatTime(tickets.arrival.date)
            binding.departureTime.text = formatTime(tickets.departure.date)
            if (tickets.hasTransfer) {
                binding.flyTime.text = calculateFlightTime(tickets.departure.date,tickets.arrival.date)
            } else {
                binding.flyTime.text = calculateFlightTime(
                    departureString = tickets.departure.date,
                    arrivalString = tickets.arrival.date
                ) + "/Без пересадок"
            }
        }
    }
}