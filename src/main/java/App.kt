import common.Expression
import logarithmic.*
import trigonometric.PowerSeriesCos
import trigonometric.SinUsingCos
import trigonometric.TanUsingCosAndSin
import java.io.FileOutputStream
import java.io.PrintWriter
import java.util.*

private const val OUT_FILES = "src/test/resources/out/"

fun getPW(name:String): PrintWriter {
    return PrintWriter(FileOutputStream("${OUT_FILES}$name.csv", true), true)
}

fun main(){
    val sc = Scanner(System.`in`)
    println("Введите размер шага")
    val step = sc.nextLine().toDouble()
    println("Введите левую границу")
    val left = sc.nextLine().toDouble()
    println("Введите правую границу")
    val right = sc.nextLine().toDouble()
    var curr = left
    val cos = PowerSeriesCos(getPW("cos"))
    val sin = SinUsingCos(PowerSeriesCos(), getPW("sin"))
    val tan = TanUsingCosAndSin(SinUsingCos(PowerSeriesCos()), PowerSeriesCos(), getPW("tan"))
    val ln = PowerSeriesLn(getPW("ln"))
    val log2 =  Log2(PowerSeriesLn(), getPW("log2"))
    val log3 =  Log3(PowerSeriesLn(), getPW("log3"))
    val log5 =  Log5(PowerSeriesLn(), getPW("log5"))
    val log10 =  Log10(PowerSeriesLn(), getPW("log10"))
    val expression = Expression(cos, tan, ln, log2, log3, log5, log10, getPW("exp"))
    while (curr <= right) {
        expression.compute(curr)
        curr+=step
    }
}