package com.bakigoal.serviceb.rest

import com.bakigoal.serviceb.model.ResourceB
import com.bakigoal.serviceb.service.ResourceBService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/b")
class ResourceBController(
    @Autowired val bService: ResourceBService
) {

    @GetMapping
    fun getResourceA(): List<ResourceB> {
        return bService.findAll()
    }

    @GetMapping("/{id}")
    fun getResource(@PathVariable id: String): Optional<ResourceB> {
        return bService.findById(id)
    }

    @PostMapping
    fun createResource(@RequestBody resourceB: ResourceB): ResourceB {
        return bService.save(resourceB)
    }

    @PutMapping("/{id}")
    fun updateResource(@PathVariable id: String, @RequestBody resourceB: ResourceB): ResourceB {
        resourceB.id = id
        return bService.update(resourceB)
    }

    @DeleteMapping("/{id}")
    fun createResource(@PathVariable id: String): Optional<ResourceB> {
        val findById = bService.findById(id)
        if (findById.isPresent) {
            bService.deleteById(id)
        }
        return findById
    }
}