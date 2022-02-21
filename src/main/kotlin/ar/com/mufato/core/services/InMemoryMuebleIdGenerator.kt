package ar.com.mufato.core.services

interface MuebleIdGenerator {
    fun nextId(): Int
}

class InMemoryMuebleIdGenerator : MuebleIdGenerator {
    private var id = 0

    override fun nextId(): Int {
        return updateWithNextId()
    }

    private fun updateWithNextId(): Int {
        id += 1
        return id
    }

}
