package com.flyex.text;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class TestComparator extends WritableComparator {
    public TestComparator() {
        super(TextBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        TextBean t1 = (TextBean) a;
        TextBean t2 = (TextBean) b;

        return t1.getNum()-t2.getNum()==0?0:1;
    }
}
