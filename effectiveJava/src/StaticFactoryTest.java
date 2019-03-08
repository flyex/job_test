import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class StaticFactoryTest {
    private final int age;
    private final int name;
    private final String birth;
    private final String date;



    public static class Builder1{
        private final int age;
        private final int name;
        

        private  String birth ;
        private  String date ;

        public Builder1(int a,int b) {
            this.age = a;
            this.name = b;
        }
            public Builder1 birth (String a){
                birth = a;
                return this;
            }
            public Builder1 date (String a){
                date = a;
                return this;
            }

            public StaticFactoryTest build (){
                return new StaticFactoryTest(this);
            }
        }
        private StaticFactoryTest(Builder1 builder){
            name=builder.name;
            age=builder.age;
            birth=builder.birth;
            date=builder.date;

        }

    public static void main(String[] args){
       StaticFactoryTest user = new Builder1(10,10).birth("123").date("abc").build();
       StaticFactoryTest user2 = new Builder1(12,14).birth("222").build();
       System.out.println(user.age+user2.date);
    }
}
