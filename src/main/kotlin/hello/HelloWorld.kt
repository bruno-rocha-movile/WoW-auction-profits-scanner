package hello

import com.github.kittinunf.fuel.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import util

fun main(args: Array<String>) {
   // com.jmstest.util.Log.setLevel(com.jmstest.util.Log.Level.NONE)
    SpringApplication.run(Main::class.java, *args)
    Main().start()
}

@SpringBootApplication
open class Main {
    fun start() {
        val app = WowApp()
        app.showGreeting()
        Thread.sleep(4000)
        app.login()
        Thread.sleep(1000)
        app.smash()
        /*  Fuel.get("http://httpbin.org/get").response { request, response, result ->
              println(request)
              println(response)
              val (bytes, error) = result
              if (bytes != null) {
                  println(bytes)
              }
          } */
    }
}
