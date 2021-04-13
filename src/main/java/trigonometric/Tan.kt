package trigonometric

import common.ComputableWritableModule
import java.io.PrintWriter

abstract class Tan(_out: PrintWriter?) : ComputableWritableModule(_out) {

    override fun calc(arg: Double, precision: Double): Double {
        return tan(arg, precision)
    }

    abstract fun tan(arg: Double, precision: Double): Double
}