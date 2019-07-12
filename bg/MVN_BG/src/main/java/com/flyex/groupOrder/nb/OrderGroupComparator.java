package com.flyex.groupOrder.nb;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupComparator extends WritableComparator {
    public OrderGroupComparator(){
        super(OrderBean2.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean2 o1 = (OrderBean2) a;
        OrderBean2 o2 = (OrderBean2) b;
        return o1.getOrderId().compareTo(o2.getOrderId());
    }
}
