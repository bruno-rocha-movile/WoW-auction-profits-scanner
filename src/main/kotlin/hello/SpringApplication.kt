package hello

/**
 * Created by bruno.rocha on 11/6/16.
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

val starlightRose: Long = 124105
val dreamleaf: Long = 124102
val foxflower: Long = 124103
val fjarnskaggl: Long = 124104

var data: MutableMap<Long,Long> = emptyMutableMap()
fun emptyMutableMap(): MutableMap<Long,Long> = mutableMapOf()

@RestController
class Application {
    @RequestMapping("/")
    fun index(): String {
        val page = "\n" +
                "Starlight Rose: " + data[starlightRose] + "\n" +
                "Dreamleaf: " + data[dreamleaf] + "\n" +
                "Foxflower: " + data[foxflower] + "\n" +
                "Fjarnskaggl: " + data[fjarnskaggl] + "\n"
        return page
    }
}
