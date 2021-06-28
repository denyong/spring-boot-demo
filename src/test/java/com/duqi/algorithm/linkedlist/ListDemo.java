package com.duqi.algorithm.linkedlist;

/**
 * @Auther: dengyong
 * @Date: 2021/02/25/9:05
 * @Description: 链表练习题
 */
public class ListDemo {

  public static void main(String[] args) {
    reverserNode();
    deleteNodeByIndex();
  }

  private static void reverserNode() {
    ListNode node1 = new ListNode(1);
    ListNode node2 = new ListNode(2);
    ListNode node3 = new ListNode(3);
    ListNode node4 = new ListNode(4);

    node1.next = node2;
    node2.next = node3;
    node3.next = node4;

    // 链表的反转
    ListNode pre = null; // 表示当前节点的上一个节点
    ListNode tail = null; // 表示当前节点的下一个节点

    while (node1 != null) {
      tail = node1.next;

      node1.next = pre;

      pre = node1;

      node1 = tail;
    }
  }

  /**
   * 删除链表的倒数第N个节点
   */
  public static void deleteNodeByIndex() {

  }
}


class ListNode {

  int val;
  ListNode next;

  public ListNode(int val) {
    this.val = val;
  }
}
