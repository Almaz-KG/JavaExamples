package almaz.murzabekov.math.parser.handlers;

import almaz.murzabekov.math.parser.BinaryOperatorParser;
import almaz.murzabekov.math.parser.ParseException;

public class PlusOperatorExecutor extends BinaryOperatorParser {
    public static final int PRIORITY = 1;

    public PlusOperatorExecutor() {
        super("+", PRIORITY, false);
    }

    @Override
    protected String execute(String left, String right) throws ParseException {
        try{
            double l = Double.parseDouble(left);
            double r = Double.parseDouble(right);

            return (l + r) + "";
        } catch (NumberFormatException ex){
            throw new ParseException(ex);
        }
    }
}
