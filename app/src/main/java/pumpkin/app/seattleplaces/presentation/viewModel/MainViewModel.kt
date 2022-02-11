package pumpkin.app.seattleplaces.presentation.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import pumpkin.app.seattleplaces.presentation.model.Resourse
import pumpkin.app.seattleplaces.data.repository.Repository
import java.lang.Exception

class MainViewModel(private val repo: Repository) : ViewModel() {

    private val _placeToFind = MutableLiveData<String>()
    private val placeToFind: LiveData<String> get() = _placeToFind

    //this function sets the place to search
    fun setPlaceToFind(place: String){
        _placeToFind.value = place
    }

    //init the list with a default value
    init {
        setPlaceToFind("coffe")
    }

    //use the Coroutine Scope to call suspend function from repository
    val getPlaceSearched = placeToFind.distinctUntilChanged().switchMap {
        liveData(Dispatchers.IO){
            emit(Resourse.Loading())
            try {
                emit(repo.getDataPlace(it))
            }catch (e:Exception){
                emit(Resourse.Failure(e))
            }
        }
    }
}