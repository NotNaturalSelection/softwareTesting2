package logarithmic

import java.io.PrintWriter
import kotlin.math.abs
import kotlin.math.pow

class PowerSeriesLn(_out: PrintWriter? = null) : Ln(_out) {
    override fun ln(arg: Double, precision: Double): Double {
        val minimizedArg: Double = (arg - 1) / (arg + 1)
        var sum: Double = 0.0
        var divider: Double = 1.0
        var rowMember: Double
        do {
            rowMember = sum
            sum += (minimizedArg.pow(divider) / divider)
            divider += 2
        } while (abs(sum - rowMember) > precision.pow(2))
        sum *= 2
        return sum
    }
}