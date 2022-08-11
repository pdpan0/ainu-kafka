package com.pdpano.ainukafka

import com.pdpano.ainukafka.config.KafkaConfiguration
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.UUID

@Suppress("unused")
class NewOrderMain {}

fun main() {
    val producer = KafkaConfiguration.initProducer<String, String>()

    for (i in 0..20) {
        val key = UUID.randomUUID().toString()
        var value = "Thank you for your order! We are processing your order."
        val record = ProducerRecord("AINU_NEW_TATTO_ORDER", key, value)

        producer.send(record) { _, ex ->
            if (ex != null) {
                ex.printStackTrace()
            } else {
                println("Notificação enviada com sucesso!")
            }
        }.get()

        value = "Sending email for confirmation"
        val record2 = ProducerRecord("AINU_SEND_EMAIL", key, value)

        producer.send(record2) { _, ex ->
            if (ex != null) {
                ex.printStackTrace()
            } else {
                println("Email enviado com sucesso!")
            }
        }.get()

        println("Total Rows $i")
    }
}