package com.bakigoal.serviceb.event

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFutureCallback


@Component
class EventPublisher(
    @Autowired val kafkaTemplate: KafkaTemplate<String, ResourceBChange>
) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(EventPublisher::class.java)
    }

    @Value(value = "\${kafka.topics.resource_b_changed}")
    private val topic: String? = null

    fun publishResourceBChange(action: ActionEnum, id: String) {
        logger.info("Sending Kafka message {} for Id: {}", action, id)

        kafkaTemplate
            .send(topic!!, id, createMessage(action, id))
            .addCallback(sendStatusCallback(id))
    }

    private fun sendStatusCallback(id: String) =
        object : ListenableFutureCallback<SendResult<String, ResourceBChange>> {
            override fun onSuccess(result: SendResult<String, ResourceBChange>?) {
                logger.info("Successfully sent message $id")
            }

            override fun onFailure(ex: Throwable) {
                logger.info("Error while sending message $id")
            }
        }

    private fun createMessage(action: ActionEnum, id: String) = ResourceBChange(
        ResourceBChange::class.java.typeName,
        action.toString(),
        id
    )

}

enum class ActionEnum {
    CREATED, UPDATED, DELETED
}