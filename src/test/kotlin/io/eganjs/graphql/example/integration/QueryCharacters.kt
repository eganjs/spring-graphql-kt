package io.eganjs.graphql.example.integration

import io.eganjs.graphql.example.GraphqlApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ContextConfiguration(classes = [GraphqlApplication::class])
class QueryCharacters {

    @LocalServerPort
    var port: Int = 0

    @Test
    fun `when I request all characters, with only the id property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  characters {
                    id
                  }
                }
                """
            }
            post()
        }
        response {
            statusCodeMatches(200)
            jsonBodyMatches {
                """
                {
                  "data": {
                    "characters":  [
                      {
                        "id": "241109eb-0f0d-4a7a-8667-604540804f1f"
                      },
                      {
                        "id": "38e06bcc-68b9-4564-92e5-14ab9b3f39c0"
                      },
                      {
                        "id": "b0abd1f7-524d-4b24-b754-582e51395f9c"
                      },
                      {
                        "id": "bf244546-2f3e-4769-8934-3ffdf927f704"
                      },
                      {
                        "id": "0c167c94-7537-4a55-804e-8b28810228de"
                      },
                      {
                        "id": "8b373644-bdcb-4ad3-923c-46fc7b7df7f0"
                      },
                      {
                        "id": "5339c3e3-6464-4551-aafd-f046147f9c7e"
                      }
                    ]
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request all characters, with only the name property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  characters {
                    name
                  }
                }
                """
            }
            post()
        }
        response {
            statusCodeMatches(200)
            jsonBodyMatches {
                """
                {
                  "data": {
                    "characters": [
                      {
                        "name": "Luke Skywalker"
                      },
                      {
                        "name": "Darth Vader"
                      },
                      {
                        "name": "Han Solo"
                      },
                      {
                        "name": "Leia Organa"
                      },
                      {
                        "name": "Wilhuff Tarkin"
                      },
                      {
                        "name": "C-3PO"
                      },
                      {
                        "name": "R2-D2"
                      }
                    ]
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request all characters, with only the species property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  characters {
                    species
                  }
                }
                """
            }
            post()
        }
        response {
            statusCodeMatches(200)
            jsonBodyMatches {
                """
                {
                  "data": {
                    "characters": [
                      {
                        "species": "Human"
                      },
                      {
                        "species": "Human"
                      },
                      {
                        "species": "Human"
                      },
                      {
                        "species": "Human"
                      },
                      {
                        "species": "Human"
                      },
                      {
                        "species": "Protocol"
                      },
                      {
                        "species": "Astromech"
                      }
                    ]
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request all characters, with only the appearsIn property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  characters {
                    appearsIn
                  }
                }
                """
            }
            post()
        }
        response {
            statusCodeMatches(200)
            jsonBodyMatches {
                """
                {
                  "data": {
                    "characters": [
                      {
                        "appearsIn": [
                          "TheEmpireStrikesBack",
                          "NewHope",
                          "ReturnOfTheJedi"
                        ]
                      },
                      {
                        "appearsIn": [
                          "TheEmpireStrikesBack",
                          "NewHope",
                          "ReturnOfTheJedi"
                        ]
                      },
                      {
                        "appearsIn": [
                          "TheEmpireStrikesBack",
                          "NewHope",
                          "ReturnOfTheJedi"
                        ]
                      },
                      {
                        "appearsIn": [
                          "TheEmpireStrikesBack",
                          "NewHope",
                          "ReturnOfTheJedi"
                        ]
                      },
                      {
                        "appearsIn": [
                          "NewHope"
                        ]
                      },
                      {
                        "appearsIn": [
                          "TheEmpireStrikesBack",
                          "NewHope",
                          "ReturnOfTheJedi"
                        ]
                      },
                      {
                        "appearsIn": [
                          "TheEmpireStrikesBack",
                          "NewHope",
                          "ReturnOfTheJedi"
                        ]
                      }
                    ]
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request all characters, with only the friends{id} property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  characters {
                    friends {
                      id
                    }
                  }
                }
                """
            }
            post()
        }
        response {
            statusCodeMatches(200)
            jsonBodyMatches {
                """
                {
                  "data": {
                    "characters": [
                      {
                        "friends": [
                          {
                            "id": "5339c3e3-6464-4551-aafd-f046147f9c7e"
                          },
                          {
                            "id": "8b373644-bdcb-4ad3-923c-46fc7b7df7f0"
                          },
                          {
                            "id": "b0abd1f7-524d-4b24-b754-582e51395f9c"
                          },
                          {
                            "id": "bf244546-2f3e-4769-8934-3ffdf927f704"
                          }
                        ]
                      },
                      {
                        "friends": [
                          {
                            "id": "0c167c94-7537-4a55-804e-8b28810228de"
                          }
                        ]
                      },
                      {
                        "friends": [
                          {
                            "id": "241109eb-0f0d-4a7a-8667-604540804f1f"
                          },
                          {
                            "id": "5339c3e3-6464-4551-aafd-f046147f9c7e"
                          },
                          {
                            "id": "bf244546-2f3e-4769-8934-3ffdf927f704"
                          }
                        ]
                      },
                      {
                        "friends": [
                          {
                            "id": "241109eb-0f0d-4a7a-8667-604540804f1f"
                          },
                          {
                            "id": "5339c3e3-6464-4551-aafd-f046147f9c7e"
                          },
                          {
                            "id": "8b373644-bdcb-4ad3-923c-46fc7b7df7f0"
                          },
                          {
                            "id": "b0abd1f7-524d-4b24-b754-582e51395f9c"
                          }
                        ]
                      },
                      {
                        "friends": [
                          {
                            "id": "38e06bcc-68b9-4564-92e5-14ab9b3f39c0"
                          }
                        ]
                      },
                      {
                        "friends": [
                          {
                            "id": "241109eb-0f0d-4a7a-8667-604540804f1f"
                          },
                          {
                            "id": "5339c3e3-6464-4551-aafd-f046147f9c7e"
                          },
                          {
                            "id": "b0abd1f7-524d-4b24-b754-582e51395f9c"
                          },
                          {
                            "id": "bf244546-2f3e-4769-8934-3ffdf927f704"
                          }
                        ]
                      },
                      {
                        "friends": [
                          {
                            "id": "241109eb-0f0d-4a7a-8667-604540804f1f"
                          },
                          {
                            "id": "b0abd1f7-524d-4b24-b754-582e51395f9c"
                          },
                          {
                            "id": "bf244546-2f3e-4769-8934-3ffdf927f704"
                          }
                        ]
                      }
                    ]
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request all characters, with only the homePlanet property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  characters {
                    homePlanet
                  }
                }
                """
            }
            post()
        }
        response {
            statusCodeMatches(200)
            jsonBodyMatches {
                """
                {
                  "data": {
                    "characters": [
                      {
                        "homePlanet": "Tatooine"
                      },
                      {
                        "homePlanet": "Tatooine"
                      },
                      {
                        "homePlanet": null
                      },
                      {
                        "homePlanet": "Alderaan"
                      },
                      {
                        "homePlanet": null
                      },
                      {
                        "homePlanet": "Tatooine"
                      },
                      {
                        "homePlanet": null
                      }
                    ]
                  }
                }
                """
            }
        }
    }()
}
