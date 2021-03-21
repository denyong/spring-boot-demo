package com.duqi.algorithm.tree;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: dengyong
 * @Date: 2021/02/20/8:56
 * @Description:
 */
public class TreeDemo {

  public static void main(String[] args) {
    TreeNodeDemo treeNodeDemo = new TreeNodeDemo();
    TreeNodeDemo tree = treeNodeDemo.tree();
    System.out.println("先序遍历(递归方式): ");
    beforeErgodic(tree);
    System.out.println("先序遍历(非递归方式): ");
    beforeErgodic2(tree);
    System.out.println("中序遍历(递归方式): ");
    middleErgodic(tree);
    System.out.println("中序遍历(非递归方式): ");
    middleErgodic2(tree);
    System.out.println("后序遍历(递归方式): ");
    afterErgodic(tree);
    System.out.println("后序遍历(非递归方式): ");
    afterErgodic2(tree);
    System.out.println("层序遍历: ");
    sequenceErgodic(tree);
    System.out.println("重建二叉树:");
    int[] pre = {1, 2, 4, 8, 9, 5, 3, 6, 7};
    int[] in = {8, 4, 9, 2, 5, 1, 6, 3, 7};
    TreeNodeDemo tree1 = buildTree(pre, in);
    beforeErgodic(tree1);
    System.out.println("二叉树的深度：");
    System.out.println(dephTree(tree));
    System.out.println("判断一个树是否平衡二叉树(左右子树绝对值之差小于等于1)：");
    System.out.println(avlTree(tree));;
  }

  /**
   * 先序遍历(递归)
   */
  public static void beforeErgodic(TreeNodeDemo root) {
    if (root != null) {
      System.out.println(root.val);
      beforeErgodic(root.left);
      beforeErgodic(root.right);
    }
  }

  /**
   * 先序遍历(非递归)
   * 使用栈
   */
  public static void beforeErgodic2(TreeNodeDemo root) {
    Stack<TreeNodeDemo> stack = new Stack<>();
    TreeNodeDemo node = root;
    while (node != null || !stack.empty()) {
      if (node != null) {
        System.out.println(node.val);
        stack.push(node);
        node = node.left;
      } else {
        TreeNodeDemo pop = stack.pop();
        node = pop.right;
      }
    }
  }

  /**
   * 中序遍历(递归)
   */
  public static void middleErgodic(TreeNodeDemo root) {
    if (root != null) {
      middleErgodic(root.left);
      System.out.println(root.val);
      middleErgodic(root.right);
    }
  }

  /**
   * 中序遍历(非递归)
   */
  public static void middleErgodic2(TreeNodeDemo root) {
    Stack<TreeNodeDemo> stack = new Stack<>();
    TreeNodeDemo node = root;
    while (node != null || !stack.isEmpty()) {
      if (node != null) {
        stack.push(node);
        node = node.left;
      } else {
        TreeNodeDemo pop = stack.pop();
        System.out.println(pop.val);
        node = pop.right;
      }
    }
  }

  /**
   * 后序遍历(递归)
   */
  public static void afterErgodic(TreeNodeDemo root) {
    if (root != null) {
      afterErgodic(root.left);
      afterErgodic(root.right);
      System.out.println(root.val);
    }
  }


  /**
   * 后序遍历(非递归)
   */
  public static void afterErgodic2(TreeNodeDemo root) {
    TreeNodeDemo cur, pre = null;
    Stack<TreeNodeDemo> stack = new Stack<>();

    stack.push(root);

    while (!stack.isEmpty()) {
      cur = stack.peek();
      if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left
          || pre == cur.right))) {
        System.out.println(cur.val);
        stack.pop();
        pre = cur;
      } else {
        if (cur.right != null) {
          stack.push(cur.right);
        }

        if (cur.left != null) {
          stack.push(cur.left);
        }
      }
    }
  }


  /**
   * 层序遍历
   */
  public static void sequenceErgodic(TreeNodeDemo root) {
    if (root == null) {
      return;
    }

    Queue<TreeNodeDemo> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      TreeNodeDemo poll = queue.poll();
      System.out.println(poll.val);
      if (poll.left != null) {
        queue.add(poll.left);
      }

      if (poll.right != null) {
        queue.add(poll.right);
      }
    }
  }

  // 重建树
  // 先序遍历：1 2 4 8 9 5 3 6 7
  // 中序遍历: 8 4 9 2 5 1 6 3 7
  public static TreeNodeDemo buildTree(int[] pre, int[] in) {
    if (pre.length == 0 || in.length == 0) {
      return null;
    }

    TreeNodeDemo tree = new TreeNodeDemo(pre[0]);

    for (int i = 0; i < in.length; i++) {
      if (pre[0] == in[i]) {
        tree.left = buildTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
        tree.right = buildTree(Arrays.copyOfRange(pre, i + 1, pre.length),
            Arrays.copyOfRange(in, i + 1, in.length));
      }
    }
    return tree;

  }

  /**
   * 树的深度
   *
   * @param root
   * @return
   */
  public static int dephTree(TreeNodeDemo root) {
    if (root == null) {
      return 0;
    }
    int left = dephTree(root.left);
    int right = dephTree(root.right);

    return left > right ? left + 1 : right + 1;
  }

  /**
   * 判断平衡二叉树
   *
   * @return
   */
  public static boolean avlTree(TreeNodeDemo root) {
    int left = dephTree(root.left);
    int right = dephTree(root.right);
    if (Math.abs(left - right) == 1) {
      return true;
    }
    return false;

  }


}

class TreeNodeDemo {

  int val;
  TreeNodeDemo left;
  TreeNodeDemo right;

  public TreeNodeDemo(int val) {
    this.val = val;
  }

  public TreeNodeDemo() {
  }

  public TreeNodeDemo tree() {
    TreeNodeDemo t1 = new TreeNodeDemo(1);
    TreeNodeDemo t2 = new TreeNodeDemo(2);
    TreeNodeDemo t3 = new TreeNodeDemo(3);
    TreeNodeDemo t4 = new TreeNodeDemo(4);
    TreeNodeDemo t5 = new TreeNodeDemo(5);
    TreeNodeDemo t6 = new TreeNodeDemo(6);
    TreeNodeDemo t7 = new TreeNodeDemo(7);
    TreeNodeDemo t8 = new TreeNodeDemo(8);
    TreeNodeDemo t9 = new TreeNodeDemo(9);
//    TreeNodeDemo t10 = new TreeNodeDemo(10);
    t1.left = t2;
    t1.right = t3;
    t2.left = t4;
    t2.right = t5;
    t3.left = t6;
    t3.right = t7;
    t4.left = t8;
    t4.right = t9;
//    t8.right = t10;
    return t1;
  }
}

//if (s.length() % 2 == 1){
//    return false;
//    }
//
//    Stack<Character> stack = new Stack<Character>();
//    for (int i = 0;i<s.length();i++){
//    if(stack.empty()){
//    stack.add(s.charAt(i));
//    }else if(s.charAt(i) == stack.peek()){
//    stack.add(s.charAt(i));
//    }else{
//    if(s.charAt(i) == ')' && stack.peek() == '('){
//    stack.pop();
//    }else if (s.charAt(i) == '}' && stack.peek() == '{') {
//    stack.pop();
//    }else if (s.charAt(i) == ']' && stack.peek() == '[') {
//    stack.pop();
//    }
//    }
//    }
//    if (stack.empty()){
//    return true;
//    }else{
//    return false;
//    }
