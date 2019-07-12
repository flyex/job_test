package com.flyex.totalFlow;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements Writable {
    private int upFlow;
    private int dFlow;
    private String phone;
    private int totalFlow;

    public FlowBean() {
    }

    public FlowBean(String phone,int upFlow,int dFlow) {
        this.upFlow = upFlow;
        this.dFlow = dFlow;
        this.phone = phone;
        this.totalFlow = upFlow + dFlow;
    }

    public int getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(int upFlow) {
        this.upFlow = upFlow;
    }

    public int getdFlow() {
        return dFlow;
    }

    public void setdFlow(int dFlow) {
        this.dFlow = dFlow;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalFlow() {
        return totalFlow;
    }



    public void readFields(DataInput dataInput) throws IOException{
        this.upFlow = dataInput.readInt();
        this.phone = dataInput.readUTF();
        this.dFlow = dataInput.readInt();
        this.totalFlow = dataInput.readInt();
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(upFlow);
        dataOutput.writeUTF(phone);
        dataOutput.writeInt(dFlow);
        dataOutput.writeInt(totalFlow);
    }

    @Override
    public String toString() {
        return this.phone+"\t"+upFlow+"\t"+dFlow+"\t"+totalFlow;
    }
}
