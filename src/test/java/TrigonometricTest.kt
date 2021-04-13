import common.DEFAULT_PRECISION
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import trigonometric.*
import java.io.FileOutputStream
import java.io.PrintWriter
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.sin
import kotlin.math.tan

class TrigonometricTest {
    companion object {
        private const val OUT_FILES = "src/test/resources/out/"
        private val cosMock: Cos = Mockito.mock(Cos::class.java)
        private val sinMock: Sin = Mockito.mock(Sin::class.java)
        private val tanMock: Tan = Mockito.mock(Tan::class.java)
        private val cos: Cos = PowerSeriesCos(PrintWriter(FileOutputStream("${OUT_FILES}cosResults.csv", true), true))
        private val sin: Sin = SinUsingCos(cos)
        private val tan: Tan = TanUsingCosAndSin(sin, cos)

        @BeforeAll
        @JvmStatic
        fun initMocks() {
            `when`(cosMock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.07073720166)
            `when`(cosMock.compute(2.0, DEFAULT_PRECISION)).thenReturn(-0.41614683654)
            `when`(cosMock.compute(0.1, DEFAULT_PRECISION)).thenReturn(0.99500416527)
            `when`(cosMock.compute(0.2, DEFAULT_PRECISION)).thenReturn(0.98006657784)
            `when`(cosMock.compute(0.5, DEFAULT_PRECISION)).thenReturn(0.87758256189)
            `when`(cosMock.compute(0.7, DEFAULT_PRECISION)).thenReturn(0.76484218728)
            `when`(cosMock.compute(0.822, DEFAULT_PRECISION)).thenReturn(0.68075755216)
            `when`(cosMock.compute(0.9, DEFAULT_PRECISION)).thenReturn(0.62160996827)
            `when`(cosMock.compute(1.1, DEFAULT_PRECISION)).thenReturn(0.45359612142)
            `when`(cosMock.compute(1.3, DEFAULT_PRECISION)).thenReturn(0.26749882862)
            `when`(cosMock.compute(1.7, DEFAULT_PRECISION)).thenReturn(-0.12884449429)
            `when`(cosMock.compute(3.14, DEFAULT_PRECISION)).thenReturn(-0.99999873172)
            `when`(cosMock.compute(5.1, DEFAULT_PRECISION)).thenReturn(0.37797774271)
            `when`(cosMock.compute(6.28, DEFAULT_PRECISION)).thenReturn(0.99999492691)
            `when`(cosMock.compute(7.4, DEFAULT_PRECISION)).thenReturn(0.43854732757)
            `when`(cosMock.compute(19.2, DEFAULT_PRECISION)).thenReturn(0.93922034669)
            `when`(cosMock.compute(100.0, DEFAULT_PRECISION)).thenReturn(0.86231887228)

            `when`(sinMock.compute(0.1, DEFAULT_PRECISION)).thenReturn(0.09983341664682815)
            `when`(sinMock.compute(0.2, DEFAULT_PRECISION)).thenReturn(0.19866933079506122)
            `when`(sinMock.compute(0.5, DEFAULT_PRECISION)).thenReturn(0.479425538604203)
            `when`(sinMock.compute(0.7, DEFAULT_PRECISION)).thenReturn(0.644217687237691)
            `when`(sinMock.compute(0.822, DEFAULT_PRECISION)).thenReturn(0.7325088089406709)
            `when`(sinMock.compute(0.9, DEFAULT_PRECISION)).thenReturn(0.7833269096274834)
            `when`(sinMock.compute(1.1, DEFAULT_PRECISION)).thenReturn(0.8912073600614354)
            `when`(sinMock.compute(1.3, DEFAULT_PRECISION)).thenReturn(0.963558185417193)
            `when`(sinMock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.9974949866040544)
            `when`(sinMock.compute(1.7, DEFAULT_PRECISION)).thenReturn(0.9916648104524686)
            `when`(sinMock.compute(2.0, DEFAULT_PRECISION)).thenReturn(0.9092974268256817)
            `when`(sinMock.compute(3.14, DEFAULT_PRECISION)).thenReturn(0.0015926529164868282)
            `when`(sinMock.compute(5.1, DEFAULT_PRECISION)).thenReturn(-0.9258146823277325)
            `when`(sinMock.compute(6.28, DEFAULT_PRECISION)).thenReturn(-0.0031853017931379904)
            `when`(sinMock.compute(7.4, DEFAULT_PRECISION)).thenReturn(0.8987080958116269)
            `when`(sinMock.compute(19.2, DEFAULT_PRECISION)).thenReturn(0.3433149288198954)
            `when`(sinMock.compute(100.0, DEFAULT_PRECISION)).thenReturn(-0.5063656411097588)

            `when`(tanMock.compute(0.1, DEFAULT_PRECISION)).thenReturn(0.10033467208545055)
            `when`(tanMock.compute(0.2, DEFAULT_PRECISION)).thenReturn(0.2027100355086725)
            `when`(tanMock.compute(0.5, DEFAULT_PRECISION)).thenReturn(0.5463024898437905)
            `when`(tanMock.compute(0.7, DEFAULT_PRECISION)).thenReturn(0.8422883804630794)
            `when`(tanMock.compute(0.822, DEFAULT_PRECISION)).thenReturn(1.0760200993956306)
            `when`(tanMock.compute(0.9, DEFAULT_PRECISION)).thenReturn(1.2601582175503392)
            `when`(tanMock.compute(1.1, DEFAULT_PRECISION)).thenReturn(1.9647596572486523)
            `when`(tanMock.compute(1.3, DEFAULT_PRECISION)).thenReturn(3.6021024479679786)
            `when`(tanMock.compute(1.5, DEFAULT_PRECISION)).thenReturn(14.101419947171719)
            `when`(tanMock.compute(1.7, DEFAULT_PRECISION)).thenReturn(-7.696602139459161)
            `when`(tanMock.compute(2.0, DEFAULT_PRECISION)).thenReturn(-2.185039863261519)
            `when`(tanMock.compute(3.14, DEFAULT_PRECISION)).thenReturn(-0.001592654936407223)
            `when`(tanMock.compute(5.1, DEFAULT_PRECISION)).thenReturn(-2.4493894155845988)
            `when`(tanMock.compute(6.28, DEFAULT_PRECISION)).thenReturn(-0.003185317952531891)
            `when`(tanMock.compute(7.4, DEFAULT_PRECISION)).thenReturn(2.049284169128086)
            `when`(tanMock.compute(19.2, DEFAULT_PRECISION)).thenReturn(0.3655318265062019)
            `when`(tanMock.compute(100.0, DEFAULT_PRECISION)).thenReturn(-0.5872139151569291)
        }
    }


