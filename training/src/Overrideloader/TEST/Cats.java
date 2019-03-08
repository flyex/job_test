package Overrideloader.TEST;

/**
 * Created by flyex on 2018/9/27.
 */
 class Cats extends Animals {

    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
    public void work(){
        System.out.println("猫捉老鼠");
    }
}
