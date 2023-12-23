package de.padidas.secretsantashare

import kotlinx.coroutines.flow.Flow
import org.springframework.stereotype.Service

@Service
class IdeaService(val ideaRepository: IdeaRepository) {
    suspend fun addIdea(idea: Idea): Idea? =
        ideaRepository.save(idea)

    suspend fun getIdeasForX(recipientId: String): Flow<Idea> =
        ideaRepository.findByRecipient(recipientId)

    suspend fun deleteIdea(id: String): Unit =
        ideaRepository.deleteById(id)

}