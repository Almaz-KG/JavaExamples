package designpatterns.adapter.entities.wildcats;

/**
 * Created by Almaz on 11.09.2015.
 */
public class Tiger implements WildCat{
    @Override
    public String getBreed() {
        return "Тигр обыкновенный!";
    }

    @Override
    public void growl() {
        System.out.println("Гррррр!!!");
    }

    @Override
    public void scratch() {
        System.out.println("Очень острые когти, царапаюсь до смерти!");
    }
}
