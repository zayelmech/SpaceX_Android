package com.imecatro.data.room

import com.imecatro.data.room.model.LaunchRoomEntity
import com.imecatro.domain.launches.model.LaunchDomainModel


 fun List<LaunchRoomEntity>.toDomain(): List<LaunchDomainModel> {
    return map(LaunchRoomEntity::toDomain)

}

private fun LaunchRoomEntity.toDomain(): LaunchDomainModel {
 return  LaunchDomainModel(
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

fun List<LaunchDomainModel>.toData(): List<LaunchRoomEntity> {
    return map(LaunchDomainModel::toData)
}

 fun LaunchDomainModel.toData(): LaunchRoomEntity {
    return  LaunchRoomEntity(
        missionName = this.missionName?: "unName",
        rocketName = this.rocketName?: "unknown",
        dateOfLaunch = this.dateOfLaunch?: "unknown",
        imageUrl = this.imageUrl,
        site = this.site,
        success = this.success,
        year = this.year,
        details = this.details,
        website = this.website,
        launchNumber = this.launchNumber
    )
}