package trigonometric

import java.io.PrintWriter

class TanUsingCosAndSin(_sin: Sin, _cos: Cos, _out: PrintWriter? = null) : Tan(_out) {
    private val sin: Sin = _sin
    private val cos: Cos = _cos

    override fun tan(arg: Double, precision: Double): Double {
        return sin.compute(arg, precision) / cos.compute(arg, precision)
    }
}