package com.example.spacex.ui.views

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.spacex.LaunchesRecyclerViewAdapter
import com.example.spacex.R
import com.example.spacex.database.LaunchRoomEntity
import com.example.spacex.databinding.FragmentLaunchesListBinding
import com.example.spacex.ui.LaunchesUiState
import com.example.spacex.utils.LaunchesListOnBackPressedCallback
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Launches.
 */
class LaunchFragment : BaseFragment() {

    private var _binding: FragmentLaunchesListBinding? = null
    private val binding get() = _binding!!

    private val recyclerViewAdapter by lazy {
        LaunchesRecyclerViewAdapter(::openDetailsFragment) //init adapter by lazy
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLaunchesListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        // Setting the adapter for the recyclerview that will allow me to set and update my list once the data is ready
        binding.list.apply {
            layoutManager = LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = recyclerViewAdapter
        }


        viewModel.getLaunchesFromApi()
        // I am calling a function from the viewModel to get a list that I will observe
        //once the list get all the data from the API,
        //observer method will allow me to set my data into my recyclerView


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val slidingPaneLayout = binding.slidingPaneLayout

        //next mode will lock swipe in the details pane in order to prevent users from swiping in and out using gestures
        slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED

        // Connect the SlidingPaneLayout to the system back button.
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            LaunchesListOnBackPressedCallback(slidingPaneLayout)
        )
        //The above code uses addCallback(), passing in the viewLifecycleOwner and an instance of
        //LaunchesListOnBackPressedCallback. This callback is only active during the fragment's life cycle.

//        viewModel.launchesList.observe(viewLifecycleOwner) { launchesList ->
//            if (launchesList != null) {
//                recyclerViewAdapter.setData(launchesList)
//                viewModel.launchWasSelected(1)//for larger screen I will set and show the details from the first element by default
//            } else {
//                context?.let {
//                    MaterialAlertDialogBuilder(it)
//                        .setTitle("ERROR")
//                        .setMessage(resources.getString(R.string.error_null_singlelaunch))
//                        .setPositiveButton(resources.getString(R.string.retry)) { dialog, which ->
//                            // Respond to positive button press
//                            viewModel.getLaunchesFromApi()
//                        }
//                        .show()
//                }
//            }
//        }

        // Start a coroutine in the lifecycle scope
        viewLifecycleOwner.lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is LaunchesUiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            showLaunches(uiState.launches)
                        }
                        is LaunchesUiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            showError(uiState.exception)
                        }
                        is LaunchesUiState.Loading -> binding.progressBar.visibility = View.VISIBLE

                        else -> {
                            Log.e("TAG", "onViewCreated: Well you know ")
                        }
                    }


                }

            }
        }

    }

    private fun showError(exception: Throwable) {
        binding.progressBar.visibility = View.GONE

        context?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle("ERROR")
                .setMessage(resources.getString(R.string.error_null_singlelaunch) + "\n${exception.message}")
                .setPositiveButton(resources.getString(R.string.retry)) { dialog, which ->
                    // Respond to positive button press
                    viewModel.getLaunchesFromApi()
                }
                .show()
        }
    }

    private fun showLaunches(launches: List<LaunchRoomEntity>) {
        recyclerViewAdapter.setData(launches)
        viewModel.launchWasSelected(1)//for larger screen I will set and show the details from the first element by default
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * This function display a new fragment if device has an small screen
     * in larger screen will display the details fragment next to list
     */
    private fun openDetailsFragment(id: Int) {
        viewModel.launchWasSelected(id)
        binding.slidingPaneLayout.openPane()

    }

}