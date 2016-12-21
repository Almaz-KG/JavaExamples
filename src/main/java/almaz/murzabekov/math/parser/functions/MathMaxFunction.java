package almaz.murzabekov.math.parser.functions;

import almaz.murzabekov.math.parser.BinaryOperatorParser;
import almaz.murzabekov.math.parser.ParseException;

public class MathMaxFunction extends BinaryOperatorParser {
    public static final String FUNCTION_NAME = "max";
    public static final int PRIORITY = 5;

    protected MathMaxFunction() {
        super(FUNCTION_NAME, PRIORITY, true);
    }

    @Override
    protected String execute(String lhs, String rhs) throws ParseException {
        try {
            Double l = Double.parseDouble(lhs);
            Double r = Double.parseDouble(rhs);

            return l > r ? l.toString() : r.toString();
        } catch (NumberFormatException ex) {
            throw new ParseException(ex);
        }
    }

    @Override
    protected String getRightOutside(String expression) throws ParseException {
        try {
            return expression.substring(getLastIndex(expression) + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ParseException(e);
        }
    }
    @Override
    protected String getLeftOutside(String expression) throws ParseException {
        try {
            return expression.substring(0, getFirstIndex(expression));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ParseException(e);
        }
    }

    private int getLastIndex(String expression) {
        int index = getFirstIndex(expression) + FUNCTION_NAME.length();
        int  result = expression.indexOf(')', index);
        return result;
    }

    private int getFirstIndex(String expression) {
        return super.getOperatorIndex(expression);
    }
}
