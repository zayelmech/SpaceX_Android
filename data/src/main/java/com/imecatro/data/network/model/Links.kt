package com.imecatro.data.network.model


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("article_link")
    val articleLink: String,
    @SerializedName("flickr_images")
    val flickrImages: List<String>,
    @SerializedName("mission_patch")
    val missionPatch: String,
    @SerializedName("mission_patch_small")
    val missionPatchSmall: String,
    @SerializedName("presskit")
    val presskit: String,
    @SerializedName("reddit_campaign")
    val redditCampaign: String,
    @SerializedName("reddit_launch")
    val redditLaunch: String,
    @SerializedName("reddit_media")
    val redditMedia: String,
    @SerializedName("reddit_recovery")
    val redditRecovery: String,
    @SerializedName("video_link")
    val videoLink: String,
    @SerializedName("wikipedia")
    val wikipedia: String,
    @SerializedName("youtube_id")
    val youtubeId: String
)