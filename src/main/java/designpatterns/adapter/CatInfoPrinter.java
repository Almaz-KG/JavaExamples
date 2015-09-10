package designpatterns.adapter;

import designpatterns.adapter.entities.homecats.HomeCat;

/**
 * Created by Almaz on 10.09.2015.
 */
public class CatInfoPrinter {
    public static void printCatInfo(HomeCat cat) {
        System.out.println("Кошачье досье:");

        System.out.println("Имя кота: " + cat.getName());
        System.out.print("Вид мяуканья: ");
        cat.meow();
        System.out.print("Вид царапанья: ");
        cat.scratch();

        System.out.println();
    }
}
