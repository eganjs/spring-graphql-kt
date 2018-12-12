package io.eganjs.graphql.example.queriables

import io.eganjs.graphql.example.models.Character
import io.eganjs.graphql.example.models.Episode
import io.eganjs.graphql.example.models.Species
import io.eganjs.graphql.example.repositories.CharactersRepository
import java.util.*

class QueriableCharacter(
    private val character: Character,
    private val charactersRepository: CharactersRepository
) {
    val id: UUID
        get() = character.id
    val name: String
        get() = character.name
    val species: Species
        get() = character.species
    val homePlanet: String?
        get() = character.homePlanet
    val appearsIn: Set<Episode>
        get() = character.appearsIn
    val friends: Set<QueriableCharacter> by lazy {
        val friendIds = character
            .friends
            .map { it.id }
        charactersRepository
            .findAllById(friendIds)
            .map { QueriableCharacter(it, charactersRepository) }
            .toSet()
    }
}
