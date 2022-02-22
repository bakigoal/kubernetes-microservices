package com.bakigoal.servicea.config

import com.bakigoal.servicea.event.ResourceBChange
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.serializer.JsonDeserializer


@Configuration
@EnableKafka
class KafkaConsumerConfig {

    @Value(value = "\${kafka.bootstrapAddress}")
    private val kafkaServer: String? = null

    @Value(value = "\${kafka.consumer.groupid}")
    private val kafkaGroupId: String? = null

    @Bean
    fun kafkaListenerContainerFactory(consumerFactory: ConsumerFactory<String, ResourceBChange>) =
        ConcurrentKafkaListenerContainerFactory<String, ResourceBChange>().also {
            it.consumerFactory = consumerFactory
        }

    @Bean
    fun consumerFactory(): DefaultKafkaConsumerFactory<String, ResourceBChange> {
        val jsonDeserializer = JsonDeserializer(ResourceBChange::class.java)
        jsonDeserializer.setRemoveTypeHeaders(false);
        jsonDeserializer.addTrustedPackages("*");
        jsonDeserializer.setUseTypeMapperForKey(true);

        return DefaultKafkaConsumerFactory(
            consumerProps(),
            StringDeserializer(),
            jsonDeserializer
        )
    }

    fun consumerProps() = mapOf(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to kafkaServer,
        ConsumerConfig.GROUP_ID_CONFIG to kafkaGroupId,
        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG to "earliest"
    )
}