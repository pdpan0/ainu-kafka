package com.pdpano.ainukafka

import com.pdpano.ainukafka.config.KafkaConfiguration
import org.apache.kafka.clients.producer.ProducerRecord

@Suppress("unused")
class NewOrderMain { }

fun main() {
    val producer = KafkaConfiguration.initProducer<String, String>()

    var title = "1"
    var value = "Thank you for your order! We are processing your order."
    val record = ProducerRecord("AINU_NEW_TATTO_ORDER", title, value)

    producer.send(record) { _, ex ->
        if (ex != null) {
            ex.printStackTrace()
        } else {
            println("Notificação enviada com sucesso!")
        }
    }.get()

    title = "2"
    value = "Sending email for confirmation"
    val record2 = ProducerRecord("AINU_SEND_EMAIL", title, value)

    producer.send(record2) { _, ex ->
        if (ex != null) {
            ex.printStackTrace()
        } else {
            println("Email enviado com sucesso!")
        }
    }.get()
}