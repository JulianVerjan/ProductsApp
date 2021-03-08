package com.products.catalog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.products.catalog.R
import com.products.catalog.adapter.viewholder.ProductViewHolder
import com.products.model.Product
import com.products.network.BuildConfig
import kotlinx.android.synthetic.main.product_item_view.view.productImage
import kotlinx.android.synthetic.main.product_item_view.view.productLayout
import kotlinx.android.synthetic.main.product_item_view.view.productName
import kotlinx.android.synthetic.main.product_item_view.view.productPrice

class ProductListAdapter(private val categories: List<Product>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var listener: ItemClickListener

    interface ItemClickListener {
        fun onItemClicked(view: View, product: Product?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_item_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.itemView.productName.text = categories[position].name
        holder.itemView.productPrice.text = categories[position].salePrice?.amount
        categories[position].url
        val imageUrl = "${BuildConfig.API_BASE_URL}${categories[position].url}"
        holder.itemView.productImage.load(imageUrl) {
            crossfade(true)
            placeholder(R.drawable.ic_image_loading)
            error(R.drawable.image_loader_error)
        }
        holder.itemView.productLayout.setOnClickListener {
            listener.onItemClicked(it, categories[position])
        }
    }

    fun setOnclickLister(listener: ItemClickListener) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return categories.size
    }
}
