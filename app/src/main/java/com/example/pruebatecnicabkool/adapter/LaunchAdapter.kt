package com.example.pruebatecnicabkool.adapter

import android.os.Bundle
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
import com.example.pruebatecnicabkool.databinding.FragmentListBinding
import com.example.pruebatecnicabkool.databinding.ItemLaunchBinding
import com.example.pruebatecnicabkool.ui.fragment.ListFragmentDirections

class LaunchAdapter(private val listBinding: FragmentListBinding) : RecyclerView.Adapter<LaunchAdapter.LaunchViewHolder>() {

    private var _binding : ItemLaunchBinding? = null
    private val binding get() = _binding

    inner class LaunchViewHolder(binding: ItemLaunchBinding?) : RecyclerView.ViewHolder(binding!!.root)

    private val diffCallback = object : DiffUtil.ItemCallback<LaunchListEntry>(){
        override fun areItemsTheSame(oldItem: LaunchListEntry, newItem: LaunchListEntry): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LaunchListEntry, newItem: LaunchListEntry): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this,diffCallback)

    fun submitList(list : List<LaunchListEntry>){
        differ.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        _binding = ItemLaunchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LaunchViewHolder(_binding)
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        val currentLaunch = differ.currentList[position]
        holder.itemView.apply {
            val launchDateText = "${resources.getText(R.string.date_launched)} ${currentLaunch.launchDate}"
            binding!!.txvLaunchDate.text = launchDateText

            binding!!.txvLaunchName.text = currentLaunch.name

            if (currentLaunch.success!!){
                Glide.with(this).load(ContextCompat.getDrawable(this.context,R.drawable.ic_baseline_check_24)).into(binding!!.imvStatus)
            }else{
                Glide.with(this).load(ContextCompat.getDrawable(this.context,R.drawable.ic_baseline_close_24)).into(binding!!.imvStatus)
            }
            setOnClickListener {
                if(listBinding.itemDetailNavContainer != null){
                    val bundle = Bundle()
                    bundle.putString("launchId",currentLaunch.id)
                    listBinding.itemListContainer.findNavController().navigate(R.id.launchDetailFragment, bundle)
                }else{
                    it.findNavController().navigate(ListFragmentDirections.actionListFragmentToLaunchDetailFragment(currentLaunch.id!!))
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}