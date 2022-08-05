package com.pdpano.ainukafka

import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class NewOrderMain {
    companion object {
        fun initProperties(): Properties {
            val properties = Properties()

            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
            properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)

            return properties
        }
    }
}

fun main() {
    val properties = NewOrderMain.initProperties()
    val producer = KafkaProducer<String, String>(properties)
    val title = "1"
    val value = "BOM DIA"
    val record = ProducerRecord("AINU_NEW_TATTO_ORDER", title, value)

    producer.send(record) { data, ex ->
        if (ex != null) {
            ex.printStackTrace()
        } else {
            println("""
                Notificação enviada com sucesso! 
                ::particao -> ${data.partition()}
                ::offset -> ${data.offset()}
                ::timestamp -> ${data.timestamp()}
            """.trimIndent())
        }
    }.get()
}