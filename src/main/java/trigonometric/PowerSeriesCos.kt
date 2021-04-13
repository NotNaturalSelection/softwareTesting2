package trigonometric

import java.io.PrintWriter
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.pow

class PowerSeriesCos(_out: PrintWriter? = null) : Cos(_out) {
    override fun cos(arg: Double, precision: Double): Double {
        val argument: Double = arg % (2 * PI)
        var rowMember: Double = 1.0
        var result: Double = 0.0
        var factorialBase: Int = 1
        do {
            result += rowMember
            rowMember *= -1.0 * argument * argument / ((2 * factorialBase - 1) * 2 * factorialBase)
            factorialBase++
        } while (abs(rowMember) > precision.pow(2))
        return result
    }
}