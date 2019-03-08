package thread.tortoise_rabbit;

public class Interrupte implements Animals.Stop {
    Animals a;

    public Interrupte(Animals a){
        this.a = a;
    }
    @Override
    public void win(){
        a.stop();
    }
}
