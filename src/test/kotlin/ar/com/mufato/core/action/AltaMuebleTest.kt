package ar.com.mufato.core.action

import ar.com.mufato.core.services.InMemoryMuebleIdGenerator
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals


class AltaMuebleTest{

	@Test
	fun `dar de alta mueble devuelve un mueble con un id generado por el generador de ids`() {
		//given
		val idGenerator = InMemoryMuebleIdGenerator()
		val muebleStorage = MuebleStorage()
		val accion = AltaMueble(idGenerator, muebleStorage)

		//when
		val muebleCreado = accion(AltaMueble.ActionData(mapOf("tipo" to "silla")))

		//then
		assertEquals(1, muebleCreado.id)
		assertEquals("silla", muebleCreado.datos["tipo"])
	}

	@Test
	internal fun `al dar de alta mueble este se guarda en el storage`() {
		//given
		val idGenerator = InMemoryMuebleIdGenerator()
		val muebleStorage = MuebleStorage()
		val accion = AltaMueble(idGenerator, muebleStorage)

		//when
		val muebleCreado = accion(AltaMueble.ActionData(mapOf("tipo" to "silla")))

		assertEquals(muebleCreado, muebleStorage.first())
	}
}