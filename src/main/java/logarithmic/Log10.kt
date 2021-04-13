package logarithmic

import java.io.PrintWriter

open class Log10(private val ln: Ln, _out: PrintWriter? = null) : Log(10.0, _out) {
    override fun log(arg: Double, precision: Double): Double {
        return ln.compute(arg, precision) / ln.compute(base, precision)
    }
}