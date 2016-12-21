package almaz.murzabekov.math.parser.handlers;

import almaz.murzabekov.math.parser.BinaryOperatorParser;
import almaz.murzabekov.math.parser.ParseException;

public class MultiplyOperatorExecutor extends BinaryOperatorParser {
    public static final int PRIORITY = 3;
    public MultiplyOperatorExecutor() {
        super("*", PRIORITY, false);
    }

    @Override
    protected String execute(String left, String right) throws ParseException {
        try{
            double l = Double.parseDouble(left);
            double r = Double.parseDouble(right);

            return (l * r) + "";
        } catch (NumberFormatException ex){
            throw new ParseException(ex);
        }
    }
}
