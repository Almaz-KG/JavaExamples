package designpatterns.decorator;


import designpatterns.decorator.beverages.AbstractBeverage;
import designpatterns.decorator.beverages.BlackTea;
import designpatterns.decorator.beverages.Espresso;
import designpatterns.decorator.beverages.GreenTea;
import designpatterns.decorator.decorators.MilkCondiment;
import designpatterns.decorator.decorators.SugarCondiment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Almaz on 29.08.2015.
 */
public class Runner {
    public static void main(String[] args) {
        List<AbstractBeverage> beverages =
                new ArrayList<>();

        beverages.add(new Espresso());
        beverages.add(new BlackTea());
        beverages.add(new GreenTea());

        for (AbstractBeverage beverage : beverages) {
            print(beverage);
        }

        System.out.println("--------");
        AbstractBeverage capuccino = new SugarCondiment(new MilkCondiment(new Espresso()));
        print(capuccino);

        AbstractBeverage greenTeaWithSugar = new SugarCondiment(new GreenTea());
        print(greenTeaWithSugar);
    }

    public static void print(AbstractBeverage beverage){
        System.out.println("" +
                        "Beverage: " + beverage.getDescription() +
                        "\t\tPrice: " + beverage.getCost()
        );
    }
}
