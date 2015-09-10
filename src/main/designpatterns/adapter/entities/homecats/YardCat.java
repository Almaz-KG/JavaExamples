package designpatterns.adapter.entities.homecats;

/**
 * Created by Almaz on 10.09.2015.
 */
public class YardCat implements HomeCat {
    private String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void meow() {
        System.out.println("Мяу мяу");
    }

    @Override
    public void scratch() {
        System.out.println("Я царапаюсь, но не сильно!");
    }
}
