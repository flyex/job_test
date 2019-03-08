package thread.tortoise_rabbit;

public class Demo {
    public static void main(String[] args){
        Rabbit rabbit = new Rabbit();
        Tortoise tortoise = new Tortoise();

        Interrupte rb = new Interrupte(rabbit);
        Interrupte to = new Interrupte(tortoise);

        rabbit.stop =  to;
        tortoise.stop = rb;

        rabbit.start();
        tortoise.start();






    }
}
