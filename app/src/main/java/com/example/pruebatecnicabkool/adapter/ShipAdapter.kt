package com.example.pruebatecnicabkool.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebatecnicabkool.R
import com.example.pruebatecnicabkool.data.model.launch.LaunchListEntry
import com.example.pruebatecnicabkool.data.model.ship.Ship
import com.example.pruebatecnicabkool.databinding.ItemLaunchBinding
import com.example.pruebatecnicabkool.databinding.ItemShipBinding
import com.example.pruebatecnicabkool.ui.fragment.MainFragmentDirections

class ShipAdapter : RecyclerView.Adapter<ShipAdapter.ShipViewHolder>() {

    private var _binding : ItemShipBinding? = null
    private val binding get() = _binding

    inner class ShipViewHolder(binding: ItemShipBinding?) : RecyclerView.ViewHolder(binding!!.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Ship>(){
        override fun areItemsTheSame(oldItem: Ship, newItem: Ship): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Ship, newItem: Ship): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list : List<Ship>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShipViewHolder {
        _binding = ItemShipBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ShipViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: ShipViewHolder, position: Int) {
        val currentShip = differ.currentList[position]
        holder.itemView.apply {
            binding!!.txvIdShip.text = currentShip.id
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}