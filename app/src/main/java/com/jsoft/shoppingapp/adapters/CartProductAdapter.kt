package com.jsoft.shoppingapp.adapters

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jsoft.shoppingapp.data.CartProduct
import com.jsoft.shoppingapp.data.Product
import com.jsoft.shoppingapp.databinding.CartProductItemBinding
import com.jsoft.shoppingapp.databinding.SpecialRvItemBinding
import com.jsoft.shoppingapp.helper.getProductPrice

class CartProductAdapter :
    RecyclerView.Adapter<CartProductAdapter.CartProductsViewHolder>() {

    inner class CartProductsViewHolder(val binding: CartProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartProduct: CartProduct) {
            binding.apply {
                if (cartProduct.product.images.isNotEmpty()) {
                    Glide.with(itemView).load(cartProduct.product.images[0])
                        .into(imageCartProduct)
                }
                tvProductCartName.text = cartProduct.product.name
                tvCartProductQuantity.text = cartProduct.quantity.toString()

                val priceAfterPercentage =
                    cartProduct.product.offerPercentage.getProductPrice(cartProduct.product.price)
                tvProductCartPrice.text = "$ ${String.format("%.2f", priceAfterPercentage)}"

                imageCartProductColor.setImageDrawable(
                    ColorDrawable(
                        cartProduct.selectedColor ?: Color.TRANSPARENT
                    )
                )
                tvCartProductSize.text = cartProduct.selectedSize ?: "".also {
                    imageCartProductSize.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
                }

            }
        }

    }


    val diffCallback = object : DiffUtil.ItemCallback<CartProduct>() {
        override fun areItemsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartProduct, newItem: CartProduct): Boolean {
            return oldItem.product.id == newItem.product.id
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartProductsViewHolder {
        return CartProductsViewHolder(
            CartProductItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CartProductsViewHolder, position: Int) {
        val cartProduct = differ.currentList[position]
        holder.bind(cartProduct)

        holder.itemView.setOnClickListener {
            onProductClick?.invoke(cartProduct)
        }

        holder.binding.imgPlus.setOnClickListener {
            onPlusClick?.invoke(cartProduct)
        }
        holder.binding.imgMinus.setOnClickListener {
            onMinusClick?.invoke(cartProduct)
        }
    }


    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    var onProductClick: ((CartProduct) -> Unit)? = null
    var onPlusClick: ((CartProduct) -> Unit)? = null
    var onMinusClick: ((CartProduct) -> Unit)? = null
}

