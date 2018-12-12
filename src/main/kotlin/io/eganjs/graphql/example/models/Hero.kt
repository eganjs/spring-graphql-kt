package io.eganjs.graphql.example.models

import java.util.*
import javax.persistence.*

const val HEROES = "heroes"

@Entity
@Table(name = HEROES)
data class Hero(
    @Id
    val id: UUID,
    @Enumerated(EnumType.STRING)
    val episode: Episode,
    @ManyToOne
    val character: Character
)
