package pl.sokolowskibartlomiej.catsrecruitment.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import pl.sokolowskibartlomiej.catsrecruitment.domain.model.PhotoItem
import pl.sokolowskibartlomiej.catsrecruitment.domain.usecases.LoadPhotosUseCase

data class MainScreenUiState(
    val isLoading: Boolean = false,
    val isLoadingFailed: Boolean = false,
    val items: List<PhotoItem> = listOf(),
)

class MainViewModel(private val loadPhotosUseCase: LoadPhotosUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.getAndUpdate { currentState ->
                    currentState.copy(isLoading = true, isLoadingFailed = false)
                }
                val items = loadPhotosUseCase()
                _uiState.getAndUpdate { currentState ->
                    currentState.copy(
                        isLoading = false,
                        isLoadingFailed = items.isEmpty(),
                        items = items
                    )
                }
            } catch (exc: Throwable) {
                _uiState.getAndUpdate { currentState ->
                    currentState.copy(isLoading = false, isLoadingFailed = true)
                }
            }
        }
    }
}