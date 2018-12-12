package io.eganjs.graphql.example.models

enum class Episode {
    NewHope,
    TheEmpireStrikesBack,
    ReturnOfTheJedi
}

enum class SpeciesType {
    Biological,
    Synthetic
}

enum class Species(val type: SpeciesType) {
    Human(SpeciesType.Biological),
    Protocol(SpeciesType.Synthetic),
    Astromech(SpeciesType.Synthetic)
}
