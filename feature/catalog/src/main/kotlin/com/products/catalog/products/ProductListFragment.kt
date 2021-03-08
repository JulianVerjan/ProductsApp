package com.products.catalog.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.products.catalog.R
import com.products.catalog.adapter.ProductListAdapter
import com.products.catalog.catalog.CatalogFragmentDirections
import com.products.catalog.catalog.CatalogViewModel
import com.products.catalog.model.UIState
import com.products.model.Category
import com.products.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_product_list.emptyAnimationProduct
import kotlinx.android.synthetic.main.fragment_product_list.productListRecyclerView
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductListFragment : Fragment(), ProductListAdapter.ItemClickListener {

    private val catalogListViewModel: CatalogViewModel by activityViewModels()
    private var categoryList: List<Category> = ArrayList()
    private lateinit var snackBar: Snackbar
    private val args: ProductListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatesListener()
    }

    private fun setStatesListener() {
        lifecycleScope.launchWhenStarted {
            catalogListViewModel.viewStateFlow.collect { catalog ->
                when (catalog.uiState) {
                    UIState.LOADING -> showLottieView(R.string.loading_animation_path)
                    UIState.CONTENT -> {
                        categoryList = catalog.result
                        showCatalog()
                    }
                    UIState.ERROR -> {
                        showLottieView(R.string.error_animation_path)
                        productListRecyclerView.visibility = GONE
                    }
                    UIState.IDLE -> {
                        // do nothing
                    }
                }
            }
        }
    }

    private fun showLottieView(resource: Int) {
        emptyAnimationProduct.setAnimation(getString(resource))
        emptyAnimationProduct.visibility = VISIBLE
        emptyAnimationProduct.playAnimation()
    }

    private fun showCatalog() {
        if (categoryList[args.adapterPosition].products.isNullOrEmpty().not()) {
            productListRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            val productAdapter = ProductListAdapter(categoryList[args.adapterPosition].products)
            productAdapter.setOnclickLister(this)
            productListRecyclerView.setHasFixedSize(true)
            productListRecyclerView.layoutManager = GridLayoutManager(
                requireContext(), 2
            )
            productListRecyclerView.adapter = productAdapter
            productListRecyclerView.visibility = VISIBLE
            emptyAnimationProduct.visibility = GONE
        } else {
            showLottieView(R.string.empty_animation_path)
            showSnackBar(R.string.snack_bar_empty_message)
        }
    }

    private fun showSnackBar(message: Int) {
        view?.let {
            snackBar = Snackbar.make(
                it,
                getString(message),
                Snackbar.LENGTH_LONG
            )
            snackBar.show()
        }
    }

    override fun onItemClicked(view: View, product: Product?) {
        product?.let {
            val action =
                CatalogFragmentDirections.actionToProductDetail(args.categoryName, product)
            findNavController().navigate(action)
        }
    }
}
