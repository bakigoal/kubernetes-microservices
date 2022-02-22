package com.bakigoal.servicea.repo

import com.bakigoal.servicea.model.ResourceB
import org.springframework.data.repository.CrudRepository

interface RedisRepo: CrudRepository<ResourceB, String> {
}