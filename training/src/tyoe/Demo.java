package tyoe;

/**
 * Created by flyex on 2018/9/7.
 */
public class Demo {
    float f1 = 3.55f;
    Float F1 = new Float(f1);
    int A = F1.intValue();
    public void printAge(){
        System.out.println(A);
    }
    public static void main(String[] args){
        Demo demo = new Demo();
        demo.printAge();
    }
}
