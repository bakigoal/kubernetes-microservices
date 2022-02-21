package com.bakigoal.serviceb.repo

import com.bakigoal.serviceb.model.ResourceB
import org.springframework.data.mongodb.repository.MongoRepository

interface ResourceBRepo: MongoRepository<ResourceB, String> {
}