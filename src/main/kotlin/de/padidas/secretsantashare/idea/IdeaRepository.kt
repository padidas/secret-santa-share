package de.padidas.secretsantashare.idea

import kotlinx.coroutines.flow.Flow
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface IdeaRepository : CoroutineCrudRepository<Idea, String> {
    suspend fun findByRecipient(recipient: String): Flow<Idea>
}