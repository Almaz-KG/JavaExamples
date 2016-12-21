package almaz.murzabekov.math.parser.functions;

import almaz.murzabekov.math.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class MathMaxFunctionTest {
    MathMaxFunction function = new MathMaxFunction();

    @Test
    public void test1() throws ParseException {
        String expression = "max(4, 5)";

        String expected = "5.0";
        String actual = function.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test2() throws ParseException {
        String expression = "max(-1, 1)";

        String expected = "1.0";
        String actual = function.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test3() throws ParseException {
        String expression = "max(0, 0)";

        String expected = "0.0";
        String actual = function.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test4() throws ParseException {
        String expression = "max(-100, 1000)";

        String expected = "1000.0";
        String actual = function.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test5() throws ParseException {
        String expression = "5 + max(-100, 1000)";

        String expected = "5+1000.0";
        String actual = function.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void test6() throws ParseException {
        String expression = "5 + max(-100, 1000)";

        String expected = "5+1000.0";
        String actual = function.execute(expression);

        Assert.assertEquals(expected, actual);
    }
    @Test (expected = ParseException.class)
    public void test7() throws ParseException {
        String expression = "4 + max(TRASH, 1000)";
        String actual = function.execute(expression);
    }
    @Test (expected = ParseException.class)
    public void test8() throws ParseException {
        String expression = "1 + max(1, TRASH) + 2 / 2";
        String actual = function.execute(expression);
    }
    @Test (expected = ParseException.class)
    public void test9() throws ParseException {
        String expression = "max(NOT_WELL_FORMED_FUNCTION) - 1";
        String actual = function.execute(expression);
    }
    @Test (expected = ParseException.class)
    public void test10() throws ParseException {
        String expression = "max NOT_WELL_FORMED_FUNCTION, 2";
        String actual = function.execute(expression);
    }
    @Test (expected = ParseException.class)
    public void test11() throws ParseException {
        String expression = "max 1, 2";
        String actual = function.execute(expression);
    }

}
