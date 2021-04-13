package common

import logarithmic.Ln
import logarithmic.Log
import trigonometric.Cos
import trigonometric.Sin
import trigonometric.Tan
import kotlin.reflect.KClass

enum class Modules(clazz: KClass<out Computable>) {
    SIN(Sin::class),
    COS(Cos::class),
    TAN(Tan::class),
    LN(Ln::class),
    LOG(Log::class)
}