package hello

/**
 * Created by bruno.rocha on 11/6/16.
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

var starlightRosePrice: Long = 0
var dreamLeafPrice: Long = 0
var foxFlowerPrice: Long = 0
var fjarnPrice: Long = 0

@RestController
class Application {
    @RequestMapping("/")
    fun index(): String {
        val page = "\n" +
                "Starlight Rose: " + starlightRosePrice + "\n" +
                "Dreamleaf: " + dreamLeafPrice + "\n" +
                "Foxflower: " + foxFlowerPrice + "\n" +
                "Fjarnskaggl: " + fjarnPrice + "\n"
        return page
    }
}
