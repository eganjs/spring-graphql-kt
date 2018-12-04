package io.eganjs.graphql.example.models

import java.util.*
import javax.persistence.*

const val CHARACTER = "character"
const val CHARACTERS = "characters"

@Entity(name = CHARACTER)
@Table(name = CHARACTERS)
data class Character(
    @Id
    val id: UUID,
    val name: String,
    @Enumerated(EnumType.STRING)
    val species: Species,
    val homePlanet: String?,
    @ElementCollection(targetClass = Episode::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val appearsIn: List<Episode>,
    @ManyToMany(fetch = FetchType.LAZY)
    val friends: MutableList<Character> = mutableListOf()
)

@Entity(name = CHARACTER)
@Table(name = CHARACTERS)
data class CharacterInput(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String,
    @Enumerated(EnumType.STRING)
    val species: Species,
    val homePlanet: String?,
    @ElementCollection(targetClass = Episode::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val appearsIn: List<Episode>,
    @ManyToMany(fetch = FetchType.LAZY)
    val friends: MutableList<CharacterReference> = mutableListOf()
)

@Entity(name = CHARACTER)
@Table(name = CHARACTERS)
data class CharacterReference(
    @Id
    val id: UUID
)
