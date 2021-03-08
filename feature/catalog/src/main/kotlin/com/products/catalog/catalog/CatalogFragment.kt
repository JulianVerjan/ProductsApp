package com.products.catalog.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout.INDICATOR_GRAVITY_STRETCH
import com.google.android.material.tabs.TabLayoutMediator
import com.products.catalog.R
import com.products.catalog.adapter.CatalogPagerAdapter
import com.products.catalog.model.UIState
import com.products.model.Category
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_catalog.emptyAnimation
import kotlinx.android.synthetic.main.fragment_catalog.tabLayoutCategories
import kotlinx.android.synthetic.main.fragment_catalog.toolbar
import kotlinx.android.synthetic.main.fragment_catalog.viewPagerLayoutProducts
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class CatalogFragment : Fragment() {

    private var pagerAdapter: CatalogPagerAdapter? = null
    private val catalogListViewModel: CatalogViewModel by activityViewModels()
    private lateinit var snackBar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setStatesListener()
    }

    private fun setStatesListener() {
        lifecycleScope.launchWhenStarted {
            catalogListViewModel.viewStateFlow.collect { catalog ->
                when (catalog.uiState) {
                    UIState.LOADING -> showLottieView(R.string.loading_animation_path)
                    UIState.CONTENT -> setupViewPager(catalog.result)
                    UIState.ERROR -> {
                        viewPagerLayoutProducts.visibility = GONE
                        showSnackBar(R.string.error)
                        showLottieView(R.string.error_animation_path)
                    }
                    UIState.IDLE -> {
                        // do nothing
                    }
                }
            }
        }
    }

    private fun showLottieView(resource: Int) {
        emptyAnimation.setAnimation(getString(resource))
        emptyAnimation.visibility = VISIBLE
        emptyAnimation.playAnimation()
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

    override fun onResume() {
        super.onResume()
        catalogListViewModel.fetchCatalog()
    }

    private fun setupToolbar() {
        toolbar.title = getString(R.string.catalog_title)
    }

    private fun setupViewPager(catalog: List<Category>) {
        if (catalog.isEmpty().not()) {
            pagerAdapter = CatalogPagerAdapter(
                fragment = this,
                categories = catalog
            )
            viewPagerLayoutProducts.adapter = pagerAdapter
            TabLayoutMediator(tabLayoutCategories, viewPagerLayoutProducts) { tab, position ->
                tab.text = catalog[position].name
            }.attach()
            tabLayoutCategories.setSelectedTabIndicatorGravity(INDICATOR_GRAVITY_STRETCH)
            viewPagerLayoutProducts.adapter?.notifyDataSetChanged()
            emptyAnimation.visibility = GONE
        } else {
            showLottieView(R.string.empty_animation_path)
            showSnackBar(R.string.snack_bar_empty_message)
        }
    }

    override fun onDestroyView() {
        pagerAdapter = null
        super.onDestroyView()
    }
}
