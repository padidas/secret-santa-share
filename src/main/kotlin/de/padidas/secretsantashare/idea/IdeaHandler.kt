package de.padidas.secretsantashare.idea

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import org.springframework.web.reactive.function.server.ServerResponse.*

@Component
class IdeaHandler(val ideaRepository: IdeaRepository) {

    suspend fun getIdeas(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("recipientId")
        val foundIdeas = ideaRepository.findByRecipient(id)

        return if (foundIdeas.firstOrNull() != null) ok().bodyValueAndAwait(foundIdeas)
        else noContent().buildAndAwait()
    }

    suspend fun addIdea(req: ServerRequest): ServerResponse {
        val idea = req.awaitBody<Idea>()
        try {
            val addedIdea = ideaRepository.save(idea)
            return ok().bodyValueAndAwait(addedIdea)
        } catch(e: Exception) {
            return badRequest().buildAndAwait()
        }
    }

    suspend fun removeIdea(req: ServerRequest): ServerResponse {
        val id = req.pathVariable("id")
        ideaRepository.deleteById(id)
        val deletedIdea = ideaRepository.findById(id)

        return if (deletedIdea == null) ok().buildAndAwait()
        else unprocessableEntity().buildAndAwait()
    }

}