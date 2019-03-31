package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class TreeUtils {
    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        List<String> serializedTreeNodeList = new LinkedList<>();

        if(root == null) {
            return "[null]";
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        serializedTreeNodeList.add(String.valueOf(root.val));

        while(!queue.isEmpty()) {
            int father = queue.size();
            while(father -- > 0) {
                TreeNode node = queue.poll();
                if(node.left != null) {
                    queue.add(node.left);
                    serializedTreeNodeList.add(String.valueOf(node.left.val));
                } else {
                    serializedTreeNodeList.add("null");
                }

                if(node.right != null) {
                    queue.add(node.right);
                    serializedTreeNodeList.add(String.valueOf(node.right.val));
                } else {
                    serializedTreeNodeList.add("null");
                }
            }
        }

        while(((LinkedList<String>) serializedTreeNodeList).peekLast().equals("null")) {
            serializedTreeNodeList.remove(serializedTreeNodeList.size()-1);
        }

        return "[" + serializedTreeNodeList.stream().collect(Collectors.joining(",")) + "]";
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        TreeNode root = null;
        String[] serializedNodes = data.substring(1, data.length()-1).split(",");
        if(serializedNodes.length > 0 && !serializedNodes[0].equals("null")) {
            root = new TreeNode(Integer.valueOf(serializedNodes[0]));
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        for(int i=1; i< serializedNodes.length; i++) {
            TreeNode node = queue.poll();
            if(!serializedNodes[i].equals("null")) {
                node.left = new TreeNode(Integer.valueOf(serializedNodes[i]));
                queue.add(node.left);
            }
            if(++i < serializedNodes.length && !serializedNodes[i].equals("null")) {
                node.right = new TreeNode(Integer.valueOf(serializedNodes[i]));
                queue.add(node.right);
            }

            if(!queue.isEmpty()) {
                node = queue.poll();
                if(++i < serializedNodes.length && !serializedNodes[i].equals("null")) {
                    node.left = new TreeNode(Integer.valueOf(serializedNodes[i]));
                    queue.add(node.left);
                }
                if(++i < serializedNodes.length && !serializedNodes[i].equals("null")) {
                    node.right = new TreeNode(Integer.valueOf(serializedNodes[i]));
                    queue.add(node.right);
                }
            }
        }
        return root;
    }

    public static boolean treesAreIdentical(TreeNode rootA, TreeNode rootB) {
        if(rootA == null && rootB == null) {
            return true;
        }
        if(rootA == null || rootB == null) {
            return false;
        }
        return rootA.val == rootB.val && treesAreIdentical(rootA.left, rootB.left) && treesAreIdentical(rootA.right, rootB.right);
    }
}
