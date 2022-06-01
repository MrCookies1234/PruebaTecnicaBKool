package com.example.pruebatecnicabkool.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebatecnicabkool.data.model.crew.Crew
import com.example.pruebatecnicabkool.databinding.ItemCrewBinding

class CrewAdapter : RecyclerView.Adapter<CrewAdapter.CrewViewHolder>() {

    private var _binding : ItemCrewBinding? = null
    private val binding get() = _binding

    inner class CrewViewHolder(binding: ItemCrewBinding?) : RecyclerView.ViewHolder(binding!!.root)

    private val diffCallback = object : DiffUtil.ItemCallback<Crew>(){
        override fun areItemsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Crew, newItem: Crew): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list : List<Crew>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewViewHolder {
        _binding = ItemCrewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CrewViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: CrewViewHolder, position: Int) {
        val currentCrewMember = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(currentCrewMember.image).into(_binding!!.imvCrewMember)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}