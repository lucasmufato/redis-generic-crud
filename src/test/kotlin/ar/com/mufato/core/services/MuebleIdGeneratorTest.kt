package ar.com.mufato.core.services

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MuebleIdGeneratorTest{

    private val idGenerator: MuebleIdGenerator = InMemoryMuebleIdGenerator()

    @Test
    internal fun `dado un generador recien instanciado retorna uno`() {
        assertEquals(1, idGenerator.nextId())
    }

    @Test
    internal fun `el generador debe devolver el siguiente al ultimo id generado`() {
        assertEquals(1, idGenerator.nextId())
        assertEquals(2, idGenerator.nextId())
        assertEquals(3, idGenerator.nextId())
    }
}