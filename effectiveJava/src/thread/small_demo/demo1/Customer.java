package thread.small_demo.demo1;

public class Customer implements Runnable{
    private ServicesModule service;

    public Customer(ServicesModule servicesModule){
        this.service = servicesModule;
    }

    @Override
    public void run() {

        for (String message = service.get();!message.equals("have done");message = service.get()){
            System.out.format("%s,可以吃了%n",message);
            try {
                Thread.sleep(1000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
