package designpatterns.factory.cars.parts.paints;

/**
 * Created by Almaz on 30.08.2015.
 */
public class BlackPaint extends AbstractPaint {
    private String name = "Black paint";

    @Override
    public String toString() {
        return name;
    }
}
