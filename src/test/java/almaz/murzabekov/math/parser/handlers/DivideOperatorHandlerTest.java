package almaz.murzabekov.math.parser.handlers;

import almaz.murzabekov.math.parser.BinaryOperatorParser;
import almaz.murzabekov.math.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class DivideOperatorHandlerTest {
    BinaryOperatorParser handler = new DivideOperatorExecutor();
    double dmax = Double.MAX_VALUE;
    double dmin = Double.MIN_VALUE;


    @Test
    public void test1() throws ParseException {
        Assert.assertEquals("1.0", handler.execute("2/2"));
    }
    @Test
    public void test2() throws ParseException {
        Assert.assertEquals("1.0", handler.execute(" 2 / 2 "));
    }
    @Test
    public void test3() throws ParseException {
        Assert.assertEquals("1.0", handler.execute("  2          /2       "));
    }
    @Test
    public void test4() throws ParseException {
        Assert.assertEquals("1.0", handler.execute("1/1"));
    }
    @Test
    public void test5() throws ParseException {
        Assert.assertEquals("0.0", handler.execute("0/1"));
    }
    @Test(expected = ParseException.class)
    public void test6() throws ParseException {
        handler.execute("1/0");
    }
    @Test(expected = ParseException.class)
    public void test6_1() throws ParseException {
        handler.execute("SOME_TRASH/1");
    }
    @Test(expected = ParseException.class)
    public void test6_2() throws ParseException {
        handler.execute("1/SOME_TRASH");
    }
    @Test(expected = ParseException.class)
    public void test6_3() throws ParseException {
        handler.execute("SOME_TRASH/SOME_TRASH");
    }
    /***
     * This is test case when we try parse expression like 4.9E-324.0 / 1
     *
     * Unfortunately, in this version this parsing not supported
     *
     * TODO: Implement, fix this bug
     * @throws ParseException
     * @Test
     */
    @Deprecated
    public void test7() throws ParseException {
        String expected = String.valueOf(dmax / dmin);
        String actual = handler.execute(dmax + " / " + dmin);

        Assert.assertEquals(expected, actual);
    }
    /***
     * This is test case when we try parse expression like 4.9E-324.0 / 1
     *
     * Unfortunately, in this version this parsing not supported
     *
     * TODO: Implement, fix this bug
     * @throws ParseException
     * @Test(expected = ParseException.class)
     */
    @Deprecated
    public void test8() throws ParseException {
        String actual = handler.execute(dmin + " / " + dmax);
    }
    /***
     * This is test case when we try parse expression like 4.9E-324.0 / 1
     *
     * Unfortunately, in this version this parsing not supported
     *
     * TODO: Implement, fix this bug
     * @throws ParseException
     * @Test
     */
    @Deprecated
    public void test9() throws ParseException {
        String expected = (dmin / 1.0) + "" ;
        String actual = handler.execute(dmin + " / " + 1);

        Double dexpected = Double.parseDouble(expected);
        Double dactual = Double.parseDouble(actual);

        Assert.assertEquals(dexpected, dactual);
    }
    /***
     * This is test case when we try parse expression like 4.9E-324.0 / 1
     *
     * Unfortunately, in this version this parsing not supported
     *
     * TODO: Implement, fix this bug
     * @throws ParseException
     * @Test
     */
    @Deprecated
    public void test10() throws ParseException {
        String expected = String.valueOf(dmax / 1);
        String actual = handler.execute(dmax + " / " + 1);

        Assert.assertEquals(expected, actual);
    }
    /***
     * This is test case when we try parse expression like 4.9E-324.0 / 1
     *
     * Unfortunately, in this version this parsing not supported
     *
     * TODO: Implement, fix this bug
     * @throws ParseException
     * @Test
     */
    @Deprecated
    public void test11() throws ParseException {
        String expected = String.valueOf(1 / dmax);
        String actual = handler.execute(1 + "/" + dmax);

        double dexpected = Double.parseDouble(expected);
        double dactual = Double.parseDouble(actual);

        Assert.assertEquals(dexpected, dactual, 0.000001);
    }
    /***
     * This is test case when we try parse expression like 4.9E-324.0 / 1
     *
     * Unfortunately, in this version this parsing not supported
     *
     * TODO: Implement, fix this bug
     * @throws ParseException
     * @Test
     */
    @Deprecated
    public void test12() throws ParseException {
        String expected = String.valueOf(1 / dmin);
        String actual = handler.execute(1 + "/" + dmin);

        Assert.assertEquals(expected, actual);
    }
    @Test()
    public void test13() throws ParseException {
        String expected= "(SOME_TRASH + SOME_TRASH * SOME_TRASH) - 2";
        String actual = handler.execute(expected);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test14() throws ParseException {
        String expression = "2 + 1 / 1";
        String expected = "2+1.0";
        String actual = handler.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test15() throws ParseException {
        String expression = "2 / 1 - 1";
        String expected = "2.0-1";
        String actual = handler.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test16() throws ParseException {
        String expression = "2 / 1 / 1";
        String expected = "2.0";
        String actual = handler.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test17() throws ParseException {
        String expression = "2 / 1 - 10 / 2";
        String expected = "2.0-5.0";
        String actual = handler.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test18() throws ParseException {
        String expression = "7 / 2 - 11 / 2 + 8 / 2";
        String expected = "3.5-5.5+4.0";
        String actual = handler.execute(expression);

        Assert.assertEquals(expected, actual);
    }
}
