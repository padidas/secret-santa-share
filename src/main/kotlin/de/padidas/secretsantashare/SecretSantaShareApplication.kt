package de.padidas.secretsantashare

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SecretSantaShareApplication

fun main(args: Array<String>) {
    runApplication<SecretSantaShareApplication>(*args)
}
