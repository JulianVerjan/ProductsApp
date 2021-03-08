package com.products.catalog.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.products.catalog.R
import com.products.catalog.databinding.FragmentProductDetailBinding
import com.products.model.Product
import com.products.network.BuildConfig
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductFragment : Fragment() {

    private var _binding: FragmentProductDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var snackBar: Snackbar
    private val args: ProductFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.product
        showInfo(product)
    }

    private fun showLottieView(resource: Int) {
        with(binding) {
            emptyAnimation.setAnimation(getString(resource))
            emptyAnimation.visibility = VISIBLE
            emptyAnimation.playAnimation()
        }
    }

    private fun showInfo(product: Product?) {
        with(binding) {
            if (product != null) {

                productNameVariable = product.name
                productValueVariable = "${product.salePrice?.amount} ${product.salePrice?.currency}"
                appbar.setExpanded(true, true)
                val imageUrl = "${BuildConfig.API_BASE_URL}${product.url}"
                productImage.load(imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.ic_image_loading)
                    error(R.drawable.image_loader_error)
                }
                toolbar.title = args.categoryName
                toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
                toolbar.setNavigationOnClickListener {
                    findNavController().popBackStack()
                }
                bodyLayout.visibility = VISIBLE
                emptyAnimation.visibility = GONE
            } else {
                showLottieView(R.string.empty_animation_path)
                showSnackBar(R.string.snack_bar_empty_message)
            }
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
