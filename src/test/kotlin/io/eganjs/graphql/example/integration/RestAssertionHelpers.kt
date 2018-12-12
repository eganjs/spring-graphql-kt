package io.eganjs.graphql.example.integration

import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import io.restassured.response.Response
import io.restassured.specification.RequestSpecification
import org.assertj.core.api.Assertions.assertThat
import org.skyscreamer.jsonassert.JSONAssert

fun restAssertion(assertion: RestAssertion.() -> Unit): RestAssertion {
    val restAssertion = RestAssertion()
    assertion(restAssertion)
    return restAssertion
}

class RestAssertion {

    private lateinit var requestSpecification: RequestSpecification.() -> Response
    private lateinit var responseSpecification: Response.() -> Unit

    fun request(requestSpecification: RequestSpecification.() -> Response) {
        this.requestSpecification = requestSpecification
    }

    fun response(responseSpecification: Response.() -> Unit) {
        this.responseSpecification = responseSpecification
    }

    operator fun invoke() =
        responseSpecification(requestSpecification(given().log().all()))

}

fun RequestSpecification.jsonBody(jsonBodyResolver: () -> String) {
    contentType(ContentType.JSON)
    body(jsonBodyResolver().trimIndent())
}

fun RequestSpecification.graphqlBody(graphqlQueryResolver: () -> String) = jsonBody {
    """
    {
      "query": "${graphqlQueryResolver().formatGraphqlQuery()}"
    }
    """
}

fun Response.statusCodeMatches(expectedStatusCode: Int) =
    assertThat(statusCode)
        .`as`("Status Code should be [$expectedStatusCode]")
        .isEqualTo(expectedStatusCode)!!

fun Response.jsonBodyMatches(expectedBodyResolver: () -> String) {
    val expectedBody = expectedBodyResolver().trimIndent()
    JSONAssert
        .assertEquals(expectedBody, body.print(), false)
}

fun String.formatGraphqlQuery() = this
    .trimIndent()
    .replace("\n", " ")
    .replace(Regex("[ ]{2,}"), " ")
    .replace("\"", "\\\"")

