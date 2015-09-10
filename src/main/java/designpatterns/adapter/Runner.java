package designpatterns.adapter;

import designpatterns.adapter.adapters.HomeCatAdapter;
import designpatterns.adapter.entities.homecats.HomeCat;
import designpatterns.adapter.entities.homecats.PedigreedCat;
import designpatterns.adapter.entities.homecats.YardCat;
import designpatterns.adapter.entities.wildcats.Tiger;
import designpatterns.adapter.entities.wildcats.WildCat;

/**
 * Created by Almaz on 10.09.2015.
 */
public class Runner {
    public static void main(String[] args) {
        HomeCat vaska = new YardCat();
        vaska.setName("Васька");
        CatInfoPrinter.printCatInfo(vaska);

        HomeCat wagner = new PedigreedCat();
        wagner.setName("Вагнер");
        CatInfoPrinter.printCatInfo(wagner);

        WildCat tiger = new Tiger();
        CatInfoPrinter.printCatInfo(new HomeCatAdapter(tiger));
    }
}
