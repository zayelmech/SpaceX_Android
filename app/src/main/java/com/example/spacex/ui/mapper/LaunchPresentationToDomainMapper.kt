package com.example.spacex.ui.mapper

import com.imecatro.domain.launches.model.LaunchDomainModel
import com.example.spacex.ui.model.LaunchPresentationModel


fun LaunchPresentationModel.toDomain(): LaunchDomainModel {
    return LaunchDomainModel(
        missionName = this.missionName,
        rocketName = this.rocketName,
        dateOfLaunch = this.dateOfLaunch,
        imageUrl = this.imageUrl,
        site = this.site,
        success = this.success,
        year = this.year,
        details = this.details,
        website = this.website,
        launchNumber = this.launchNumber
    )
}
