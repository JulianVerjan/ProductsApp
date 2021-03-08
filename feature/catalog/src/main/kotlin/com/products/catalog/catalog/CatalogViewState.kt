
package com.products.catalog.catalog

import com.products.catalog.model.UIState
import com.products.model.Category

data class CatalogViewState(
    val uiState: UIState = UIState.IDLE,
    val result: List<Category> = emptyList()
)
