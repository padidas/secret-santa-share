package de.padidas.secretsantashare

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing

@SpringBootApplication
@EnableReactiveMongoAuditing
class SecretSantaShareApplication

fun main(args: Array<String>) {
    runApplication<SecretSantaShareApplication>(*args)
}
