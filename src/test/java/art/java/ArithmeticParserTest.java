package art.java;

import art.java.arithmeticparser.parser.Parser;
import art.java.arithmeticparser.parser.ParserException;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Almaz on 11.09.2015.
 */
public class ArithmeticParserTest {
    @Test
    public void arithmeticParserTest() throws Exception{
        Parser parser = new Parser();

        double result = parser.evaluate("1 + 1");
        assert (result == 2);

        result = parser.evaluate("2 - 1");
        assert (result == 1);

        result = parser.evaluate("1 * 1");
        assert (result == 1);

        result = parser.evaluate("0 * 1");
        assert (result == 0);

        result = parser.evaluate("0 / 1");
        assert (result == 0);
    }

    @Test(expected = ParserException.class)
    public void testDividingByZero() throws ParserException{
        new Parser().evaluate("5/0");
    }
}
