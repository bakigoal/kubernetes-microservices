package com.bakigoal.servicec.service

import com.bakigoal.servicec.model.ResourceC
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import java.time.Duration

@Service
class CService {
    fun getAll(): Flux<ResourceC> {
        return Flux.fromIterable(
            listOf(
                ResourceC("1", "c1"),
                ResourceC("2", "c2"),
                ResourceC("3", "c3"),
                ResourceC("4", "c4"),
                ResourceC("5", "c5")
            )
        ).delayElements(Duration.ofMillis(1000))
    }
}