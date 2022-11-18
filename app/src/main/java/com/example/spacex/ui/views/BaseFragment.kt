package com.example.spacex.ui.views

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.spacex.ui.viewmodel.LaunchesViewModel

/**It's a Fragment class that works as a parent fragment
 * then all fragments will have these common elements,
 * for this example: viewModel
 * You will notice the "open" keyword
 * all classes in kotlin are "final" by default therefore you
 * need to make them "open" in order to can be inherited
 * **/
open class BaseFragment : Fragment() {

    //ViewModelProvider returns an existing ViewModel if one exists, or it creates a new one if it does not already exist.
    protected val viewModel by lazy {
        ViewModelProvider(requireActivity())[LaunchesViewModel::class.java]
    }
}