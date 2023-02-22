import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.browser.window
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

const val BASE_URL = "http://localhost:8080"

val client = HttpClient {
    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }
    expectSuccess = true
    HttpResponseValidator {
        handleResponseExceptionWithRequest { cause, _ ->
            when (cause) {
                is ServerResponseException -> {
                    val responseContent = cause.response.bodyAsText()
                    window.alert(responseContent)
                }
            }
        }
    }
}

@Serializable
data class Cocktail(
    val id: Int,
    val name: String,
    val available: Boolean,
    val cooked: Boolean,
)

suspend fun getCocktails(): List<Cocktail> {
    return client.get("$BASE_URL/cocktails").body()
}

suspend fun deleteCocktail(id: Int) {
    client.delete("$BASE_URL/cocktails/$id")
}
