package io.eganjs.graphql.example.models

import java.util.*
import javax.persistence.*

const val CHARACTERS = "characters"

@Entity
@Table(name = CHARACTERS)
data class Character(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String,
    @Enumerated(EnumType.STRING)
    val species: Species,
    val homePlanet: String?,
    @ElementCollection(targetClass = Episode::class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    val appearsIn: Set<Episode>,
    @ManyToMany(fetch = FetchType.EAGER)
    val friends: MutableSet<CharacterReference> = mutableSetOf()
)

@Entity
@Table(name = CHARACTERS)
data class CharacterReference(
    @Id
    val id: UUID
)
