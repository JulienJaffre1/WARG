package com.example.warg.domain.model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.warg.data.core.Status
import com.example.warg.data.repository.WargRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WargViewModelState(
    var account: AccountDomain? = null,
    var token: String? = null,
    var accountSettingsDomain: AccountSettingsDomain? = null,
    var gamesDomain: GamesDomain? = null,
    var isLoading: Boolean = false
)

open class WargViewModel(private val repository: WargRepositoryInterface) : ViewModel() {
    private val _viewState = MutableStateFlow<WargViewModelState>(WargViewModelState())
    var viewState = _viewState.asStateFlow()

    fun accountConnectionSus(mail: String, password: String) = accountConnection(mail, password)
    private fun accountConnection(mail: String, password: String){
        viewModelScope.launch(context = Dispatchers.IO) {
            val flow = repository.accountConnection(mail, password)

            Log.d("WARG", "WARG View")

            flow.collect {
                _viewState.value = WargViewModelState(
                    token = it.data?.token,
                    isLoading = it.status == Status.LOADING
                )
            }
        }
    }
}