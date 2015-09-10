package designpatterns.adapter.adapters;

import designpatterns.adapter.entities.homecats.HomeCat;
import designpatterns.adapter.entities.wildcats.WildCat;

/**
 * Created by Almaz on 11.09.2015.
 */
public class HomeCatAdapter implements HomeCat {
    private WildCat wildCat;

    public HomeCatAdapter(WildCat wildCat) {
        this.wildCat = wildCat;
    }

    @Override
    public String getName() {
        return wildCat.getBreed();
    }

    @Override
    public void setName(String name) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public void meow() {
        wildCat.growl();
    }

    @Override
    public void scratch() {
        wildCat.scratch();
    }
}
