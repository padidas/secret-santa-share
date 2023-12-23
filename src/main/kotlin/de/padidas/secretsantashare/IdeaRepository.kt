package de.padidas.secretsantashare

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface IdeaRepository : CoroutineCrudRepository<Idea, String> {
    suspend fun findByRecipient(recipient: String): Flow<Idea>
}