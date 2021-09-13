import java.io.File
import java.io.FileInputStream
import java.util.*

/**
 * @author amadeus.portes
 */
object KeyHelper {
    const val KEY_STORE_FILE = "storeFile"
    const val KEY_STORE_PASS = "storePassword"
    const val KEY_ALIAS = "keyAlias"
    const val KEY_PASS = "keyPassword"
    private const val PRIVATE_PROPERTIES = "/Documents/MyTodo/keystore.properties"
    private val properties by lazy {
        Properties().apply { load(FileInputStream(File("${System.getProperty("user.home")}$PRIVATE_PROPERTIES"))) }
    }

    fun getValue(key: String): String {
        return properties.getProperty(key)
    }
}
