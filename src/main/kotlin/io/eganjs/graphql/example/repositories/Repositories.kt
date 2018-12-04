package io.eganjs.graphql.example.repositories

import io.eganjs.graphql.example.models.Character
import io.eganjs.graphql.example.models.CharacterInput
import io.eganjs.graphql.example.models.Hero
import io.eganjs.graphql.example.models.HeroInput
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CharactersRepository : JpaRepository<Character, UUID>
interface CharactersInputRepository : JpaRepository<CharacterInput, UUID>

interface HeroesRepository : JpaRepository<Hero, UUID>
interface HeroesInputRepository : JpaRepository<HeroInput, UUID>
