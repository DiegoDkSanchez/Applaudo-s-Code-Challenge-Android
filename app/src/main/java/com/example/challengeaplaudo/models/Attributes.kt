package com.example.challengeaplaudo.models

import java.io.Serializable

data class Attributes(
    val titles: Titles,
    val synopsis: String,
    val ageRating: String,
    val ageRatingGuide: String,
    val averageRating: String,
    val coverImage: CoverImage?,
    val episodeCount: Int?,
    val episodeLength: Int?,
    val chapterCount: Int?,
    val posterImage: PosterImage?,
    val slug: String?,
    val youtubeVideoId: String?
): Serializable

data class CoverImage(
    val large: String,
    val original: String,
    val small: String,
    val tiny: String
): Serializable

data class PosterImage(
    val large: String,
    val medium: String,
    val original: String,
    val small: String,
    val tiny: String
): Serializable

data class Titles(
    val en: String?,
    val en_jp: String?,
    val ja_jp: String?
): Serializable
