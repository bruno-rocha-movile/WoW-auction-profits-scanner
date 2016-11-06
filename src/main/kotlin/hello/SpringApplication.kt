package hello

/**
 * Created by bruno.rocha on 11/6/16.
 */

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
class Application {
    @RequestMapping("/")
    fun index(): String {
        return "Greetings from Spring Boot!"
    }
}
