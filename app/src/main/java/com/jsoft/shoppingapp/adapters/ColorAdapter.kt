package com.jsoft.shoppingapp.adapters

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.jsoft.shoppingapp.databinding.ColorRvItemBinding

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ColorsViewHolder>() {

    private var selectedPosition = -1

    inner class ColorsViewHolder(private val binding: ColorRvItemBinding) :
        ViewHolder(binding.root) {
        fun bind(color: Int, position: Int) {
            val imageDrawable = ColorDrawable(color)
            binding.imageColor.setImageDrawable(imageDrawable)
            if (position == selectedPosition) {
                binding.apply {
                    imageShadow.visibility = View.VISIBLE
                    imagePicked.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    imageShadow.visibility = View.INVISIBLE
                    imagePicked.visibility = View.INVISIBLE
                }
            }
        }
    }

    private val diffCallback = object : DiffUtil.ItemCallback<Int>() {
        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    val diff = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsViewHolder {
        return ColorsViewHolder(ColorRvItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ColorsViewHolder, position: Int) {
        val color = diff.currentList[position]
        holder.bind(color, position)

        holder.itemView.setOnClickListener {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition)
            selectedPosition = holder.adapterPosition
            notifyItemChanged(selectedPosition)
            onItemClick?.invoke(color)
        }
    }

    override fun getItemCount(): Int {
        return diff.currentList.size
    }

    var onItemClick: ((Int) -> Unit)? = null
}