import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
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

@Serializable
data class CocktailCreation(
    val name: String,
    val available: Boolean,
)

@Serializable
data class CocktailUpdate(
    val name: String,
    val available: Boolean,
)

suspend fun getCocktails(): List<Cocktail> {
    return client.get("$BASE_URL/cocktails").body()
}

suspend fun createCocktail(name: String) {
    client.post("$BASE_URL/cocktails") {
        setBody(CocktailCreation(name, available = true))
        contentType(ContentType.Application.Json)
    }
}

suspend fun updateCocktail(cocktail: Cocktail, newName: String) {
    client.put("$BASE_URL/cocktails/${cocktail.id}") {
        setBody(CocktailUpdate(name = newName, available = cocktail.available))
        contentType(ContentType.Application.Json)
    }
}

suspend fun updateCocktail(cocktail: Cocktail, newAvailable: Boolean) {
    client.put("$BASE_URL/cocktails/${cocktail.id}") {
        setBody(CocktailUpdate(name = cocktail.name, available = newAvailable))
        contentType(ContentType.Application.Json)
    }
}

suspend fun deleteCocktail(id: Int) {
    client.delete("$BASE_URL/cocktails/$id")
}
