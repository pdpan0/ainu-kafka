package com.pdpano.ainukafka.config

import com.google.gson.GsonBuilder
import org.apache.kafka.common.serialization.Deserializer
import java.lang.RuntimeException
class GsonDeserializer<T>: Deserializer<T> {
    private var type: Class<T>? = null
    private val gson = GsonBuilder().create()

    companion object {
        const val TYPE_CONFIG: String = "com.pdpano.ainukafka.type_config"
    }

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        val typeName = configs?.get(TYPE_CONFIG).toString()
        try {
            this.type = Class.forName(typeName) as Class<T>
        } catch (e: ClassNotFoundException) {
            throw RuntimeException("Type for deserialization does not exist in the classpath", e)
        }
    }

    override fun deserialize(topic: String?, data: ByteArray?): T {
        return gson.fromJson(String(data!!), type)
    }

}

