package com.bakigoal.serviceb.service

import com.bakigoal.serviceb.event.ActionEnum
import com.bakigoal.serviceb.event.EventPublisher
import com.bakigoal.serviceb.model.ResourceB
import com.bakigoal.serviceb.repo.ResourceBRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ResourceBService(
    @Autowired val resourceBRepo: ResourceBRepo,
    @Autowired val eventPublisher: EventPublisher
) {

    fun findAll(): List<ResourceB> {
        return resourceBRepo.findAll()
    }

    fun findById(id: String): Optional<ResourceB> {
        return resourceBRepo.findById(id)
    }

    fun save(resourceB: ResourceB): ResourceB {
        return resourceBRepo.save(resourceB)
            .also { eventPublisher.publishResourceBChange(ActionEnum.CREATED, it.id) }
    }

    fun deleteById(id: String) {
        return resourceBRepo.deleteById(id)
            .also { eventPublisher.publishResourceBChange(ActionEnum.DELETED, id) }
    }

    fun update(resourceB: ResourceB): ResourceB {
        return resourceBRepo.save(resourceB)
            .also { eventPublisher.publishResourceBChange(ActionEnum.UPDATED, it.id) }
    }
}
