package XuLieHua;

/**
 * Created by flyex on 2018/10/15.
 */
public class Employee implements java.io.Serializable{
    public String name;
    public String address;
    public transient int ssn;
    public int number;
    public void mailCheck()
    {
        System.out.println("Mailing a check to " + name
                + " " + address);
    }
}
