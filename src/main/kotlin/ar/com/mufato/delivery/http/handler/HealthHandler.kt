package ar.com.mufato.delivery.http.handler

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import kotlinx.serialization.Serializable

class HealthHandler: Handler {
	override fun routing(a: Application) {
		a.routing {
			get("/health") {
				call.respond( HttpStatusCode.OK, HealthResponse("OK") )
			}
		}
	}

	@Serializable
	data class HealthResponse(val status: String )

}
