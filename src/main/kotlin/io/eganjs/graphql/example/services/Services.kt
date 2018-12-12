package io.eganjs.graphql.example.services

import io.eganjs.graphql.example.models.Character
import io.eganjs.graphql.example.models.Episode
import io.eganjs.graphql.example.queriables.QueriableCharacter
import io.eganjs.graphql.example.repositories.CharactersRepository
import io.eganjs.graphql.example.repositories.HeroesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class QueriableCharactersService(
    private val charactersRepository: CharactersRepository
) {

    fun findById(id: String): QueriableCharacter? =
        runCatching { UUID.fromString(id) }
            .map { uuid ->
                charactersRepository
                    .findById(uuid)
                    .map { id -> QueriableCharacter(id, charactersRepository) }
                    .orElse(null)
            }
            .getOrNull()

    fun findAll(): List<QueriableCharacter> =
        charactersRepository
            .findAll()
            .map { QueriableCharacter(it, charactersRepository) }

    fun save(character: Character): QueriableCharacter =
        QueriableCharacter(
            charactersRepository.save(character),
            charactersRepository
        )
}

@Service
class HeroesService(
    private val charactersRepository: CharactersRepository,
    private val heroesRepository: HeroesRepository
) {

    fun findByEpisode(episode: Episode): QueriableCharacter? =
        heroesRepository
            .findByEpisode(episode)
            .map { QueriableCharacter(it.character, charactersRepository) }
            .orElse(null)

    fun findAll(): List<QueriableCharacter> =
        heroesRepository
            .findAll()
            .map { QueriableCharacter(it.character, charactersRepository) }
}
