package io.eganjs.graphql.example.repositories

import io.eganjs.graphql.example.models.Character
import io.eganjs.graphql.example.models.Episode
import io.eganjs.graphql.example.models.Hero
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CharactersRepository : JpaRepository<Character, UUID>

interface HeroesRepository : JpaRepository<Hero, UUID> {
    fun findByEpisode(episode: Episode): Optional<Hero>
}
