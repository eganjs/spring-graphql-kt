package io.eganjs.graphql.example

import io.eganjs.graphql.example.models.*
import io.eganjs.graphql.example.repositories.CharactersRepository
import io.eganjs.graphql.example.repositories.HeroesRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

@Profile("dev")
@Configuration
class DevConfiguration {

    @Bean
    fun initialiseData(
        charactersRepository: CharactersRepository,
        heroesRepository: HeroesRepository
    ) = CommandLineRunner {

        // Create character data

        val lukeSkywalker = Character(
            UUID.fromString("241109eb-0f0d-4a7a-8667-604540804f1f"),
            "Luke Skywalker",
            Species.Human,
            "Tatooine",
            setOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val darthVader = Character(
            UUID.fromString("38e06bcc-68b9-4564-92e5-14ab9b3f39c0"),
            "Darth Vader",
            Species.Human,
            "Tatooine",
            setOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val hanSolo = Character(
            UUID.fromString("b0abd1f7-524d-4b24-b754-582e51395f9c"),
            "Han Solo",
            Species.Human,
            null,
            setOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val leiaOrgana = Character(
            UUID.fromString("bf244546-2f3e-4769-8934-3ffdf927f704"),
            "Leia Organa",
            Species.Human,
            "Alderaan",
            setOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val wilhuffTarkin = Character(
            UUID.fromString("0c167c94-7537-4a55-804e-8b28810228de"),
            "Wilhuff Tarkin",
            Species.Human,
            null,
            setOf(Episode.NewHope)
        )
        val c3po = Character(
            UUID.fromString("8b373644-bdcb-4ad3-923c-46fc7b7df7f0"),
            "C-3PO",
            Species.Protocol,
            "Tatooine",
            setOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val aretoo = Character(
            UUID.fromString("5339c3e3-6464-4551-aafd-f046147f9c7e"),
            "R2-D2",
            Species.Astromech,
            null,
            setOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )

        // Persist character data

        fun <T> JpaRepository<T, *>.saveAll(vararg items: T) {
            saveAll(items.asIterable())
            flush()
        }

        charactersRepository.saveAll(
            lukeSkywalker,
            darthVader,
            hanSolo,
            leiaOrgana,
            wilhuffTarkin,
            c3po,
            aretoo
        )

        // Update character data with links

        fun Character.toReference() = CharacterReference(id)

        fun MutableCollection<CharacterReference>.addAll(vararg items: Character) =
            addAll(items.map(Character::toReference))

        lukeSkywalker.friends.addAll(hanSolo, leiaOrgana, c3po, aretoo)
        darthVader.friends.addAll(wilhuffTarkin)
        hanSolo.friends.addAll(lukeSkywalker, leiaOrgana, aretoo)
        leiaOrgana.friends.addAll(lukeSkywalker, hanSolo, c3po, aretoo)
        wilhuffTarkin.friends.addAll(darthVader)
        c3po.friends.addAll(lukeSkywalker, hanSolo, leiaOrgana, aretoo)
        aretoo.friends.addAll(lukeSkywalker, hanSolo, leiaOrgana)

        // Persist links between character data

        charactersRepository.apply {
            save(lukeSkywalker)
            save(darthVader)
            save(hanSolo)
            save(leiaOrgana)
            save(wilhuffTarkin)
            save(c3po)
            save(aretoo)
        }

        // Persist hero data

        heroesRepository.saveAll(
            Hero(
                UUID.fromString("d4317fa3-e9da-4581-8300-52ed9f47c827"),
                Episode.NewHope,
                lukeSkywalker
            ),
            Hero(
                UUID.fromString("d72e5b48-0553-4037-93d4-0d6725ece4d4"),
                Episode.TheEmpireStrikesBack,
                aretoo
            ),
            Hero(
                UUID.fromString("3ed0c3e0-2d31-479a-8862-326dbb397bbb"),
                Episode.ReturnOfTheJedi,
                darthVader
            )
        )
    }
}
