package com.test.rabbitmq;

/**
 * @Auther: dengyong
 * @Date: 2021/03/16/22:23
 * @Description:
 */
public class Test {

  public static void main(String[] args) {
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    int[] arry={};
    int[] arry1={1,2,3};
    node1.next = node2;
    node2.next = node3;
    node3.next = node1;

  }
}


class Node{
  int val;
  Node next;
  public Node(int val){
    this.val = val;
  }
}
