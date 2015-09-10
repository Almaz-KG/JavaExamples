package designpatterns.adapter.entities.homecats;

/**
 * Created by Almaz on 10.09.2015.
 */
public class PedigreedCat implements HomeCat {
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
        System.out.println("Уррррр уррррр");
    }

    @Override
    public void scratch() {
        System.out.println("Я не царапаюсь!");
    }
}
