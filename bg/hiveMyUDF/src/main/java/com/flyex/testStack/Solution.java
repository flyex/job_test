package com.flyex.testStack;

class Solution {

    //TODO 遍历反转
    public static Node traverseReverse(Node head){
        if (head == null || head.next == null){
            return head;
        }

        Node nextNode = head.next;
        Node tmp;
        Node finalNode = head;

        head.next = null;

        while (nextNode != null){
            tmp = nextNode.next;
            nextNode.next = finalNode;
            finalNode = nextNode;
            nextNode = tmp;
        }

        return finalNode;

    }

    //TODO 链表反转之 递归反转法
    public static Node recursiveReverse(Node currentNode){
        if (currentNode == null || currentNode.next == null){
            return currentNode;
        }
        Node returnNode = recursiveReverse(currentNode.next);

        //通过递归方法遍历到最后一个node
        //再把当前node的下一个node的next设为当前node
        currentNode.next.next = currentNode;
        currentNode.next = null;
        return returnNode;
    }

    //TODO 遍历链表
    public static void output(Node head){
        Node tmp = head;
        while (tmp != null){
            System.out.print(tmp.t + "->");
            tmp = tmp.next;
        }
    }

    //TODO 奇偶链表
    public static Node oddEvenSelect(Node head){

        if (head == null){
            return null;
        }

        Node odd = head;
        Node even = head.next;
        Node evenHead = head.next;

        while (odd.next != null&&even.next != null){
            odd.next = even.next;
            odd = odd.next;

            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }


    //TODO 索引链表
    public static int getIndex(Node<Integer> head, int index){
        int count = 0;
        Node tmp = head;
        while (tmp != null){
            if (count == index){
                return (Integer) tmp.t;
            }

            tmp = tmp.next;
            if (tmp == null) {
                System.out.println("index越界");
                return 0;
            }
            count++;
        }
        return 0;
    }

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

//        Node temp = node;
//        //不同之处
//        while (temp .next!= null) {
//            temp = temp.next;
//        }
//        temp.next = new Node(10);
//        System.out.println(node.next.next.next.next.t);

        //System.out.println(getIndex(node,5));

        node = oddEvenSelect(node);

        output(node);

        /**
            递归反转链表
         */
        //output(recursiveReverse(node));

        /**
         * 遍历反转链表
         */
        //output(traverseReverse(node));





    }
}
class Node<T> {
    Node next;
    T t;

    Node(T t) {
        this.t = t;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
