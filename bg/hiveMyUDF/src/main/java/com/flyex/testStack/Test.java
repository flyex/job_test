package com.flyex.testStack;

public class Test {

    public static void main(String[] args) {

        Node node= new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        node.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        //node = node.next;

        Node n = node;
        n.next = n.next.next;

        Solution.output(node);
        System.out.println(n.t);


    }

}
