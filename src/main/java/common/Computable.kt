package common

interface Computable {
    fun compute(arg: Double): Double

    fun compute(arg: Double, precision: Double): Double

    fun validate(arg: Double) {
        if (!arg.isFinite()) {
            throw IllegalArgumentException("Argument must be a finite number")
        }
    }
}