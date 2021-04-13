package common

import logarithmic.*
import trigonometric.Cos
import trigonometric.Tan
import java.io.PrintWriter
import kotlin.math.pow

class Expression(
    private val cos: Cos, private val tan: Tan, private val ln: Ln, private val log2: Log2,
    private val log3: Log3, private val log5: Log5, private val log10: Log10, _out: PrintWriter? = null
) : ComputableWritableModule(_out) {

    override fun calc(arg: Double, precision: Double): Double {
        return if (arg <= 0) {
            cos.compute(arg, precision).pow(4) - tan.compute(arg, precision) + cos.compute(arg, precision)
        } else {
            ((((ln.compute(arg, precision) - log3.compute(arg, precision)) * log5.compute(arg, precision)) - log2.compute(
                arg,
                precision
            )) * log10.compute(arg, precision)) + ln.compute(arg, precision)
        }
    }
}