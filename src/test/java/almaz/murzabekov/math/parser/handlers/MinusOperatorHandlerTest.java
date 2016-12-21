package almaz.murzabekov.math.parser.handlers;

import almaz.murzabekov.math.parser.BinaryOperatorParser;
import almaz.murzabekov.math.parser.ParseException;
import org.junit.Assert;
import org.junit.Test;

public class MinusOperatorHandlerTest {
    BinaryOperatorParser handler = new MinusOperatorExecutor();

    @Test
    public void test1() throws ParseException {
        Assert.assertEquals("0.0", handler.execute("1-1"));
    }
    @Test
    public void test2() throws ParseException {
        Assert.assertEquals("0.0", handler.execute("10000-10000"));
    }
}
