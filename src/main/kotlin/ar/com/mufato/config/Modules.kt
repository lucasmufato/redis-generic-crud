package ar.com.mufato.config

import ar.com.mufato.delivery.http.KtorConfig
import ar.com.mufato.delivery.http.KtorServer
import ar.com.mufato.delivery.http.handler.HealthHandler

object Modules{

	private val healthHandler = HealthHandler()

	private val handlerList = listOf(healthHandler)

	val httpServer = KtorServer(KtorConfig(9091), handlerList)
}