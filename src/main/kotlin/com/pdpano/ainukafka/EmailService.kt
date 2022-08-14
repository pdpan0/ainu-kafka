package com.pdpano.ainukafka

import com.pdpano.ainukafka.config.KafkaConfiguration
import com.pdpano.ainukafka.domain.Email
import java.time.Duration
import java.util.*

class EmailService { }

fun main() {
    val consumer = KafkaConfiguration.initConsumer<String, Email>(EmailService::class.simpleName!!)
    consumer.subscribe(Collections.singletonList("AINU_SEND_EMAIL"))

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))

        if (!records.isEmpty) {
            records.forEach {
                println("""
                    Email resgatado! 
                    ${it.key()}
                    ${it.value()}
                    ${it.partition()}
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
