package com.bakigoal.serviceb.rest

import com.bakigoal.serviceb.model.ResourceB
import com.bakigoal.serviceb.repo.ResourceBRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/v1/b")
class ResourceBController(
    @Autowired val resourceBRepo: ResourceBRepo
) {

    @GetMapping
    fun getResourceA(): List<ResourceB> {
        return resourceBRepo.findAll()
    }

    @GetMapping("/{id}")
    fun getResource(@PathVariable id: String): Optional<ResourceB> {
        return resourceBRepo.findById(id)
    }

    @PostMapping
    fun createResource(@RequestBody resourceB: ResourceB): ResourceB {
        return resourceBRepo.save(resourceB)
    }

    @PutMapping("/{id}")
    fun updateResource(@PathVariable id: String, @RequestBody resourceB: ResourceB): ResourceB {
        resourceB.id = id
        return resourceBRepo.save(resourceB)
    }

    @DeleteMapping("/{id}")
    fun createResource(@PathVariable id: String): Optional<ResourceB> {
        val findById = resourceBRepo.findById(id)
        if (findById.isPresent) {
            resourceBRepo.deleteById(id)
        }
        return findById
    }
}