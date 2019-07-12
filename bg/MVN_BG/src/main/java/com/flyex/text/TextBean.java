package com.flyex.text;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TextBean implements WritableComparable<TextBean> {
    private int num;

    public void set(int mun){
        this.num = num;
    }
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void write(DataOutput dataOutput) throws IOException{
        dataOutput.writeInt(num);
    }

    public void readFields(DataInput dataInput) throws IOException{
        this.num = dataInput.readInt();
    }

    public int compareTo(TextBean o) {
        return o.getNum()-this.getNum()>0?1:-1;
    }

    @Override
    public String toString() {
        return this.getNum()+"**";
    }
}
