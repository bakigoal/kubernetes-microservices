package com.bakigoal.servicea.model

import org.springframework.data.redis.core.RedisHash

@RedisHash("resourceB")
data class ResourceB (
    var id: String,
    var name: String
)