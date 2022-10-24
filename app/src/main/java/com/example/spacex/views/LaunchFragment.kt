package com.example.spacex.views

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.example.spacex.LaunchesRecyclerViewAdapter
import com.example.spacex.databinding.FragmentLaunchesListBinding
import com.example.spacex.utils.LaunchesListOnBackPressedCallback

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

        viewModel.launchesList.observe(viewLifecycleOwner) { launchesList ->
            recyclerViewAdapter.setData(launchesList)
            viewModel.launchWasSelected(1)//for larger screen I will set and show the details from the first element by default
        }

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