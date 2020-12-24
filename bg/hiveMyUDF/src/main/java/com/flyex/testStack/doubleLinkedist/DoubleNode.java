package com.flyex.testStack.doubleLinkedist;


import jodd.io.NetUtil;

public class DoubleNode {

    DNode head;
    DNode tail;

    int length;



    public  DoubleNode(DNode node){
        head = node;
        length = 0;
    }

    //TODO 判断空
    public boolean isEmpty(){
        if (length == 0){
            return true;
        }
        return false;
    }

    //TODO 增加节点
    public void addNode(DNode n){
        DNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = n;
        n.pre = temp;
        length = length + 1;
    }

    //TODO 遍历节点
    public void listNode(){
        DNode temp = head;
        if (isEmpty()){
            System.out.println("链表为空");
            return;
        }

        while (temp != null){
            System.out.print(temp.t + "-->");

            temp = temp.next;
        }
    }

    //TODO 删除节点
    public void deleteNode(DNode node){

        DNode temp = head;
        DNode res = null;

        if (isEmpty()){
            System.out.println("链表空 不能删除");
            return;
        }


        for (int i=0; i<length; i++){
            if (temp.next.t == node.t ){
                res = temp.next;
                break;
            }
            temp = temp.next;
        }

        if (res != null){
           if (res.next == null){
               res.pre.next = null;
               res.pre = null;
           }else {
               res.pre.next = res.next;
               res.pre = res.next.pre;
           }

        }else{
            System.out.println("要删除的节点不存在");
        }

    }



    public static void main(String[] args) {
        DNode headd = new DNode(null);

        DoubleNode linked = new DoubleNode(headd);

        DNode n1 = new DNode(1);
        DNode n2 = new DNode(2);
        DNode n3 = new DNode(3);
        DNode n4 = new DNode(4);

        //linked.listNode();

        linked.addNode(n1);
        linked.addNode(n2);
        linked.addNode(n3);
        //linked.addNode(n4);

        linked.listNode();
        System.out.println();

        linked.deleteNode(n1);

        linked.listNode();

    }

}
 class DNode<T>{

    T t;

    DNode next;

    DNode pre;

    public DNode(T t){
        this.t = t;
    }
}


