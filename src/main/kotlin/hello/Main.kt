package hello

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import util.ANSI_RED
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.nio.file.Paths
import java.util.*
import javax.swing.text.rtf.RTFEditorKit

fun main(args: Array<String>) {
    SpringApplication.run(Main::class.java, *args)
    Main().start()
}


@SpringBootApplication
open class Main {
    fun start() {
        applyUserSettings()
        val app = WowDataRetriever()
        app.start()
    }

    fun applyUserSettings() {
        var properties: Properties? = null
        val attemptFilenames = arrayOf("config.properties", "config.properties.txt", "config.properties.rtf")
        val dir = File(System.getProperty("java.class.path")).absoluteFile.parentFile
        var filename = "";
        fileLoop@ for (path in arrayOf(Paths.get("").toAbsolutePath(), dir)) {
            for (attemptFilename in attemptFilenames) {
                try {
                    filename = attemptFilename
                    properties = loadUserSettings("${path.toString()}/$filename")
                    break@fileLoop
                } catch (e: FileNotFoundException) {}
            }
        }

        if (properties == null) {
            util.println("Settings file not found. Exiting", ANSI_RED)
            System.exit(1);
            return
        } else {
            data = SettingsParser(properties).createSettingsFromProperties()
        }
    }

    fun loadUserSettings(filename: String): Properties {
        val properties = Properties()
        var failed = false
        try {
            FileInputStream(filename).use {
                try {
                    properties.load(it)
                } catch (e: Exception) {
                    failed = true
                }
            }
        } catch (e: FileNotFoundException) {
            throw e
        }
        if (failed) {
            FileInputStream(filename).use {
                val rtfParser = RTFEditorKit()
                val document = rtfParser.createDefaultDocument()
                rtfParser.read(it.reader(), document, 0)
                val text = document.getText(0, document.getLength())
                properties.load(text.byteInputStream())
            }
        }
        return properties
    }
}
