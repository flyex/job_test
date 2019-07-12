package com.my.flow;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class FlowBean implements WritableComparable<FlowBean> {
    private String phone;
    private int upflow;
    private int dflow;
    private int totalflow;

    public FlowBean() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUpflow() {
        return upflow;
    }

    public void setUpflow(int upflow) {
        this.upflow = upflow;
    }

    public int getDflow() {
        return dflow;
    }

    public void setDflow(int dflow) {
        this.dflow = dflow;
    }

    public int getTotalflow() {
        return totalflow;
    }

    public void setTotalflow(int totalflow) {
        this.totalflow = totalflow;
    }

    public FlowBean(String phone, int upflow, int dflow, int totalflow) {
        this.phone = phone;
        this.upflow = upflow;
        this.dflow = dflow;
        this.totalflow = upflow+dflow;
    }

    public void readFields(DataInput dataInput) throws IOException {
        this.phone = dataInput.readUTF();
        this.upflow = dataInput.readInt();
        this.dflow = dataInput.readInt();
        this.totalflow = dataInput.readInt();
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.phone);
        dataOutput.writeInt(this.upflow);
        dataOutput.writeInt(this.dflow);
        dataOutput.writeInt(this.totalflow);
    }

    public int compareTo(FlowBean o) {
        return o.totalflow-this.totalflow==0?this.getPhone().compareTo(o.getPhone()):o.getTotalflow()-this.totalflow;
    }

    @Override
    public String toString() {
        return phone+"\t"+upflow+"\t"+dflow+"\t"+totalflow;
    }
}
