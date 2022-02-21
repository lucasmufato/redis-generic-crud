package ar.com.mufato.core.action

import ar.com.mufato.core.domain.Mueble
import ar.com.mufato.core.services.InMemoryMuebleIdGenerator


class AltaMueble(private val idGenerator: InMemoryMuebleIdGenerator, muebleStorage: MuebleStorage) {

	operator fun invoke(datosParaElAlta: ActionData): Mueble{

		return Mueble(idGenerator.nextId(), datosParaElAlta.datos)
	}

	class ActionData(val datos: Map<String,String> ){
		init {
			if (datos.isEmpty()) {throw RuntimeException("No se puede crear un mueble sin datos")}
		}
	}
}

