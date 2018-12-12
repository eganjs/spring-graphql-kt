package io.eganjs.graphql.example.resolvers

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import io.eganjs.graphql.example.models.Episode
import io.eganjs.graphql.example.models.SpeciesType
import io.eganjs.graphql.example.queriables.QueriableCharacter
import io.eganjs.graphql.example.services.HeroesService
import io.eganjs.graphql.example.services.QueriableCharactersService
import org.springframework.stereotype.Component

@Component
class Query(
    private val queriableCharactersService: QueriableCharactersService,
    private val heroesService: HeroesService
) : GraphQLQueryResolver {

    fun characters(): List<QueriableCharacter> =
        queriableCharactersService
            .findAll()

    fun character(id: String): QueriableCharacter? =
        queriableCharactersService
            .findById(id)

    fun humans(): List<QueriableCharacter> =
        queriableCharactersService
            .findAll()
            .filter { it.species.type === SpeciesType.Biological }

    fun human(id: String): QueriableCharacter? =
        queriableCharactersService
            .findById(id)
            ?.takeIf { it.species.type === SpeciesType.Biological }

    fun droids(): List<QueriableCharacter> =
        queriableCharactersService
            .findAll()
            .filter { it.species.type === SpeciesType.Synthetic }

    fun droid(id: String): QueriableCharacter? =
        queriableCharactersService
            .findById(id)
            ?.takeIf { it.species.type === SpeciesType.Synthetic }

    fun heroes(): List<QueriableCharacter> =
        heroesService
            .findAll()

    fun hero(episode: Episode?): QueriableCharacter? =
        heroesService
            .findByEpisode(episode ?: Episode.NewHope)

}
