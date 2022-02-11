package pumpkin.app.seattleplaces.presentation.view

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
import pumpkin.app.seattleplaces.R
import pumpkin.app.seattleplaces.data.DataSource
import pumpkin.app.seattleplaces.presentation.model.Resourse
import pumpkin.app.seattleplaces.data.repository.RepositoryImpl
import pumpkin.app.seattleplaces.databinding.FragmentMainBinding
import pumpkin.app.seattleplaces.presentation.view.recyclerViewAdapter.RecyclerAdapter
import pumpkin.app.seattleplaces.presentation.viewModel.FactoryViewModel
import pumpkin.app.seattleplaces.presentation.viewModel.MainViewModel


class MainFragment : Fragment(R.layout.fragment_main) {
    private lateinit var binding: FragmentMainBinding
    private lateinit var recycler: RecyclerView

    //instantiate the ViewModel
    private val viewModel by viewModels<MainViewModel> {
        FactoryViewModel(RepositoryImpl(DataSource()))
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)
        binding.lottieLoading.setAnimation(R.raw.loading)

        //take the control of BackPressed
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            //poner el cartel para salir de la app
            val viewToAlert = layoutInflater.inflate(R.layout.alert, null)
            val alert = CustomAlert.createAlert(requireContext(), viewToAlert)
            alert!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            alert.show()
            viewToAlert.findViewById<Button>(R.id.exitBtn)
                .setOnClickListener { requireActivity().finishAffinity() }
            viewToAlert.findViewById<Button>(R.id.cancelBtn).setOnClickListener { alert.dismiss() }
        }
        setUpRecycler()
        observerMethod()
        search()
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

    //observe method
    private fun observerMethod() {
        viewModel.getPlaceSearched.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resourse.Loading -> {
                    binding.loadingScreen.visibility = View.VISIBLE
                }

                is Resourse.Success -> {
                    binding.loadingScreen.visibility = View.GONE
                    val ordinaryList = response.data.body()!!.meta
                    val adapter = RecyclerAdapter(ordinaryList) { clickDetector(it) }
                    recycler.adapter = adapter
                }
                is Resourse.Failure -> {
                    binding.loadingScreen.visibility = View.GONE
                    Toast.makeText(requireContext(), "${response.e}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //this method send the query that the user want to search
    private fun search() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            //to search a final query
            override fun onQueryTextSubmit(p0: String?): Boolean {
                viewModel.setPlaceToFind(p0.toString())
                //Hide the keyBoard when user submit data
                val hideKeyBoard =
                    requireActivity().getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                hideKeyBoard.hideSoftInputFromWindow(view?.windowToken, 0)
                return true
            }

            //to search while the user is typing
            override fun onQueryTextChange(p0: String?): Boolean {
                viewModel.setPlaceToFind(p0.toString())
                return true
            }

        })
    }
}