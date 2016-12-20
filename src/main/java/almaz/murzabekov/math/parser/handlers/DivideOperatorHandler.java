package almaz.murzabekov.math.parser.handlers;

import almaz.murzabekov.math.parser.ParseException;

public class DivideOperatorHandler extends BinaryOperatorHandler{
    public static final int PRIORITY = 3;

    public DivideOperatorHandler() {
        super("/", PRIORITY);
    }

    @Override
    protected String execute(String left, String right) throws ParseException {
        try{
            double l = Double.parseDouble(left);
            double r = Double.parseDouble(right);

            if(r == 0)
                throw new ParseException("Divider is zero");

            if(Double.MIN_VALUE == l && Double.MAX_VALUE == r){
                throw new ParseException("Infinity result found");
            }

            return (l / r) + "";
        } catch (NumberFormatException ex){
            throw new ParseException(ex);
        }
    }
}
