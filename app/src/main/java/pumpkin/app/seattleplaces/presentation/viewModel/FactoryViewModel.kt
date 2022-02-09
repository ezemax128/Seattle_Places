package pumpkin.app.seattleplaces.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pumpkin.app.seattleplaces.data.repository.Repository

class FactoryViewModel(private val repo:Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java).newInstance(repo)
    }
}