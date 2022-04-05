package com.niuke;

/**
 * @author dengyong
 * @description
 * @create 2022/3/2 13:42
 */
public class Test3 {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node3 = new Node(3);
        Node node5 = new Node(5);
        Node node2 = new Node(2);
        Node node4 = new Node(4);
        Node node6 = new Node(6);
        node1.next = node3;
        node3.next = node5;

        node2.next = node4;
        node4.next = node6;
        Node rNode = test(node1,node2);

        while (rNode != null){
            System.out.println(rNode.val);
            rNode = rNode.next;
        }
    }

    private static Node test(Node node1, Node node2) {
        if (node1 == null){
            return node2;
        }

        if (node2 == null){
            return node1;
        }

        if (node1.val < node2.val){
            node1.next = test(node1.next, node2);
            return node1;
        }else{
            node2.next = test(node1, node2.next);
            return node2;
        }
    }
}

class Node{
    int val;
    Node next = null;
    public Node(int val){
        this.val = val;
    }
}
