package logarithmic

import common.ComputableWritableModule
import java.io.PrintWriter

abstract class Ln(_out: PrintWriter?) : ComputableWritableModule(_out) {

    override fun validate(arg: Double) {
        super.validate(arg)
        if (arg <= 0.0 || arg == 1.0) {
            throw IllegalArgumentException("Argument should be positive and not equal to one")
        }
    }

    override fun calc(arg: Double, precision: Double): Double {
        return ln(arg, precision)
    }

    abstract fun ln(arg: Double, precision: Double): Double
}