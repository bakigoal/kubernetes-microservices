package com.bakigoal.servicec.rest

import com.bakigoal.servicec.model.ResourceC
import com.bakigoal.servicec.service.CService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/v1/c")
class CController {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(CController::class.java)
    }

    @Autowired
    lateinit var cService: CService

    @GetMapping(produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getAll(): Flux<ResourceC> = cService.getAll().doOnNext { logger.info("Server produces: $it") }

}