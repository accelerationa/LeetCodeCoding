package com.company;

import java.util.LinkedList;
import java.util.Queue;
import static com.company.RandomUtils.generateRandomBoolean;
import static com.company.RandomUtils.generateRandomInteger;

class TreeDataGenerator implements DataGenerator<TreeNode> {

    private static final double TRUE_PROBABILITY = 0.7;
    private static final int INTEGER_RANGE_START = 1;
    private static final int INTEGER_RANGE_END = 3;
    private static final int TREENODES_RANGE_START = 5;
    private static final int TREENODES_RANGE_END = 50;

    @Override
    public TreeNode generateData() {

        int upToNumberOfNodes = generateRandomInteger(TREENODES_RANGE_START, TREENODES_RANGE_END);

        if(generateRandomBoolean(0.1)) {
            // Empty tree
            return null;
        }

        TreeNode root = new TreeNode(1);
        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);


        while(upToNumberOfNodes -- > 0 && !queue.isEmpty()) {

            TreeNode node = queue.poll();
            if(generateRandomBoolean(TRUE_PROBABILITY)) {
                node.left = new TreeNode(generateRandomInteger(INTEGER_RANGE_START, INTEGER_RANGE_END));
                queue.add(node.left);
            }
            if(generateRandomBoolean(TRUE_PROBABILITY)) {
                node.right = new TreeNode(generateRandomInteger(2, 1000));
                queue.add(node.right);
            }
        }
        return root;
    }
}