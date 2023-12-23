package de.padidas.secretsantashare

import kotlinx.coroutines.flow.firstOrNull
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class IdeaEndpoints {

    @Bean
    fun http(ideaService: IdeaService, ideaRepository: IdeaRepository) = coRouter {

        "/v2/ideas".nest {

            POST("") {
                val idea = it.awaitBody<Idea>()
                val addedIdea = ideaService.addIdea(idea)

                if (addedIdea != null) ServerResponse.ok().bodyValueAndAwait(addedIdea)
                else ServerResponse.badRequest().buildAndAwait()
            }

            GET("{recipientId}") {
                val id = it.pathVariable("recipientId")
                val foundIdeas = ideaService.getIdeasForX(id)

                if (foundIdeas.firstOrNull() != null) ServerResponse.ok().bodyAndAwait(foundIdeas)
                else ServerResponse.noContent().buildAndAwait()
            }

            DELETE("{id}") {
                val id = it.pathVariable("id")
                ideaService.deleteIdea(id)
                val deletedIdea = ideaRepository.findById(id)

                if (deletedIdea == null) ServerResponse.ok().buildAndAwait()
                else ServerResponse.unprocessableEntity().buildAndAwait()
            }
        }
    }
}
