package de.padidas.secretsantashare.idea

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class IdeaRouter {
    @Bean
    fun http(ideaHandler: IdeaHandler) = coRouter {
        "/ideas".nest {
            POST("", ideaHandler::addIdea)
            GET("{recipientId}", ideaHandler::getIdeas)
            DELETE("{id}", ideaHandler::removeIdea)
        }
    }
}
