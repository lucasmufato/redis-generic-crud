package ar.com.mufato.delivery.http

import ar.com.mufato.delivery.http.handler.Handler
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.serialization.json
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory
import org.slf4j.event.Level

class KtorServer(
	private val config: KtorConfig,
	private val handlers: List<Handler>
) {
	private val logger = LoggerFactory.getLogger(this::class.java)

	fun start() {
		logger.info("Starting in port ${config.port}")

		val server = embeddedServer(Netty, port = config.port) {
			main()
		}

		server.start(wait = true)
	}

	private fun Application.main() {
		installFeatures()

		handlers.forEach { it.routing(this) }
	}

	private fun Application.installFeatures() {
		install(DefaultHeaders)
		installContentNegotiation()
		install(CallLogging) {
			level = Level.INFO
		}
		install(StatusPages) {
			addExceptionHandlers()
		}
	}


	private fun Application.installContentNegotiation() {
		install(ContentNegotiation) {
			json(
				Json {
					ignoreUnknownKeys = true
				}
			)
		}
	}

	private fun StatusPages.Configuration.addExceptionHandlers() {
		exception<Exception> { cause ->
			logger.error("Unhandled exception in ${call.request.path()}: ${cause.localizedMessage}", cause)
			call.respond(HttpStatusCode.InternalServerError, cause::class.java.simpleName + ": " + cause.message)
		}
	}

}