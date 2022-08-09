package com.pdpano.ainukafka

import com.pdpano.ainukafka.config.KafkaConfiguration
import java.time.Duration
import java.util.regex.Pattern

class LogService { }

fun main() {
    val consumer = KafkaConfiguration.initConsumer<String, String>(LogService::class.simpleName!!)
    consumer.subscribe(Pattern.compile("AINU.*"))

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))

        if (!records.isEmpty) {
            records.forEach {
                println("Log ${it.topic()}")
            }
        }
    }
}