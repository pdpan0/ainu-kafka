package com.pdpano.ainukafka.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class KafkaConfiguration {
    companion object {
        fun <K, V> initConsumer(groupName: String): KafkaConsumer<K, V> {
            val properties = Properties()

            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupName)
            properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, "$groupName - ${UUID.randomUUID()}")
            properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1")

            return KafkaConsumer<K, V>(properties)
        }

        fun <K, V> initProducer(): KafkaProducer<K, V> {
            val properties = Properties()

            properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
            properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)

            return KafkaProducer<K, V>(properties)
        }
    }
}