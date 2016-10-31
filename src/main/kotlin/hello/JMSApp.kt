package hello

/**
 * Created by bruno.rocha on 10/31/16.
 */

fun showGreeting() {
    println("---------------------------------")
    println("---------------------------------")
    println("---------------------------------")
    println("---JMS Bot 0.1 by Bruno Rocha----", ANSI_BLUE)
    println("---------------------------------")
    println("---------------------------------")
    println("---------------------------------")
}

fun login() {
    println("Logging in...")
    Thread.sleep(3000)
    println("Success.", ANSI_GREEN)
}

fun smash() {
    println("Searching for opponent", ANSI_YELLOW)
    Thread.sleep(1000)
    println("Opponent 328 found. Battle token: 24873829284", ANSI_GREEN)
    println("Starting battle...", ANSI_YELLOW)
    Thread.sleep(1500)
    println("Battle result: VICTORY", ANSI_GREEN)
    println("Waiting 2 seconds...")
    Thread.sleep(2000)
    smash()
}