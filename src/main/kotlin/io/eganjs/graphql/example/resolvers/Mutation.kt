package io.eganjs.graphql.example.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.eganjs.graphql.example.models.Character
import io.eganjs.graphql.example.models.CharacterInput
import io.eganjs.graphql.example.repositories.CharactersInputRepository
import io.eganjs.graphql.example.repositories.CharactersRepository
import org.springframework.stereotype.Component

@Component
class Mutation(
    val charactersInputRepository: CharactersInputRepository,
    val charactersRepository: CharactersRepository
) : GraphQLMutationResolver {

    fun create(input: CharacterInput): Character {
        val id = charactersInputRepository.save(input).id
        return charactersRepository.findById(id).orElse(null)
    }
}
