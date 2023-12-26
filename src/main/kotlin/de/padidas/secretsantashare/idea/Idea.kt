package de.padidas.secretsantashare.idea

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document
data class Idea(
    @Id
    val id: String? = null,
    val author: String,
    val recipient: String,
    val content: String,
    @CreatedDate
    var createdAt: Date? = null,
)