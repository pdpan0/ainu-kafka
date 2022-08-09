package com.pdpano.ainukafka

import com.pdpano.ainukafka.config.KafkaConfiguration
import java.time.Duration
import java.util.*

class FraudDetectorService { }

fun main() {
    val consumer = KafkaConfiguration.initConsumer<String, String>(FraudDetectorService::class.simpleName!!)
    consumer.subscribe(Collections.singletonList("AINU_NEW_TATTO_ORDER"))

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))

        if (!records.isEmpty) {
            records.forEach {
                println("""
                    Notificação resgatada! 
                    ${it.key()}
                    ${it.value()}
                """.trimIndent())

                delay(5000)
            }
        }
    }
}

private fun delay(milis: Long) {
    try {
        Thread.sleep(milis)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
