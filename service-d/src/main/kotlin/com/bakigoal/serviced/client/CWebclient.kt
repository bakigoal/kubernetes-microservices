package com.bakigoal.serviced.client

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Component
class CWebclient {

    var webClientBuilder = WebClient.builder()

    fun getAll(): Flux<ResourceC> {
        return webClientBuilder.build().get().uri("http://localhost:4444/v1/c").retrieve()
            .bodyToFlux(
                ResourceC::class.java
            )
    }

}
