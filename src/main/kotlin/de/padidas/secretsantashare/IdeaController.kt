package de.padidas.secretsantashare

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("ideas")
class IdeaController(val ideaService: IdeaService, val ideaRepository: IdeaRepository) {

    @PostMapping
    suspend fun addIdea(@RequestBody idea: Idea): ResponseEntity<Idea> {
        val addedIdea = ideaService.addIdea(idea)

        return if (addedIdea != null) ResponseEntity.ok().body(addedIdea)
        else ResponseEntity.badRequest().build()
    }

    @GetMapping("{recipientId}")
    suspend fun getIdeasForX(@PathVariable recipientId: String): ResponseEntity<Flow<Idea>> {
        val foundIdeas = ideaService.getIdeasForX(recipientId)

        return if (foundIdeas.firstOrNull() != null) ResponseEntity.ok().body(foundIdeas)
        else ResponseEntity.noContent().build()
    }

    @DeleteMapping("{id}")
    suspend fun deleteIdea(@PathVariable id: String): ResponseEntity<Unit> {
        ideaService.deleteIdea(id)
        val deletedIdea = ideaRepository.findById(id)

        return if (deletedIdea == null) ResponseEntity.ok().build()
        else ResponseEntity.unprocessableEntity().build()
    }
}
