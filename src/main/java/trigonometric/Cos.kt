package trigonometric

import common.ComputableWritableModule
import java.io.PrintWriter

abstract class Cos(_out: PrintWriter?) : ComputableWritableModule(_out) {
    override fun calc(arg: Double, precision: Double): Double {
        return cos(arg, precision)
    }

    abstract fun cos(arg: Double, precision: Double): Double
}