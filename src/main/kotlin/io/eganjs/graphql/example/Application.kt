package io.eganjs.graphql.example

import io.eganjs.graphql.example.models.CharacterInput
import io.eganjs.graphql.example.models.CharacterReference
import io.eganjs.graphql.example.models.Episode.*
import io.eganjs.graphql.example.models.HeroInput
import io.eganjs.graphql.example.models.Species.*
import io.eganjs.graphql.example.repositories.CharactersInputRepository
import io.eganjs.graphql.example.repositories.HeroesInputRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID.randomUUID

@SpringBootApplication
class Application {

    @Bean
    fun initialise(
        charactersInputRepository: CharactersInputRepository,
        heroesInputRepository: HeroesInputRepository
    ) = CommandLineRunner {

        // Create character data

        val lukeSkywalker = CharacterInput(
            randomUUID(),
            "Luke Skywalker",
            Human,
            "Tatooine",
            listOf(NewHope, ReturnOfTheJedi, TheEmpireStrikesBack)
        )
        val darthVader = CharacterInput(
            randomUUID(),
            "Darth Vader",
            Human,
            "Tatooine",
            listOf(NewHope, ReturnOfTheJedi, TheEmpireStrikesBack)
        )
        val hanSolo = CharacterInput(
            randomUUID(),
            "Han Solo",
            Human,
            null,
            listOf(NewHope, ReturnOfTheJedi, TheEmpireStrikesBack)
        )
        val leiaOrgana = CharacterInput(
            randomUUID(),
            "Leia Organa",
            Human,
            "Alderaan",
            listOf(NewHope, ReturnOfTheJedi, TheEmpireStrikesBack)
        )
        val wilhuffTarkin = CharacterInput(
            randomUUID(),
            "Wilhuff Tarkin",
            Human,
            null,
            listOf(NewHope)
        )
        val c3po = CharacterInput(
            randomUUID(),
            "C-3PO",
            Protocol,
            "Tatooine",
            listOf(NewHope, ReturnOfTheJedi, TheEmpireStrikesBack)
        )
        val aretoo = CharacterInput(
            randomUUID(),
            "R2-D2",
            Astromech,
            null,
            listOf(NewHope, ReturnOfTheJedi, TheEmpireStrikesBack)
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
            HeroInput(randomUUID(), NewHope, lukeSkywalker.toReference()),
            HeroInput(randomUUID(), TheEmpireStrikesBack, aretoo.toReference()),
            HeroInput(randomUUID(), ReturnOfTheJedi, darthVader.toReference())
        )
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
