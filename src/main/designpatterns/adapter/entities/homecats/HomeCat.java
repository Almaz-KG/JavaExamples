package designpatterns.adapter.entities.homecats;

/**
 * Created by Almaz on 10.09.2015.
 */
public interface HomeCat {
    String getName();
    void setName(String name);

    void meow();
    void scratch();
}
