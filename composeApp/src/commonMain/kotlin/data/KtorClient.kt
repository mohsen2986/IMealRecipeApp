package data

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object KtorClient {
//        const val BASE_URL = "https://fakestoreapi.com"

    val client = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                isLenient = true
            })
        }
//        install(Logging) {
//            logger = object : Logger {
//                override fun log(message: String) {
//                    co.touchlab.kermit.Logger.d(tag = "HTTP_CLIENT",
//                                                messageString = message)
//                }
//            }
//            level = LogLevel.ALL
//        }
    }
}