package ar.com.mufato.delivery.http.handler

import ar.com.mufato.core.action.AltaMueble
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.post
import io.ktor.routing.routing
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class AltaHandler(private val alta: AltaMueble): Handler {


	override fun routing(a: Application) {
		a.routing {
			post("app/mueble") {

				// obtener el json.
				//val muebleJson = MuebleRepresentation( mapOf("color" to "rojo", "tipo" to "silla"))
				val muebleJson = call.receive<MuebleRepresentation>()
				val mueble = muebleJson.transformateAlCore()
				alta( mueble )
			}
		}
	}

	@Serializable
	data class MuebleRepresentation(
		@SerialName("datos_del_mueble") val datos: Map<String, String>
	) {
		fun transformateAlCore(): AltaMueble.ActionData {
			return AltaMueble.ActionData(datos)
		}
	}


}