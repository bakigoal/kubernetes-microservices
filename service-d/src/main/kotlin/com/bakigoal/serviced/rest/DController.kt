package com.bakigoal.serviced.rest

import com.bakigoal.serviced.client.CWebclient
import com.bakigoal.serviced.client.ResourceC
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/v1/d")
class DController {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(DController::class.java)
    }

    @Autowired
    lateinit var cwebClient: CWebclient

    @GetMapping(produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getAll(): Flux<ResourceC> {
        return cwebClient.getAll().doOnNext { logger.info("Produced $it") }
    }
}