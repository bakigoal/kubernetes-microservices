package com.bakigoal.servicea.client

import com.bakigoal.servicea.model.ResourceB
import com.bakigoal.servicea.repo.RedisRepo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.util.*

@Component
class ResourceBClient(
    @Autowired val redisRepo: RedisRepo
) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ResourceBClient::class.java)
    }

    fun getById(id: String): ResourceB? {
        logger.info("Getting resource B $id")
        val fromCache = getFromCache(id)
        if (fromCache.isPresent) {
            logger.debug("Successfully retrieved an organization $id from the redis cache: ${fromCache.get()}");
            return fromCache.get()
        }
        logger.debug("Unable to get organization from the redis cache: $id.");
        val b = getFromServiceB(id)
        if (b != null) {
            putToCache(b)
        }
        return b
    }

    private fun putToCache(b: ResourceB) {
        try {
            redisRepo.save(b)
        } catch (e: Exception) {
            logger.error("Unable to cache organization ${b.id} in Redis. Exception $e}")
        }
    }

    private fun getFromCache(id: String): Optional<ResourceB> {
        try {
            return redisRepo.findById(id)
        } catch (e: Exception) {
            logger.error("Error retrieving cache. Exception $e}")
        }
        return Optional.empty()
    }

    private fun getFromServiceB(id: String): ResourceB? {
        val serviceUri = "http://service-b:3333/v1/b/$id"
        val exchange = RestTemplate().exchange(
            serviceUri,
            HttpMethod.GET, null, ResourceB::class.java, id
        )
        return exchange.body
    }
}