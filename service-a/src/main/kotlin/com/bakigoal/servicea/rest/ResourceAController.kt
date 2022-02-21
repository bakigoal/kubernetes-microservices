package com.bakigoal.servicea.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/a")
class ResourceAController {

    @GetMapping
    fun getResourceA(): String {
        return "Hello from service A"
    }

}