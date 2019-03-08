package Interface;

/**
 * Created by flyex on 2018/10/11.
 */
public class Tiger extends BuRu {
    public static String name = "tiger";
    public String getName(){
        return this.name;
    }

    public void move(String description){
        System.out.println("tiger move to"+description);
    }

    public void drink(){
        System.out.println("tiger drink");
    }
}
