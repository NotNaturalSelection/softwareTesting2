package common

import java.io.PrintWriter

interface Writable {

    fun writeToOutput(arg: Double, res: Double, out: PrintWriter) {
        out.write("$arg, $res\n")
        out.flush()
    }
}