import common.DEFAULT_PRECISION
import common.Expression
import logarithmic.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvFileSource
import org.mockito.Mockito
import trigonometric.*

class ExpressionTest {
    companion object {
        private const val OUT_FILES = "src/test/resources/out/"
        private val cosMock: Cos = Mockito.mock(Cos::class.java)
        private val sinMock: Sin = Mockito.mock(Sin::class.java)
        private val tanMock: Tan = Mockito.mock(Tan::class.java)
        private val lnMock: Ln = Mockito.mock(Ln::class.java)
        private val log2Mock: Log2 = Mockito.mock(Log2::class.java)
        private val log3Mock: Log3 = Mockito.mock(Log3::class.java)
        private val log5Mock: Log5 = Mockito.mock(Log5::class.java)
        private val log10Mock: Log10 = Mockito.mock(Log10::class.java)

        private val cos: Cos = PowerSeriesCos(/*PrintWriter(FileOutputStream("${OUT_FILES}cosResults.csv", true), true)*/)
        private val sin: Sin = SinUsingCos(cos)
        private val tan: Tan = TanUsingCosAndSin(sin, cos)
        private val ln: Ln = PowerSeriesLn()
        private val log2: Log2 = Log2(ln)
        private val log3: Log3 = Log3(ln)
        private val log5: Log5 = Log5(ln)
        private val log10: Log10 = Log10(ln)

        private val tanWithMocks: Tan = TanUsingCosAndSin(sinMock, cosMock)
        private val sinWithMock: Sin = SinUsingCos(cosMock)
        private val tanWithOnlyCosMock: Tan = TanUsingCosAndSin(sinWithMock, cosMock)

        @BeforeAll
        @JvmStatic
        fun initMocks() {
            Mockito.`when`(cosMock.compute(0.0, DEFAULT_PRECISION)).thenReturn(1.0)
            Mockito.`when`(cosMock.compute(-0.226, DEFAULT_PRECISION)).thenReturn(0.974570513346983)
            Mockito.`when`(cosMock.compute(-0.723, DEFAULT_PRECISION)).thenReturn(0.7498241949689665)
            Mockito.`when`(cosMock.compute(-1.0, DEFAULT_PRECISION)).thenReturn(0.5403023058681398)
            Mockito.`when`(cosMock.compute(-1.4, DEFAULT_PRECISION)).thenReturn(0.16996714290024104)
            Mockito.`when`(cosMock.compute(-1.515, DEFAULT_PRECISION)).thenReturn(0.055767380167282464)
            Mockito.`when`(cosMock.compute(-1.57, DEFAULT_PRECISION)).thenReturn(7.963267107332633E-4)
            Mockito.`when`(cosMock.compute(-1.58, DEFAULT_PRECISION)).thenReturn(-0.009203543268808336)
            Mockito.`when`(cosMock.compute(-1.7, DEFAULT_PRECISION)).thenReturn(-0.12884449429552464)
            Mockito.`when`(cosMock.compute(-1.8, DEFAULT_PRECISION)).thenReturn(-0.2272020946930871)
            Mockito.`when`(cosMock.compute(-2.0, DEFAULT_PRECISION)).thenReturn(-0.4161468365471424)
            Mockito.`when`(cosMock.compute(-3.0, DEFAULT_PRECISION)).thenReturn(-0.9899924966004454)
            Mockito.`when`(cosMock.compute(-4.0, DEFAULT_PRECISION)).thenReturn(-0.6536436208636119)
            Mockito.`when`(cosMock.compute(-4.7, DEFAULT_PRECISION)).thenReturn(-0.01238866346289056)

            Mockito.`when`(sinMock.compute(0.0, DEFAULT_PRECISION)).thenReturn(0.0)
            Mockito.`when`(sinMock.compute(-0.226, DEFAULT_PRECISION)).thenReturn(-0.22408104452317695)
            Mockito.`when`(sinMock.compute(-0.723, DEFAULT_PRECISION)).thenReturn(-0.6616371185469732)
            Mockito.`when`(sinMock.compute(-1.0, DEFAULT_PRECISION)).thenReturn(-0.8414709848078965)
            Mockito.`when`(sinMock.compute(-1.4, DEFAULT_PRECISION)).thenReturn(-0.9854497299884601)
            Mockito.`when`(sinMock.compute(-1.515, DEFAULT_PRECISION)).thenReturn(-0.998443788757924)
            Mockito.`when`(sinMock.compute(-1.57, DEFAULT_PRECISION)).thenReturn(-0.9999996829318346)
            Mockito.`when`(sinMock.compute(-1.58, DEFAULT_PRECISION)).thenReturn(-0.9999576464987401)
            Mockito.`when`(sinMock.compute(-1.7, DEFAULT_PRECISION)).thenReturn(-0.9916648104524686)
            Mockito.`when`(sinMock.compute(-1.8, DEFAULT_PRECISION)).thenReturn(-0.9738476308781951)
            Mockito.`when`(sinMock.compute(-2.0, DEFAULT_PRECISION)).thenReturn(-0.9092974268256817)
            Mockito.`when`(sinMock.compute(-3.0, DEFAULT_PRECISION)).thenReturn(-0.1411200080598672)
            Mockito.`when`(sinMock.compute(-4.0, DEFAULT_PRECISION)).thenReturn(0.7568024953079282)
            Mockito.`when`(sinMock.compute(-4.7, DEFAULT_PRECISION)).thenReturn(0.9999232575641008)

            Mockito.`when`(tanMock.compute(0.0, DEFAULT_PRECISION)).thenReturn(0.0)
            Mockito.`when`(tanMock.compute(-0.226, DEFAULT_PRECISION)).thenReturn(-0.2299279954137047)
            Mockito.`when`(tanMock.compute(-0.723, DEFAULT_PRECISION)).thenReturn(-0.8823896627853637)
            Mockito.`when`(tanMock.compute(-1.0, DEFAULT_PRECISION)).thenReturn(-1.5574077246549023)
            Mockito.`when`(tanMock.compute(-1.4, DEFAULT_PRECISION)).thenReturn(-5.797883715482887)
            Mockito.`when`(tanMock.compute(-1.515, DEFAULT_PRECISION)).thenReturn(-17.9037241083039)
            Mockito.`when`(tanMock.compute(-1.57, DEFAULT_PRECISION)).thenReturn(-1255.7655915007897)
            Mockito.`when`(tanMock.compute(-1.58, DEFAULT_PRECISION)).thenReturn(108.6492036048431)
            Mockito.`when`(tanMock.compute(-1.7, DEFAULT_PRECISION)).thenReturn(7.696602139459161)
            Mockito.`when`(tanMock.compute(-1.8, DEFAULT_PRECISION)).thenReturn(4.286261674628062)
            Mockito.`when`(tanMock.compute(-2.0, DEFAULT_PRECISION)).thenReturn(2.185039863261519)
            Mockito.`when`(tanMock.compute(-3.0, DEFAULT_PRECISION)).thenReturn(0.1425465430742778)
            Mockito.`when`(tanMock.compute(-4.0, DEFAULT_PRECISION)).thenReturn(-1.1578212823495777)
            Mockito.`when`(tanMock.compute(-4.7, DEFAULT_PRECISION)).thenReturn(-80.71276296747477)

            Mockito.`when`(lnMock.compute(0.1, DEFAULT_PRECISION)).thenReturn(-2.3025850929940455)
            Mockito.`when`(lnMock.compute(0.2, DEFAULT_PRECISION)).thenReturn(-1.6094379124341003)
            Mockito.`when`(lnMock.compute(0.5, DEFAULT_PRECISION)).thenReturn(-0.6931471805599453)
            Mockito.`when`(lnMock.compute(0.8, DEFAULT_PRECISION)).thenReturn(-0.2231435513142097)
            Mockito.`when`(lnMock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.4054651081081644)
            Mockito.`when`(lnMock.compute(2.0, DEFAULT_PRECISION)).thenReturn(0.6931471805599453)
            Mockito.`when`(lnMock.compute(2.3136, DEFAULT_PRECISION)).thenReturn(0.8388047529823085)
            Mockito.`when`(lnMock.compute(2.8, DEFAULT_PRECISION)).thenReturn(1.0296194171811581)
            Mockito.`when`(lnMock.compute(3.5, DEFAULT_PRECISION)).thenReturn(1.252762968495368)
            Mockito.`when`(lnMock.compute(5.0, DEFAULT_PRECISION)).thenReturn(1.6094379124341003)
            Mockito.`when`(lnMock.compute(5.523, DEFAULT_PRECISION)).thenReturn(1.7089211909190505)
            Mockito.`when`(lnMock.compute(5.7, DEFAULT_PRECISION)).thenReturn(1.7404661748405046)
            Mockito.`when`(lnMock.compute(6.0, DEFAULT_PRECISION)).thenReturn(1.791759469228055)
            Mockito.`when`(lnMock.compute(20.0, DEFAULT_PRECISION)).thenReturn(2.995732273553991)

            Mockito.`when`(lnMock.compute(2.0, DEFAULT_PRECISION)).thenReturn(0.69314718056)
            Mockito.`when`(lnMock.compute(3.0, DEFAULT_PRECISION)).thenReturn(1.09861228867)
            Mockito.`when`(lnMock.compute(5.0, DEFAULT_PRECISION)).thenReturn(1.60943791243)
            Mockito.`when`(lnMock.compute(10.0, DEFAULT_PRECISION)).thenReturn(2.30258509299)

            Mockito.`when`(log2Mock.compute(0.1, DEFAULT_PRECISION)).thenReturn(-3.321928094887362)
            Mockito.`when`(log2Mock.compute(0.2, DEFAULT_PRECISION)).thenReturn(-2.321928094887362)
            Mockito.`when`(log2Mock.compute(0.5, DEFAULT_PRECISION)).thenReturn(-1.0)
            Mockito.`when`(log2Mock.compute(0.8, DEFAULT_PRECISION)).thenReturn(-0.3219280948873623)
            Mockito.`when`(log2Mock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.5849625007211562)
            Mockito.`when`(log2Mock.compute(2.0, DEFAULT_PRECISION)).thenReturn(1.0)
            Mockito.`when`(log2Mock.compute(2.3136, DEFAULT_PRECISION)).thenReturn(1.2101394574016684)
            Mockito.`when`(log2Mock.compute(2.8, DEFAULT_PRECISION)).thenReturn(1.4854268271702415)
            Mockito.`when`(log2Mock.compute(3.5, DEFAULT_PRECISION)).thenReturn(1.8073549220576042)
            Mockito.`when`(log2Mock.compute(5.0, DEFAULT_PRECISION)).thenReturn(2.321928094887362)
            Mockito.`when`(log2Mock.compute(5.523, DEFAULT_PRECISION)).thenReturn(2.465452127408976)
            Mockito.`when`(log2Mock.compute(5.7, DEFAULT_PRECISION)).thenReturn(2.5109619192773796)
            Mockito.`when`(log2Mock.compute(6.0, DEFAULT_PRECISION)).thenReturn(2.584962500721156)
            Mockito.`when`(log2Mock.compute(20.0, DEFAULT_PRECISION)).thenReturn(4.321928094887363)

            Mockito.`when`(log3Mock.compute(0.1, DEFAULT_PRECISION)).thenReturn(-2.0959032742893844)
            Mockito.`when`(log3Mock.compute(0.2, DEFAULT_PRECISION)).thenReturn(-1.4649735207179269)
            Mockito.`when`(log3Mock.compute(0.5, DEFAULT_PRECISION)).thenReturn(-0.6309297535714574)
            Mockito.`when`(log3Mock.compute(0.8, DEFAULT_PRECISION)).thenReturn(-0.20311401357501224)
            Mockito.`when`(log3Mock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.3690702464285425)
            Mockito.`when`(log3Mock.compute(2.0, DEFAULT_PRECISION)).thenReturn(0.6309297535714574)
            Mockito.`when`(log3Mock.compute(2.3136, DEFAULT_PRECISION)).thenReturn(0.7635129896455318)
            Mockito.`when`(log3Mock.compute(2.8, DEFAULT_PRECISION)).thenReturn(0.9371999820149524)
            Mockito.`when`(log3Mock.compute(3.5, DEFAULT_PRECISION)).thenReturn(1.1403139955899648)
            Mockito.`when`(log3Mock.compute(5.0, DEFAULT_PRECISION)).thenReturn(1.4649735207179269)
            Mockito.`when`(log3Mock.compute(5.523, DEFAULT_PRECISION)).thenReturn(1.5555271031883704)
            Mockito.`when`(log3Mock.compute(5.7, DEFAULT_PRECISION)).thenReturn(1.5842405849569907)
            Mockito.`when`(log3Mock.compute(6.0, DEFAULT_PRECISION)).thenReturn(1.6309297535714573)
            Mockito.`when`(log3Mock.compute(20.0, DEFAULT_PRECISION)).thenReturn(2.7268330278608417)

            Mockito.`when`(log5Mock.compute(0.1, DEFAULT_PRECISION)).thenReturn(-1.430676558073393)
            Mockito.`when`(log5Mock.compute(0.2, DEFAULT_PRECISION)).thenReturn(-1.0)
            Mockito.`when`(log5Mock.compute(0.5, DEFAULT_PRECISION)).thenReturn(-0.43067655807339306)
            Mockito.`when`(log5Mock.compute(0.8, DEFAULT_PRECISION)).thenReturn(-0.1386468838532139)
            Mockito.`when`(log5Mock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.25192963641259225)
            Mockito.`when`(log5Mock.compute(2.0, DEFAULT_PRECISION)).thenReturn(0.43067655807339306)
            Mockito.`when`(log5Mock.compute(2.3136, DEFAULT_PRECISION)).thenReturn(0.521178696302554)
            Mockito.`when`(log5Mock.compute(2.8, DEFAULT_PRECISION)).thenReturn(0.6397385131955605)
            Mockito.`when`(log5Mock.compute(3.5, DEFAULT_PRECISION)).thenReturn(0.7783853970487746)
            Mockito.`when`(log5Mock.compute(5.0, DEFAULT_PRECISION)).thenReturn(1.0)
            Mockito.`when`(log5Mock.compute(5.523, DEFAULT_PRECISION)).thenReturn(1.061812436327222)
            Mockito.`when`(log5Mock.compute(5.7, DEFAULT_PRECISION)).thenReturn(1.081412436847743)
            Mockito.`when`(log5Mock.compute(6.0, DEFAULT_PRECISION)).thenReturn(1.1132827525593785)
            Mockito.`when`(log5Mock.compute(20.0, DEFAULT_PRECISION)).thenReturn(1.8613531161467862)

            Mockito.`when`(log10Mock.compute(0.1, DEFAULT_PRECISION)).thenReturn(-0.9999999999999998)
            Mockito.`when`(log10Mock.compute(0.2, DEFAULT_PRECISION)).thenReturn(-0.6989700043360187)
            Mockito.`when`(log10Mock.compute(0.5, DEFAULT_PRECISION)).thenReturn(-0.30102999566398114)
            Mockito.`when`(log10Mock.compute(0.8, DEFAULT_PRECISION)).thenReturn(-0.09691001300805638)
            Mockito.`when`(log10Mock.compute(1.5, DEFAULT_PRECISION)).thenReturn(0.17609125905568124)
            Mockito.`when`(log10Mock.compute(2.0, DEFAULT_PRECISION)).thenReturn(0.30102999566398114)
            Mockito.`when`(log10Mock.compute(2.3136, DEFAULT_PRECISION)).thenReturn(0.3642882756144368)
            Mockito.`when`(log10Mock.compute(2.8, DEFAULT_PRECISION)).thenReturn(0.44715803134221915)
            Mockito.`when`(log10Mock.compute(3.5, DEFAULT_PRECISION)).thenReturn(0.5440680443502756)
            Mockito.`when`(log10Mock.compute(5.0, DEFAULT_PRECISION)).thenReturn(0.6989700043360187)
            Mockito.`when`(log10Mock.compute(5.523, DEFAULT_PRECISION)).thenReturn(0.7421750432236771)
            Mockito.`when`(log10Mock.compute(5.7, DEFAULT_PRECISION)).thenReturn(0.7558748556724914)
            Mockito.`when`(log10Mock.compute(6.0, DEFAULT_PRECISION)).thenReturn(0.7781512503836435)
            Mockito.`when`(log10Mock.compute(20.0, DEFAULT_PRECISION)).thenReturn(1.301029995663981)
        }
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["exptest.csv"])
    fun testExpressionWithMocks(arg: Double, res: Double) {
        val exp: Expression = Expression(cosMock, tanMock, lnMock, log2Mock, log3Mock, log5Mock, log10Mock)
        val resComp = exp.compute(arg, DEFAULT_PRECISION)
        assertEquals(res, resComp, DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["exptest.csv"])
    fun testExpressionIntegratedToBottomFunctions(arg: Double, res: Double) {
        val exp: Expression = Expression(cosMock, tanWithMocks, lnMock, log2, log3, log5, log10)
        val resComp = exp.compute(arg, DEFAULT_PRECISION)
        assertEquals(res, resComp, DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["exptest.csv"])
    fun testExpressionIntegratedWithOnlyCosMock(arg: Double, res: Double) {
        val exp: Expression = Expression(cosMock, tanWithOnlyCosMock, lnMock, log2, log3, log5, log10)
        val resComp = exp.compute(arg, DEFAULT_PRECISION)
        assertEquals(res, resComp, DEFAULT_PRECISION)
    }

    @ParameterizedTest
    @CsvFileSource(resources = ["exptest.csv"])
    fun testFullyIntegratedExpression(arg: Double, res: Double) {
        val exp: Expression = Expression(cos, tan, ln, log2, log3, log5, log10)
        val resComp = exp.compute(arg, DEFAULT_PRECISION)
        assertEquals(res, resComp, DEFAULT_PRECISION)
    }
}