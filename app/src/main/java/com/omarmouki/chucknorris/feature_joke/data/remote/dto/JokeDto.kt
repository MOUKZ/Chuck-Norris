package com.omarmouki.chucknorris.feature_joke.data.remote.dto


import com.google.gson.annotations.SerializedName
import com.omarmouki.chucknorris.feature_joke.domin.model.JokeModel

data class JokeDto(
    @SerializedName("icon_url")
    val iconUrl: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("value")
    val value: String
)

fun JokeDto.toJoke(): JokeModel {
    return JokeModel(
        iconUrl = iconUrl,
        content = value
    )

}