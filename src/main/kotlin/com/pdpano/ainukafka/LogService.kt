package com.pdpano.ainukafka

import com.pdpano.ainukafka.config.KafkaConfiguration
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import java.time.Duration
import java.util.regex.Pattern

class LogService { }

fun main() {
    val consumer = KafkaConfiguration.initConsumer<String, String>(
        LogService::class.simpleName!!,
        mapOf(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to StringDeserializer::class.java.name)
    )
    consumer.subscribe(Pattern.compile("AINU.*"))

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))

        if (!records.isEmpty) {
            records.forEach {
                println("${it.topic()} :: ${it.key()} :: ${it.value()}")
            }
        }
    }
}