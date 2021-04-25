import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import java.io.File
import java.io.PrintStream
import java.io.ByteArrayOutputStream


internal class MainKtTest {
    private val out = ByteArrayOutputStream()
    private val originOut = System.out
    private val sep = System.lineSeparator()

    @BeforeEach
    fun init() {
        System.setOut(PrintStream(out))
    }

    @AfterEach
    fun after(){
        System.setOut(originOut)
    }

    @Test
    fun find () {
        find("main.kt", File("src\\main\\kotlin"),true)
        assertEquals("src\\main\\kotlin\\main.kt : main.kt$sep", out.toString())
        out.reset()
        find("compiler.xml",File(".idea"),false)
        assertEquals(".idea\\compiler.xml : compiler.xml$sep",out.toString())
        out.reset()
        find("find.txt",File("test\\for"),false)
        assertEquals("test\\for\\find.txt : find.txt$sep",out.toString())
        out.reset()
    }
}