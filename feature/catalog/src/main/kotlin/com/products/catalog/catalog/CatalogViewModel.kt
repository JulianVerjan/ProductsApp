package com.products.catalog.catalog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.products.catalog.model.UIState
import com.products.data.usecase.FetchCatalogUseCase
import com.products.network.model.mapper.RepositoryResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val fetchCatalogUseCase: FetchCatalogUseCase
) : ViewModel() {

    private val _viewStateFlow = MutableStateFlow(CatalogViewState())
    val viewStateFlow: StateFlow<CatalogViewState> = _viewStateFlow

    fun fetchCatalog() {
        viewModelScope.launch {
            _viewStateFlow.value = viewStateFlow.value.copy(uiState = UIState.LOADING)
            when (val catalogResult = fetchCatalogUseCase.fetchCatalog()) {
                is RepositoryResult.Success -> {
                    _viewStateFlow.value = CatalogViewState(
                        uiState = if (catalogResult.result != null) UIState.CONTENT else UIState.ERROR,
                        result = catalogResult.result ?: emptyList()
                    )
                }
                else -> {
                    _viewStateFlow.value = CatalogViewState(uiState = UIState.ERROR)
                }
            }
        }
    }
}
