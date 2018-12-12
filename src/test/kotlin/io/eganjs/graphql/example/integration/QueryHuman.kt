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
class QueryHuman {

    @LocalServerPort
    var port: Int = 0

    @Test
    fun `when I request one human by id, with only the id property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  human(id: "241109eb-0f0d-4a7a-8667-604540804f1f") {
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
                    "human": {
                      "id": "241109eb-0f0d-4a7a-8667-604540804f1f"
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one human by id, with only the id property, using an invalid id`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  human(id: "invalid id") {
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
                    "human": null
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one human by id, with only the name property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  human(id: "241109eb-0f0d-4a7a-8667-604540804f1f") {
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
                    "human": {
                      "name": "Luke Skywalker"
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one human by id, with only the species property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  human(id: "241109eb-0f0d-4a7a-8667-604540804f1f") {
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
                    "human": {
                      "species": "Human"
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one human by id, with only the appearsIn property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  human(id: "241109eb-0f0d-4a7a-8667-604540804f1f") {
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
                    "human": {
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
    fun `when I request one human by id, with only the friends{id} property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  human(id: "241109eb-0f0d-4a7a-8667-604540804f1f") {
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
                    "human": {
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
                    }
                  }
                }
                """
            }
        }
    }()

    @Test
    fun `when I request one human by id, with only the homePlanet property`() = restAssertion {
        request {
            baseUri("http://localhost:$port/graphql")
            graphqlBody {
                """
                {
                  human(id: "241109eb-0f0d-4a7a-8667-604540804f1f") {
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
                    "human": {
                      "homePlanet": "Tatooine"
                    }
                  }
                }
                """
            }
        }
    }()
}
