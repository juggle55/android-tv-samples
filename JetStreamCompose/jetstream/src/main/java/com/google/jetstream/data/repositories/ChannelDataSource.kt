package com.google.jetstream.data.repositories

import com.google.jetstream.data.entities.toChannel
import com.google.jetstream.data.util.AssetsReader
import com.google.jetstream.data.util.StringConstants
import javax.inject.Inject

class ChannelDataSource @Inject constructor(
    assetsReader: AssetsReader
) {

    private val channelDataReader = CachedDataReader {
        readChannelData(assetsReader, StringConstants.Assets.ChannelList).map {
            it.toChannel()
        }
    }

    suspend fun getChannels() = channelDataReader.read()
}
