package com.flyex.testNoUse;

import java.util.LinkedList;

public class CollectionsTest {

    public static void main(String[] args) {

        LinkedList<Integer> linkedList = new LinkedList<>();

        linkedList.add(1);
        linkedList.add(1);

        for (int i = 0; i < linkedList.size(); i++) {
            System.out.println(linkedList.get(i));
        };

    }
}
