package com.pdpano.ainukafka.config

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

class KafkaConfiguration {
    companion object {
        inline fun <K, reified V: Any> initConsumer(groupName: String, customProperties: Map<String, String> = emptyMap()): KafkaConsumer<K, V> {
            val properties = Properties()

            properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
            properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupName)
            properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.CLIENT_ID_CONFIG, "$groupName - ${UUID.randomUUID()}")
            properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1")
            properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
            properties.setProperty(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, GsonDeserializer::class.java.name)
            properties.setProperty(GsonDeserializer.TYPE_CONFIG, V::class.java.name)

            properties.putAll(customProperties)
            return KafkaConsumer<K, V>(properties)
        }

        fun <K, V> initProducer(): org.apache.kafka.clients.producer.KafkaProducer<K, V> {
            val properties = Properties()

            properties.setProperty(org.apache.kafka.clients.producer.ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
            properties.setProperty(org.apache.kafka.clients.producer.ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.java.name)
            properties.setProperty(org.apache.kafka.clients.producer.ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer::class.java.name)

            return org.apache.kafka.clients.producer.KafkaProducer<K, V>(properties)
        }
    }
}