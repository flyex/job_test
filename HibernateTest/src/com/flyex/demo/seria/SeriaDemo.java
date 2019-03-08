package com.flyex.demo.seria;
import java.io.*;
public class SeriaDemo implements Serializable{


    private int width;
    private int height;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width){
            this.width  = width;
        }
        public void setHeight(int height){
            this.height = height;
        }

        @Override
        public String toString(){
            return "width="+width+"height="+height;
        }


        public static void main(String[] args){
            SeriaDemo myBox = new SeriaDemo();
            myBox.setWidth(50);
            myBox.setHeight(30);

            try{
                FileOutputStream fs = new FileOutputStream("foo.ser");
                ObjectOutputStream os =  new ObjectOutputStream(fs);
                os.writeObject(myBox);
                os.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }


}
