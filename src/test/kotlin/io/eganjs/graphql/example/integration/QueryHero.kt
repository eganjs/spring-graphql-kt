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
class QueryHero {

    @LocalServerPort
    var port: Int = 0

    @Test
    fun `when I request one hero by episode, with only the id property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  hero(episode: ReturnOfTheJedi) {
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
                    "hero": {
                      "id": "38e06bcc-68b9-4564-92e5-14ab9b3f39c0"
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one hero by episode, with only the id property, using an invalid episode`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  hero(episode: InvalidEpisode) {
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
                  "data": null,
                  "errors": [
                    {
                      "description": "argument 'episode' with value 'EnumValue{name='InvalidEpisode'}' is not a valid 'Episode'",
                      "validationErrorType": "WrongType",
                      "queryPath": [
                        "hero"
                      ],
                      "errorType": "ValidationError"
                    }
                  ]
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one hero by episode, with only the name property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  hero(episode: ReturnOfTheJedi) {
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
                    "hero": {
                      "name": "Darth Vader"
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one hero by episode, with only the species property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  hero(episode: ReturnOfTheJedi) {
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
                    "hero": {
                      "species": "Human"
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one hero by episode, with only the appearsIn property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  hero(episode: ReturnOfTheJedi) {
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
                    "hero": {
                      "appearsIn": [
                        "TheEmpireStrikesBack",
                        "NewHope",
                        "ReturnOfTheJedi"
                      ]
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one hero by episode, with only the friends{id} property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  hero(episode: ReturnOfTheJedi) {
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
                    "hero": {
                      "friends": [
                        {
                          "id": "0c167c94-7537-4a55-804e-8b28810228de"
                        }
                      ]
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one hero by episode, with only the homePlanet property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  hero(episode: ReturnOfTheJedi) {
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
                    "hero": {
                      "homePlanet": "Tatooine"
                    }
                  }
                }
                """
            }
        }
    }()
}
