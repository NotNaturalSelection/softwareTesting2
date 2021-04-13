package trigonometric

import java.io.PrintWriter
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.sqrt

class SinUsingCos(private val powerSeriesCos: Cos, _out: PrintWriter? = null) : Sin(_out) {
    override fun sin(arg: Double, precision: Double): Double {
        return if (arg > 0) {
            if (abs(arg % (2 * PI)) > PI) {
                -sqrt(1 - powerSeriesCos.compute(arg, precision).pow(2))
            } else {
                sqrt(1 - powerSeriesCos.compute(arg, precision).pow(2))
            }
        } else {
            if (abs(arg % (2 * PI)) > PI) {
                sqrt(1 - powerSeriesCos.compute(arg, precision).pow(2))
            } else {
                -sqrt(1 - powerSeriesCos.compute(arg, precision).pow(2))
            }
        }
    }
}