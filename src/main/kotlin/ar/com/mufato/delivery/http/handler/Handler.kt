package ar.com.mufato.delivery.http.handler

import io.ktor.application.Application

interface Handler {
	fun routing(a: Application)
}
