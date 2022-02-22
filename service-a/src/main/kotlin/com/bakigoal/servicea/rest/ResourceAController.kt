package com.bakigoal.servicea.rest

import com.bakigoal.servicea.client.ResourceBClient
import com.bakigoal.servicea.model.ResourceB
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/a")
class ResourceAController(
    @Autowired val resourceBClient: ResourceBClient
) {

    @GetMapping
    fun getResourceA(): String {
        return "Hello from service A"
    }

    @GetMapping("/b/{id}")
    fun getResourceB(@PathVariable id: String): ResourceB? {
        return resourceBClient.getById(id)
    }

}