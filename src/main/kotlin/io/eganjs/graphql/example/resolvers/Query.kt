package io.eganjs.graphql.example.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import io.eganjs.graphql.example.models.Character
import io.eganjs.graphql.example.models.Episode
import io.eganjs.graphql.example.models.Species.*
import io.eganjs.graphql.example.repositories.CharactersRepository
import io.eganjs.graphql.example.repositories.HeroesRepository
import org.springframework.stereotype.Component
import java.util.*

@Component
class Query(
    private val charactersRepository: CharactersRepository,
    private val heroesRepository: HeroesRepository
) : GraphQLQueryResolver {

    fun heroes(): List<Character> =
        heroesRepository
            .findAll()
            .mapNotNull { charactersRepository.findById(it.character.id).orElse(null) }

    fun hero(episode: Episode?): Character? {
        val chosenEpisode = (episode ?: Episode.NewHope)
        return heroesRepository
            .findAll()
            .filter { it.episode === chosenEpisode }
            .mapNotNull { charactersRepository.findById(it.id).orElse(null) }
            .firstOrNull()
    }

    fun humans(): List<Character> =
        charactersRepository
            .findAll()
            .filter(Character::isHuman)

    fun human(id: UUID): Character? =
        charactersRepository
            .findById(id)
            .filter(Character::isHuman)
            .orElse(null)

    fun droids(): List<Character> =
        charactersRepository
            .findAll()
            .filter(Character::isDroid)

    fun droid(id: UUID): Character? =
        charactersRepository
            .findById(id)
            .filter(Character::isDroid)
            .orElse(null)

    fun characters(): List<Character> =
        charactersRepository
            .findAll()

    fun character(id: UUID): Character? =
        charactersRepository
            .findById(id)
            .orElse(null)

}

fun Character.isHuman() = species === Human

fun Character.isDroid() = when (species) {
    Protocol, Astromech -> true
    else -> false
}
