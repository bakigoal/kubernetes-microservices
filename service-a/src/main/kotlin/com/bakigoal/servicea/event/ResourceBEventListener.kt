package com.bakigoal.servicea.event

import com.bakigoal.servicea.repo.RedisRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ResourceBEventListener(
    @Autowired val redisRepo: RedisRepo
) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ResourceBEventListener::class.java)
    }

    @KafkaListener(
        id = "resource-b-change-model",
        topics = ["\${kafka.consumer.topic.name}"]
    )
    fun organizationChanged(change: ResourceBChange) {
        logger.debug("Received change Event: $change")
        redisRepo.deleteById(change.id)
        logger.debug("Removed cache for resource B ${change.id}")
    }
}