package io.eganjs.graphql.example.models

import java.util.*
import javax.persistence.*

const val HERO = "hero"
const val HEROES = "heroes"

@Entity(name = HERO)
@Table(name = HEROES)
data class Hero(
    @Id
    val id: UUID,
    @Enumerated(EnumType.STRING)
    val episode: Episode,
    @ManyToOne
    val character: Character
)

@Entity(name = HERO)
@Table(name = HEROES)
data class HeroInput(
    @Id
    val id: UUID,
    @Enumerated(EnumType.STRING)
    val episode: Episode,
    @ManyToOne
    val character: CharacterReference
)
