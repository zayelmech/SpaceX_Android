package com.imecatro.data.network

import com.imecatro.data.network.model.LaunchesEntity
import com.imecatro.domain.launches.model.LaunchDomainModel

fun List<LaunchesEntity>.toDomain(): List<LaunchDomainModel> {
    return map { launchesEntity ->
        LaunchDomainModel(
            missionName = launchesEntity.missionName,
            rocketName = launchesEntity.rocket.rocketName,
            dateOfLaunch = launchesEntity.launchDateUtc,
            imageUrl = launchesEntity.links.missionPatchSmall,
            launchNumber = launchesEntity.flightNumber,
            site = launchesEntity.launchSite.siteName,
            success = launchesEntity.launchSuccess ?: false,
            year = launchesEntity.launchYear ?: "2000",
            details = launchesEntity.details ?: "N/A",
            website = launchesEntity.links.articleLink ?: "spacex.com"
        )
    }
}