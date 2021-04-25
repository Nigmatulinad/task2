import java.io.File
import kotlin.system.exitProcess

/**Вариант 4 -- find Поиск файла(ов) с заданным в командной строке именем в указанной ключом -d
 * директории, по умолчанию в текущей директории. Ключ -r указывает на необходимость поиска также во
 * всех поддиректориях. Command Line: find [-r] [-d directory] filename.txt Кроме самой программы,
 * следует написать автоматические тесты к ней.
 */

fun main(args : Array<String>) {
    var path = ""
    var r = false
    var i = 0
    while (i < args.size - 1) {
        when (args[i]) {
            "-r" -> r = true
            "-d" -> {
                path = args[i + 1]
                i++
            }
            else -> {
                println("Неизвестный аргумент ${args[i]}")
                exitProcess(1)
            }
        }
        i++
    }

    if (i == args.size) {
        println("Отсутствует имя файла")
        exitProcess(1)
    }
    find(args.last(), File(path), r)
}


fun find(name: String, path: File, r: Boolean){
    val trajectory = path.listFiles()
    if (r) {
        if (path.isDirectory) {
            if (trajectory != null) for (file in trajectory) {
                if (file.isDirectory)
                    find(name, path, true)
                if (name == file.name) println(file.path + " : " + name)
            }
        }
    } else {
        if (trajectory != null)
            for (i in trajectory)
                if (name == i.name) println(i.path + " : " + name)
    }
}