package com.pdpano.ainukafka.config

import org.apache.kafka.common.serialization.Serializer

class GsonSerializer<T> : Serializer<T> {
    private val gson: com.google.gson.Gson = com.google.gson.GsonBuilder().create()

    override fun serialize(topic: String?, data: T): ByteArray =
        gson.toJson(data).toByteArray()
}