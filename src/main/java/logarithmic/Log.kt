package logarithmic

import common.ComputableWritableModule
import java.io.PrintWriter

abstract class Log(val base: Double, _out: PrintWriter?) : ComputableWritableModule(_out) {
    override fun validate(arg: Double) {
        super.validate(arg)
        if (arg <= 0) {
            throw IllegalArgumentException("Argument should be positive")
        }
    }

    override fun calc(arg: Double, precision: Double): Double {
        return log(arg, precision)
    }

    abstract fun log(arg: Double, precision: Double): Double
}