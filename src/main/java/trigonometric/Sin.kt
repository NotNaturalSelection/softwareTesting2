package trigonometric

import common.ComputableWritableModule
import java.io.PrintWriter

abstract class Sin(_out: PrintWriter?) : ComputableWritableModule(_out) {
    override fun calc(arg: Double, precision: Double): Double {
        return sin(arg, precision)
    }

    abstract fun sin(arg: Double, precision: Double): Double
}