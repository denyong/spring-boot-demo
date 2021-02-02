package com.duqi;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: dengyong
 * @Date: 2021/01/28/14:27
 * @Description:
 */
public class Solution {

  public static void main(String[] args) {
    TreeNode t1 = new TreeNode(1);
    TreeNode t2 = new TreeNode(2);
    TreeNode t3 = new TreeNode(1);
    TreeNode t4 = new TreeNode(1);
    TreeNode t5 = new TreeNode(1);
    TreeNode t6 = new TreeNode(1);
    TreeNode t7 = new TreeNode(1);
    TreeNode t8 = new TreeNode(1);
    TreeNode t9 = new TreeNode(1);
    t1.left = t2;
//    t1.right = t3;
//    t2.left = t4;
//    t2.right = t5;
//    t3.left = t6;
//    t3.right = t7;
//    t4.left = t8;
//    t4.right = t9;
//    System.out.println(treeDeph(t1));
//    preTest(t1);
    System.out.println(isSymmetric(t1));
  }
  public static int treeDeph(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int left = treeDeph(root.left);
    int right = treeDeph(root.right);
    return left > right ? left + 1 : right + 1;
  }


  public static void preTest(TreeNode root){
    if (root==null){
      return;
    }
    System.out.println(root.val);
    preTest(root.left);
    preTest(root.right);

  }


  public static boolean isSymmetric (TreeNode root) {
    if (root == null ){
      return true;
    }

    List<Integer> left = new ArrayList<>();
    pre(root.left,left);
    List<Integer> right = new ArrayList<Integer>();
    pre(root.right,right);
    if (left.size() != right.size()){
      return false;
    }
    for (int i : left){
      if (left.get(i) != right.get(i)){
        return false;
      }
    }

    return true;
  }



  public static void pre(TreeNode root,List<Integer> list){
    if (root == null){
      return;
    }
    list.add(root.val);
    pre(root.left,list);
    pre(root.right,list);
  }
}

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  public TreeNode(int val) {
    this.val = val;
  }
}
