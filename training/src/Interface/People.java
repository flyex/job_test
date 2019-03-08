package Interface;

/**
 * Created by flyex on 2018/10/11.
 */
public class People {
    public void description(String description){
        System.out.println("go"+description);
    }
    public void feedWater(Animal animal,String description){
        this.description(description);
        animal.move(description);
        animal.drink();
    }

    public static void main(String[] args){
        Tiger tiger = new Tiger();
        People people = new People();
        people.feedWater(tiger,"room");
    }
}