    @ParameterizedTest
    @CsvFileSource(resources = ["costest.csv"])
    fun testCos(arg: Double, res: Double) {
        val calcRes = cos.compute(arg, DEFAULT_PRECISION)
        assertEquals(cos(arg), calcRes, DEFAULT_PRECISION)
        assertEquals(res, calcRes, DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["sintest.csv"])
    fun testSin(arg: Double, res: Double) {
        val calcRes = sin.compute(arg, DEFAULT_PRECISION)
        assertEquals(sin(arg), calcRes, DEFAULT_PRECISION)
        assertEquals(res, calcRes, DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["tantest.csv"])
    fun testTan(arg: Double, res: Double) {
        val calcRes = tan.compute(arg, DEFAULT_PRECISION)
        assertEquals(tan(arg), calcRes, DEFAULT_PRECISION)
        assertEquals(res, calcRes, DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["sintest.csv"])
    fun testSinIntegrated(arg: Double, res: Double) {
        val sin = SinUsingCos(cosMock)
        val calcRes = sin.compute(arg, DEFAULT_PRECISION)
        assertEquals(sin(arg), calcRes, DEFAULT_PRECISION)
        assertEquals(res, calcRes, DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["tantest.csv"])
    fun testTanIntegrated(arg: Double, res: Double) {
        val tan = TanUsingCosAndSin(sinMock, cosMock)
        val calcRes = tan.compute(arg, DEFAULT_PRECISION)
        assertEquals(tan(arg), calcRes, DEFAULT_PRECISION)
        assertEquals(res, calcRes, DEFAULT_PRECISION)
    }
}