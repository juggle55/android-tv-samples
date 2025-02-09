package com.google.jetstream.data.models


import kotlinx.serialization.Serializable

@Serializable
data class ChannelResponseItem (
    val id: String,
    val name: String,
    val streamUrl: String,
)