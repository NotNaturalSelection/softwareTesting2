package common

import java.io.PrintWriter

abstract class ComputableWritableModule(private var outWriter: PrintWriter? = null) : Computable, Writable {

    override fun compute(arg: Double): Double {
        return compute(arg, DEFAULT_PRECISION)
    }

    override fun compute(arg: Double, precision: Double): Double {
        validate(arg)
        val res = calc(arg, precision)
        if (outWriter != null) {
            writeToOutput(arg, res, outWriter!!)
        }
        return res
    }

    abstract fun calc(arg: Double, precision: Double): Double
}