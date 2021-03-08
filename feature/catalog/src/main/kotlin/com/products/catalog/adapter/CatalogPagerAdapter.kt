package com.products.catalog.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.products.catalog.products.ProductListFragment
import com.products.catalog.products.ProductListFragmentArgs
import com.products.model.Category

class CatalogPagerAdapter(
    fragment: Fragment,
    private val categories: List<Category>
) : FragmentStateAdapter(fragment) {

    override fun createFragment(position: Int): Fragment {
        val args = ProductListFragmentArgs(
            adapterPosition = position,
            categoryName = categories[position].name
        )
        val fragment = ProductListFragment()
        fragment.arguments = args.toBundle()
        return fragment
    }

    override fun containsItem(itemId: Long): Boolean {
        categories.forEach { category ->
            if (itemId == category.id.toLong()) {
                return true
            }
        }
        return false
    }

    override fun getItemCount(): Int = categories.size

    override fun getItemId(position: Int): Long = categories[position].id.toLong()
}
