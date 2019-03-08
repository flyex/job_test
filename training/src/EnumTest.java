/**
 * Created by flyex on 2018/10/23.
 */
enum EnumTest {
    lamborghini(888),tata(123),audi(222);
    private int price;
    EnumTest(int p){
        price=p;
    }
    int getPrice(){
        return price;
    }
    public static void main(String[] args){
       for(EnumTest t:EnumTest.values()){
           System.out.println(t+"的价格是："+t.getPrice());
       }
    }
}
