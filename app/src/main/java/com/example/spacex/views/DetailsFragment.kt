package com.example.spacex.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.spacex.R
import com.example.spacex.databinding.FragmentDetailsBinding
import com.example.spacex.model.LaunchesEntity

class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        //once the user select an item from the list, fillDetails will be called
        //and will draw the full details into every view
        viewModel.singleLaunch.observe(viewLifecycleOwner) { singleLaunch ->
            fillDetails(singleLaunch)
        }


        return binding.root
    }

    /**
     * This function receive a Launch entity as parameter
     * that contains all the data from the API and draw
     * the data into different views
     */
    private fun fillDetails(launch: LaunchesEntity) {
        with(binding) {

            Glide.with(binding.root)
                .load(launch.links.missionPatchSmall)
                .error(R.drawable.astronaut)
                .into(binding.launchImage)

            missionName.text = launch.missionName

            launchSuccess.text = launch.launchSuccess.toString()
            launchYear.text =launch.launchYear
            flightNumber.text = launch.flightNumber.toString()

            rocketName.text = launch.rocket.rocketName
            launchSiteName.text = launch.launchSite.siteName

            dateOfLaunch.text = launch.launchDateUtc
            details.text = launch.details

            websiteBtn.setOnClickListener {
                val uri = Uri.parse(launch.links.articleLink)
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}