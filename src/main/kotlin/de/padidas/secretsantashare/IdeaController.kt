package de.padidas.secretsantashare

import kotlinx.coroutines.flow.Flow
import org.springframework.web.bind.annotation.*

@RestController
class IdeaController(val ideaRepository: IdeaRepository) {

    @GetMapping("/ideas/{id}")
    suspend fun getIdea(@PathVariable(value = "id") id: String): Idea? =
        ideaRepository.findById(id)

    @PostMapping("/ideas")
    suspend fun addIdea(@RequestBody idea: Idea): Idea? =
        ideaRepository.save(idea)

    @GetMapping("/ideas")
    suspend fun getIdeasForX(@RequestParam(value = "name") name: String): Flow<Idea> =
        ideaRepository.findByRecipient(name)
}