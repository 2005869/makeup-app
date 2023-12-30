package com.fv2005869.makeupapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.fv2005869.makeupapp.MakeUpApplication
import com.fv2005869.makeupapp.data.MakeUpRepository
import com.fv2005869.makeupapp.model.Product
import com.fv2005869.makeupapp.model.ProductItem
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface MakeUpUiState {
    data class Success(val products: List<ProductItem>) : MakeUpUiState
//    data class Success(val products: List<Product>) : MakeUpUiState
    object Loading : MakeUpUiState
    object Error : MakeUpUiState
}

class MakeUpViewModel(private val makeupRepository: MakeUpRepository) : ViewModel() {


    var makeupUiState: MakeUpUiState by mutableStateOf(MakeUpUiState.Loading)
        private set


    init {
        getMakeUpProducts()
    }

    fun getMakeUpProducts() {
        viewModelScope.launch {
            makeupUiState = try {
                val listResult = makeupRepository.getMakeUpProducts()
                MakeUpUiState.Success(listResult)
            } catch (e: IOException) {
                Log.e("ErrorX", "${e.message}")
                MakeUpUiState.Error

            } catch (e: Exception) {
                Log.e("ErrorEx", "${e.message}")
                MakeUpUiState.Error
            }

        }
    }

    companion object {
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MakeUpApplication)
                val makeupRepository = application.container.makeupRepository
                MakeUpViewModel(makeupRepository = makeupRepository)
            }
        }
    }

}