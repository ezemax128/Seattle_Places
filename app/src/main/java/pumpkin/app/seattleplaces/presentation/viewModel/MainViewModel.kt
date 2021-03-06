package pumpkin.app.seattleplaces.presentation.viewModel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import pumpkin.app.seattleplaces.data.model.Resourse
import pumpkin.app.seattleplaces.data.repository.Repository
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {

    private val _placeToFind = MutableLiveData<String>()
    private val placeToFind: LiveData<String> get() = _placeToFind

    //this function sets the place to search
    fun setPlaceToFind(place: String) {
        _placeToFind.value = place
    }

    //init the list with a default value
    init {
        setPlaceToFind("coffee")
    }

    //use the Coroutine Scope to call suspend function from repository
    val getPlaceSearched = placeToFind.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO) {
            emit(Resourse.Loading())
            try {
                emit(repo.getDataPlace(it))
            } catch (e: Exception) {
                emit(Resourse.Failure(e))
            }
        }
    }
}