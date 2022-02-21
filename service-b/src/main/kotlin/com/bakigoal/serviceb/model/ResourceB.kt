package com.bakigoal.serviceb.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ResourceB (
    @Id
    var id: String,
    var name: String
)