package com.flyex.groupOrder.nb;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderBean2 implements WritableComparable<OrderBean2> {
    private String orderId;
    private String userId;
    private String pdtName;
    private float price;
    private int number;
    private float total;

    public void set(String orderId, String userId, String pdtName, float price, int number) {
        this.orderId = orderId;
        this.userId = userId;
        this.pdtName = pdtName;
        this.price = price;
        this.number = number;
        this.total = price * number;
    }
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPdtName() {
        return pdtName;
    }

    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.orderId);
        dataOutput.writeUTF(this.userId);
        dataOutput.writeUTF(this.pdtName);
        dataOutput.writeFloat(this.price);
        dataOutput.writeInt(this.number);
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.orderId = dataInput.readUTF();
        this.userId = dataInput.readUTF();
        this.pdtName = dataInput.readUTF();
        this.price = dataInput.readFloat();
        this.number = dataInput.readInt();
        this.total = this.price * this.number;
    }

    public int compareTo(OrderBean2 o) {
        if (o.getOrderId().compareTo(this.getOrderId())==0){
            if (o.getTotal()-this.getTotal()==0){
                return o.getPdtName().compareTo(this.getPdtName());
            }else {
                return Float.compare(o.getTotal(),this.getTotal());
            }
        }else {
            return this.getOrderId().compareTo(o.getOrderId());
        }
    }

    @Override
    public String toString() {
        return this.orderId + "," + this.userId + "," + this.pdtName + "," + this.price + "," + this.number + "------>"
                + this.total;
    }
}
