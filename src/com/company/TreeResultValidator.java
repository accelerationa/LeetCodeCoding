package com.company;

import static com.company.TreeUtils.treesAreIdentical;

public class TreeResultValidator implements Validator<TreeNode>{
    @Override
    public void checkResult(TreeNode rootA, TreeNode rootB) {
        assert treesAreIdentical(rootA, rootB);
    }
}
