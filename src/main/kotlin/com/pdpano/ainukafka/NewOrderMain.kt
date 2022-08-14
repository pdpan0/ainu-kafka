package com.pdpano.ainukafka

import com.pdpano.ainukafka.config.KafkaConfiguration
import com.pdpano.ainukafka.domain.Email
import com.pdpano.ainukafka.domain.Order
import org.apache.kafka.clients.producer.ProducerRecord
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.UUID

@Suppress("unused")
class NewOrderMain {}

fun main() {
    val orderProducer = KafkaConfiguration.initProducer<String, Order>()
    val emailProducer = KafkaConfiguration.initProducer<String, Email>()

    for (i in 0..20) {
        val userUuid = UUID.randomUUID().toString()
        val email = UUID.randomUUID().toString() + "@mail.com"

        val record = ProducerRecord("AINU_NEW_TATTO_ORDER", userUuid, Order(
            orderUuid = UUID.randomUUID().toString(),
            userUuid = userUuid,
            price = BigDecimal(Math.random() * 5000 + 1).setScale(2, RoundingMode.DOWN)
        ))

        orderProducer.send(record) { _, ex ->
            if (ex != null) {
                ex.printStackTrace()
            } else {
                println("Notificação enviada com sucesso!")
            }
        }.get()

        val record2 = ProducerRecord("AINU_SEND_EMAIL", userUuid, Email(
            userUuid = userUuid,
            email = email
        ))

        emailProducer.send(record2) { _, ex ->
            if (ex != null) {
                ex.printStackTrace()
            } else {
                println("Email enviado com sucesso!")
            }
        }.get()

        println("Total Rows $i")
    }
}