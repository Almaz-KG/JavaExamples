package designpatterns.factory.cars.parts.paints;

/**
 * Created by Almaz on 30.08.2015.
 */
public class WhitePaint extends AbstractPaint {
    private String name = "White paint";

    @Override
    public String toString() {
        return name;
    }
}
