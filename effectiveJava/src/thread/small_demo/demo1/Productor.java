package thread.small_demo.demo1;

import thread.tortoise_rabbit.Interrupte;

public class Productor implements Runnable{
    private ServicesModule service;

    public Productor(ServicesModule servicesModule){
        this.service = servicesModule;
    }

    @Override
    public void run() {
        String foods[] = {"包子", "饺子","白菜","汤圆"};
        for (int i=0;i<foods.length;i++){
            try {
                service.put(foods[i]);
                System.out.format("做好%s了休息会\n",foods[i]);

                Thread.currentThread().sleep(1000);

            }catch (Exception e){
                e.printStackTrace();
            }

        }
        service.put("have done");
    }
}
