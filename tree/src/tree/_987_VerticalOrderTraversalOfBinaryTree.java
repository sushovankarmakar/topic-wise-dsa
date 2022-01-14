package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class _987_VerticalOrderTraversalOfBinaryTree {

  public static void main(String[] args) {
    //TreeNode root = buildTree("1,2,3,null,null,4,6,null,5,null,null,7,null");
    TreeNode root = buildTree("10,20,30,40,60,90,100");

    List<Integer> op = verticalTraversal(root);
    System.out.println(op);

    //PriorityQueue<String> queue = new PriorityQueue<>();
//    List<Integer>  queue = new ArrayList<>();
//    queue.add(7);
//    queue.add(26);
//    queue.add(17);
//    queue.add(96);
//    queue.add(27);
//    Collections.sort(queue);
//    System.out.println(queue);
  }

  /**
   * SOLVE IN THIS ORDER :
   * 1. Vertical Traversal of Binary Tree (GFG & Leetcode variant)
   * 2. Top View of Binary Tree
   * 3. Bottom View of Binary Tree
   */

  public static class Tuple {
    TreeNode node;
    int vertical;
    int level;

    Tuple(TreeNode node, int vertical, int level) {
      this.node = node;
      this.vertical = vertical;
      this.level = level;
    }
  }

  public static List<Integer> verticalTraversal(TreeNode root) {

    Map<Integer, Map<Integer, List<Integer>>> map = new TreeMap<>();

    Queue<Tuple> queue = new LinkedList<>();
    queue.add(new Tuple(root, 0, 0));

    while (!queue.isEmpty()) {
      Tuple currTuple = queue.poll();
      TreeNode currNode  = currTuple.node;
      int currVertical = currTuple.vertical;
      int currLevel = currTuple.level;

      if (!map.containsKey(currVertical)) {
        map.put(currVertical, new TreeMap<>());
      }

      if (!map.get(currVertical).containsKey(currLevel)) {
        map.get(currVertical).put(currLevel, new ArrayList<>());
      }

      map.get(currVertical).get(currLevel).add(currNode.val);

      if (currNode.left != null)
        queue.offer(new Tuple(currNode.left, currVertical - 1, currLevel + 1));

      if (currNode.right != null)
        queue.offer(new Tuple(currNode.right, currVertical + 1, currLevel + 1));
    }

    List<List<Integer>> verticalOrder = new ArrayList<>();

    //System.out.println(map);

    ArrayList<Integer> topView = new ArrayList<>();
    for (Map<Integer, List<Integer>> innerMap : map.values()) {
      List<Integer> list = new ArrayList<>();
      for (List<Integer> values : innerMap.values()) {

        // for vertical order
        /*Collections.sort(values);
        System.out.println(values);
        list.addAll(values);*/

        // for top view
        topView.add(values.get(0));
        break;
      }
      verticalOrder.add(list);
    }

    /*ArrayList<Integer> verticalOrder = new ArrayList<>();
    for (Map<Integer, List<Integer>> innerMap : map.values()) {

      for (List<Integer> values : innerMap.values()) {
        verticalOrder.addAll(values);
      }
    }
    return verticalOrder;*/

    /*ArrayList<Integer> topView = new ArrayList<>();
    for (Map<Integer, List<Integer>> innerMap : map.values()) {

      List<Integer> list = new ArrayList<>();
      for (List<Integer> values : innerMap.values()) {
        list.addAll(values);
      }
      topView.add(list.get(0));
    }
    return topView;*/

    /*ArrayList<Integer> bottomView = new ArrayList<>();
    for (Map<Integer, List<Integer>> innerMap : map.values()) {

      List<Integer> list = new ArrayList<>();
      for (List<Integer> values : innerMap.values()) {
        list.addAll(values);
      }
      bottomView.add(list.get(list.size() - 1));
    }
    return bottomView;*/

    return topView;
  }

  private static TreeNode buildTree(String str) {
    if (str.length() == 0 || str.charAt(0) == 'N') return null;

    String[] ip = str.split(",");
    TreeNode root = new TreeNode(Integer.parseInt(ip[0]));
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);

    int i = 1;
    while (!queue.isEmpty() && i < ip.length) {

      TreeNode currNode = queue.poll();

      String currVal = ip[i];

      if (!currVal.equals("null")) {
        currNode.left = new TreeNode(Integer.parseInt(currVal));
        queue.add(currNode.left);

      }
      i++;

      if (i >= ip.length) break;

      currVal = ip[i];
      if (!currVal.equals("null")) {
        currNode.right = new TreeNode(Integer.parseInt(currVal));
        queue.add(currNode.right);
      }
      i++;
    }
    return root;
  }

}


