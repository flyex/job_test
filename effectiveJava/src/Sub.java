public class Sub extends Super {
    final String food;

    public Sub(){
         food ="肉";
    }
    @Override
    public void eat(){
        System.out.println("子类吃"+food);
    }

    public static void main(String[] args){
        Super s = new Sub(); //test
        s.eat();
    }

}
