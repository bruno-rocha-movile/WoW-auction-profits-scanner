package hello

import com.github.kittinunf.fuel.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

fun main(args: Array<String>) {
   // com.jmstest.util.Log.setLevel(com.jmstest.util.Log.Level.NONE)
    SpringApplication.run(Main::class.java, *args)
    Main().start()
}


@SpringBootApplication
open class Main {
    fun start() {
        val app = WowApp()
        app.start()
    }
}
