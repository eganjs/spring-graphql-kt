package io.eganjs.graphql.example

import io.eganjs.graphql.example.models.*
import io.eganjs.graphql.example.repositories.CharactersInputRepository
import io.eganjs.graphql.example.repositories.HeroesInputRepository
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
        charactersInputRepository: CharactersInputRepository,
        heroesInputRepository: HeroesInputRepository
    ) = CommandLineRunner {

        // Create character data

        val lukeSkywalker = CharacterInput(
            UUID.fromString("241109eb-0f0d-4a7a-8667-604540804f1f"),
            "Luke Skywalker",
            Species.Human,
            "Tatooine",
            listOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val darthVader = CharacterInput(
            UUID.fromString("38e06bcc-68b9-4564-92e5-14ab9b3f39c0"),
            "Darth Vader",
            Species.Human,
            "Tatooine",
            listOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val hanSolo = CharacterInput(
            UUID.fromString("b0abd1f7-524d-4b24-b754-582e51395f9c"),
            "Han Solo",
            Species.Human,
            null,
            listOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val leiaOrgana = CharacterInput(
            UUID.fromString("bf244546-2f3e-4769-8934-3ffdf927f704"),
            "Leia Organa",
            Species.Human,
            "Alderaan",
            listOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val wilhuffTarkin = CharacterInput(
            UUID.fromString("0c167c94-7537-4a55-804e-8b28810228de"),
            "Wilhuff Tarkin",
            Species.Human,
            null,
            listOf(Episode.NewHope)
        )
        val c3po = CharacterInput(
            UUID.fromString("8b373644-bdcb-4ad3-923c-46fc7b7df7f0"),
            "C-3PO",
            Species.Protocol,
            "Tatooine",
            listOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )
        val aretoo = CharacterInput(
            UUID.fromString("5339c3e3-6464-4551-aafd-f046147f9c7e"),
            "R2-D2",
            Species.Astromech,
            null,
            listOf(Episode.NewHope, Episode.ReturnOfTheJedi, Episode.TheEmpireStrikesBack)
        )

        // Persist character data

        fun <T> JpaRepository<T, *>.saveAll(vararg items: T) {
            saveAll(items.asIterable())
            flush()
        }

        charactersInputRepository.saveAll(
            lukeSkywalker,
            darthVader,
            hanSolo,
            leiaOrgana,
            wilhuffTarkin,
            c3po,
            aretoo
        )

        // Update character data with links

        fun CharacterInput.toReference() = CharacterReference(id)

        fun MutableCollection<CharacterReference>.addAll(vararg items: CharacterInput) =
            addAll(items.map(CharacterInput::toReference))

        lukeSkywalker.friends.addAll(hanSolo, leiaOrgana, c3po, aretoo)
        darthVader.friends.addAll(wilhuffTarkin)
        hanSolo.friends.addAll(lukeSkywalker, leiaOrgana, aretoo)
        leiaOrgana.friends.addAll(lukeSkywalker, hanSolo, c3po, aretoo)
        wilhuffTarkin.friends.addAll(darthVader)
        c3po.friends.addAll(lukeSkywalker, hanSolo, leiaOrgana, aretoo)
        aretoo.friends.addAll(lukeSkywalker, hanSolo, leiaOrgana)

        // Persist links between character data

        charactersInputRepository.apply {
            save(lukeSkywalker)
            save(darthVader)
            save(hanSolo)
            save(leiaOrgana)
            save(wilhuffTarkin)
            save(c3po)
            save(aretoo)
        }

        // Persist hero data

        heroesInputRepository.saveAll(
            HeroInput(
                UUID.fromString("d4317fa3-e9da-4581-8300-52ed9f47c827"),
                Episode.NewHope,
                lukeSkywalker.toReference()
            ),
            HeroInput(
                UUID.fromString("d72e5b48-0553-4037-93d4-0d6725ece4d4"),
                Episode.TheEmpireStrikesBack,
                aretoo.toReference()
            ),
            HeroInput(
                UUID.fromString("3ed0c3e0-2d31-479a-8862-326dbb397bbb"),
                Episode.ReturnOfTheJedi,
                darthVader.toReference()
            )
        )
    }
}
