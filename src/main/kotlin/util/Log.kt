package util

/**
 * Created by bruno.rocha on 11/6/16.
 */

import org.slf4j.LoggerFactory
import org.slf4j.Marker
import org.slf4j.MarkerFactory

class Log {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(Log::class.java)

        enum class Color(val marker: Marker) {
            BLACK(MarkerFactory.getMarker("black")),
            RED(MarkerFactory.getMarker("red")),
            GREEN(MarkerFactory.getMarker("green")),
            YELLOW(MarkerFactory.getMarker("yellow")),
            BLUE(MarkerFactory.getMarker("blue")),
            MAGENTA(MarkerFactory.getMarker("magenta")),
            CYAN(MarkerFactory.getMarker("cyan")),
            WHITE(MarkerFactory.getMarker("white"));
        }

        fun info(text: String, color: Color = Color.WHITE, vararg args: Any) {
            LOGGER.info(color.marker, text, args)
        }

        fun debug(text: String, color: Color = Color.WHITE, vararg args: Any) {
            LOGGER.debug(color.marker, text, args)
        }

        fun error(text: String, color: Color = Color.WHITE, vararg args: Any) {
            LOGGER.error(color.marker, text, args)
        }

        fun warn(text: String, color: Color = Color.WHITE, vararg args: Any) {
            LOGGER.warn(color.marker, text, args)
        }

        fun trace(text: String, color: Color = Color.WHITE, vararg args: Any) {
            LOGGER.trace(color.marker, text, args)
        }

        fun normal(text: String = "") {
            info(text, Color.WHITE)
        }

        fun black(text: String = "") {
            info(text, Color.BLACK)
        }

        fun red(text: String = "") {
            warn(text, Color.RED)
        }

        fun green(text: String = "") {
            info(text, Color.GREEN)
        }

        fun yellow(text: String = "") {
            info(text, Color.YELLOW)
        }

        fun blue(text: String = "") {
            info(text, Color.BLUE)
        }

        fun magenta(text: String = "") {
            info(text, Color.MAGENTA)
        }

        fun cyan(text: String = "") {
            info(text, Color.CYAN)
        }

        fun white(text: String = "") {
            info(text, Color.WHITE)
        }
    }
}