package io.eganjs.graphql.example.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import io.eganjs.graphql.example.models.Character
import io.eganjs.graphql.example.queriables.QueriableCharacter
import io.eganjs.graphql.example.services.QueriableCharactersService
import org.springframework.stereotype.Component

@Component
class Mutation(
    val queriableCharactersService: QueriableCharactersService
) : GraphQLMutationResolver {

    fun create(input: Character): QueriableCharacter =
        queriableCharactersService
            .save(input)
}
