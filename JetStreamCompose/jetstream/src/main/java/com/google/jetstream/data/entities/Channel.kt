package com.google.jetstream.data.entities

import com.google.jetstream.data.models.ChannelResponseItem

data class Channel (
    override val id: String,
    override val name: String,
  //  val subtitle: String?,
    override val videoUri: String,
//    val drawableId: Int,
//    val color: Int,
//    var isEnabled: Boolean = true
) : Video

fun ChannelResponseItem.toChannel(): Channel {
    return Channel(
        id,
        name,
        streamUrl,
    )
}