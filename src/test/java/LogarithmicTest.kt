import common.DEFAULT_PRECISION
import logarithmic.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.Mockito
import kotlin.math.ln
import kotlin.math.log


class LogarithmicTest {

    companion object {
        private val lnMock: Ln = Mockito.mock(Ln::class.java)

        @BeforeAll
        @JvmStatic
        fun initMocks() {
            Mockito.`when`(lnMock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.4054651081)
            Mockito.`when`(lnMock.compute(2.0, DEFAULT_PRECISION)).thenReturn(0.69314718056)
            Mockito.`when`(lnMock.compute(0.1, DEFAULT_PRECISION)).thenReturn(-2.30258509299)
            Mockito.`when`(lnMock.compute(0.2, DEFAULT_PRECISION)).thenReturn(-1.60943791243)
            Mockito.`when`(lnMock.compute(0.5, DEFAULT_PRECISION)).thenReturn(-0.69314718056)
            Mockito.`when`(lnMock.compute(0.7, DEFAULT_PRECISION)).thenReturn(-0.35667494393)
            Mockito.`when`(lnMock.compute(0.822, DEFAULT_PRECISION)).thenReturn(-0.19601488392)
            Mockito.`when`(lnMock.compute(0.9, DEFAULT_PRECISION)).thenReturn(-0.10536051565)
            Mockito.`when`(lnMock.compute(1.1, DEFAULT_PRECISION)).thenReturn(0.0953101798)
            Mockito.`when`(lnMock.compute(1.3, DEFAULT_PRECISION)).thenReturn(0.26236426446)
            Mockito.`when`(lnMock.compute(1.7, DEFAULT_PRECISION)).thenReturn(0.53062825106)
            Mockito.`when`(lnMock.compute(3.14, DEFAULT_PRECISION)).thenReturn(1.14422279992)
            Mockito.`when`(lnMock.compute(5.1, DEFAULT_PRECISION)).thenReturn(1.62924053973)
            Mockito.`when`(lnMock.compute(6.28, DEFAULT_PRECISION)).thenReturn(1.83736998048)
            Mockito.`when`(lnMock.compute(7.4, DEFAULT_PRECISION)).thenReturn(2.00148000021)
            Mockito.`when`(lnMock.compute(19.2, DEFAULT_PRECISION)).thenReturn(2.95491027903)
            Mockito.`when`(lnMock.compute(100.0, DEFAULT_PRECISION)).thenReturn(4.60517018599)
            Mockito.`when`(lnMock.compute(4.0, DEFAULT_PRECISION)).thenReturn(1.38629436112)
            Mockito.`when`(lnMock.compute(8.0, DEFAULT_PRECISION)).thenReturn(2.07944154168)
            Mockito.`when`(lnMock.compute(5.0, DEFAULT_PRECISION)).thenReturn(1.60943791243)
            Mockito.`when`(lnMock.compute(7.0, DEFAULT_PRECISION)).thenReturn(1.94591014906)
            Mockito.`when`(lnMock.compute(9.0, DEFAULT_PRECISION)).thenReturn(2.19722457734)
            Mockito.`when`(lnMock.compute(6.0, DEFAULT_PRECISION)).thenReturn(1.79175946923)
            Mockito.`when`(lnMock.compute(11.0, DEFAULT_PRECISION)).thenReturn(2.3978952728)
            Mockito.`when`(lnMock.compute(52.0, DEFAULT_PRECISION)).thenReturn(3.95124371858)
            Mockito.`when`(lnMock.compute(3.0, DEFAULT_PRECISION)).thenReturn(1.09861228867)
            Mockito.`when`(lnMock.compute(10.0, DEFAULT_PRECISION)).thenReturn(2.30258509299)
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["lntest.csv"])
    fun testLn(arg: Double, res: Double) {
        val ln: Ln = PowerSeriesLn()
        assertEquals(ln(arg), ln.compute(arg, DEFAULT_PRECISION), DEFAULT_PRECISION)
        assertEquals(res, ln.compute(arg, DEFAULT_PRECISION), DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["log2test.csv"])
    fun testLog2Integrated(arg: Double, res: Double) {
        val log2: Log2 = Log2(lnMock)
        assertEquals(log(arg, log2.base), log2.compute(arg, DEFAULT_PRECISION), DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["log3test.csv"])
    fun testLog3Integrated(arg: Double, res: Double) {
        val log: Log3 = Log3(lnMock)
        assertEquals(log(arg, log.base), log.compute(arg, DEFAULT_PRECISION), DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["log5test.csv"])
    fun testLog5Integrated(arg: Double, res: Double) {
        val log: Log5 = Log5(lnMock)
        assertEquals(log(arg, log.base), log.compute(arg, DEFAULT_PRECISION), DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["log10test.csv"])
    fun testLog10Integrated(arg: Double, res: Double) {
        val log: Log10 = Log10(lnMock)
        assertEquals(log(arg, log.base), log.compute(arg, DEFAULT_PRECISION), DEFAULT_PRECISION)
    }
}