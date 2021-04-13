package logarithmic

interface TwoArgComputable {

    fun compute(arg: Double, base: Double): Double

    fun compute(arg: Double, base: Double, precision: Double): Double

    fun validate(arg: Double, base: Double) {
        if (!arg.isFinite() || !base.isFinite()) {
            throw IllegalArgumentException("Arguments must be finite numbers")
        }
    }
}