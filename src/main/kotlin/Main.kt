import ar.com.mufato.config.Modules.httpServer

fun main(args: Array<String>) {

	println("Program arguments: ${args.joinToString()}")

	httpServer.start()
}