package hello

import com.github.kittinunf.fuel.*

fun main(args: Array<String>) {
    showGreeting()
    Thread.sleep(4000)
    login()
    Thread.sleep(1000)
    smash()
  /*  Fuel.get("http://httpbin.org/get").response { request, response, result ->
        println(request)
        println(response)
        val (bytes, error) = result
        if (bytes != null) {
            println(bytes)
        }
    } */
}
