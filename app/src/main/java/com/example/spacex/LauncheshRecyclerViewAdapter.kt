package com.example.spacex

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import com.bumptech.glide.Glide
import com.imecatro.data.room.model.LaunchRoomEntity
import com.example.spacex.databinding.FragmentItemLaunchBinding
import com.imecatro.domain.launches.model.LaunchDomainModel

/**
 * [RecyclerView.Adapter] that can display a [List].
 */
class LaunchesRecyclerViewAdapter(
    private val onCardClicked: (Int) -> Unit
) : RecyclerView.Adapter<LaunchesRecyclerViewAdapter.ViewHolder>() {

    private val values: MutableList<LaunchDomainModel> = mutableListOf()
    private var itemSelectedNumber : Int = 0

    fun setData(data: List<LaunchDomainModel>) {

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
            rocketName.text = item.rocketName
            launchSiteName.text = item.site
            dateOfLaunch.text = item.dateOfLaunch?.split("T")?.first()

            val url: String = item.imageUrl?: ""

            if (item.launchNumber == itemSelectedNumber) {
                itemView.setBackgroundColor(getColor(itemView.context, R.color.pink))
            } else {
                itemView.setBackgroundColor(getColor(itemView.context, R.color.white))
            }
            itemView.setOnClickListener {

                itemSelectedNumber = item.launchNumber!!
                onCardClicked(item.launchNumber!!)
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
        val dateOfLaunch: TextView = binding.date
        val launchImage: ImageView = binding.launchImage

    }


}