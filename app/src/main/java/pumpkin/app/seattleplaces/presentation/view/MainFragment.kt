package pumpkin.app.seattleplaces.presentation.view

import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import pumpkin.app.seattleplaces.R
import pumpkin.app.seattleplaces.data.model.PlaceData
import pumpkin.app.seattleplaces.data.model.Resourse
import pumpkin.app.seattleplaces.databinding.FragmentMainBinding
import pumpkin.app.seattleplaces.presentation.view.recyclerViewAdapter.RecyclerAdapter
import pumpkin.app.seattleplaces.presentation.viewModel.MainViewModel
import java.lang.Exception

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var recycler: RecyclerView
    private val viewModel by viewModels<MainViewModel>()


    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.lottieLoading.setAnimation(R.raw.loading)
        setUpBackButton()
        setUpRecycler()
        observerMethod()
        search()
    }


    private fun setUpBackButton() {
        //take the control of BackPressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            val viewToAlert = layoutInflater.inflate(R.layout.alert, null)
            val alert = CustomAlert.createAlert(requireContext(), viewToAlert)
            alert!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alert.show()
            viewToAlert.findViewById<Button>(R.id.exitBtn)
                .setOnClickListener { requireActivity().finishAffinity() }
            viewToAlert.findViewById<Button>(R.id.cancelBtn).setOnClickListener { alert.dismiss() }
        }
    }

    //function to catch click user into recyclerView
    private fun clickDetector(bundle: Bundle) {
        findNavController().navigate(R.id.action_mainFragment_to_mapsFragment, bundle)
    }

    //function to configure the RecyclerView
    private fun setUpRecycler() {
        recycler = binding.RecyclerPlaces
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    //this method send the query that the user want to search
    private fun search() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            //to search a final query
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.setPlaceToFind(query.toString())
                //Hide the keyBoard when user submit data
                val hideKeyBoard =
                    requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                hideKeyBoard.hideSoftInputFromWindow(view?.windowToken, 0)
                return true
            }

            //to search while the user is typing
            override fun onQueryTextChange(query: String?): Boolean {
                viewModel.setPlaceToFind(query.toString())
                return true
            }
        })
    }

    //observe method
    private fun observerMethod() {
        viewModel.getPlaceSearched.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resourse.Loading -> { loading() }
                is Resourse.Success -> { success(response.data.body()!!.meta) }
                is Resourse.Failure -> { failure(response.e) }
            }
        }
    }

    private fun loading() {
        binding.loadingScreen.visibility = View.VISIBLE
    }

    private fun success(ordinaryList: List<PlaceData>) {
        binding.loadingScreen.visibility = View.GONE
        val adapter = RecyclerAdapter(ordinaryList) { clickDetector(it) }
        recycler.adapter = adapter
    }

    private fun failure(e:Exception) {
        binding.loadingScreen.visibility = View.GONE
        Snackbar.make(requireView(), "$e", Snackbar.LENGTH_LONG).show()
    }

}