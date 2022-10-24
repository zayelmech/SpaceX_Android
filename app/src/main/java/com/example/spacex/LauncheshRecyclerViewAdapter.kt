package com.example.spacex

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import com.bumptech.glide.Glide
import com.example.spacex.databinding.FragmentItemLaunchBinding
import com.example.spacex.model.LaunchesEntity

/**
 * [RecyclerView.Adapter] that can display a [List].
 */
class LaunchesRecyclerViewAdapter(
    private val onCardClicked: (Int) -> Unit
) : RecyclerView.Adapter<LaunchesRecyclerViewAdapter.ViewHolder>() {

    private val values: MutableList<LaunchesEntity> = mutableListOf()
    private var itemSelectedNumber : Int = 0

    fun setData(data: List<LaunchesEntity>) {

        values.clear()
        values.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemLaunchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]

        with(holder) {
            missionName.text = item.missionName
            rocketName.text = item.rocket.rocketName
            launchSiteName.text = item.launchSite.siteName
            dateOfLaunch.text = item.launchDateUtc.split("T").first()

            val url: String = item.links.missionPatchSmall

            if (item.flightNumber == itemSelectedNumber) {
                itemView.setBackgroundColor(getColor(itemView.context, R.color.pink))
            } else {
                itemView.setBackgroundColor(getColor(itemView.context, R.color.white))
            }
            itemView.setOnClickListener {

                itemSelectedNumber = item.flightNumber
                onCardClicked(item.flightNumber)
                notifyDataSetChanged()
            }
            Glide.with(itemView)
                .load(url)
                .error(R.drawable.astronaut)
                .dontAnimate()
                .into(launchImage)
        }
    }

    override fun getItemCount(): Int = values.size


    inner class ViewHolder(binding: FragmentItemLaunchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val missionName: TextView = binding.missionName
        val rocketName: TextView = binding.rocketName
        val launchSiteName: TextView = binding.launchSiteName
        val dateOfLaunch: TextView = binding.dateOfLaunch
        val launchImage: ImageView = binding.launchImage

    }


}