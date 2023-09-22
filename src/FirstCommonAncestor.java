import common.BinaryTreeNode;
import common.BinaryTreeNodeWithParent;

import java.util.Optional;

public class FirstCommonAncestor {
    public static Optional<BinaryTreeNode> findFirstCommonAncestor(BinaryTreeNode root, BinaryTreeNode nodeOne, BinaryTreeNode nodeTwo) {
        if(root == null || nodeOne == null || nodeTwo == null) {
            throw new IllegalArgumentException();
        }
        if(!subtreeContains(root, nodeOne) || !subtreeContains(root, nodeTwo)) {
            return Optional.empty();
        }
        BinaryTreeNode result = findFirstCommonAncestorHelper(root, nodeOne, nodeTwo);
        return Optional.of(result);
    }
    private static BinaryTreeNode findFirstCommonAncestorHelper(BinaryTreeNode root, BinaryTreeNode nodeOne, BinaryTreeNode nodeTwo) {
        if(root == null || root == nodeOne || root == nodeOne) {
            return root;
        }
        BinaryTreeNode left = root.getLeft();
        boolean isNodeOneInLeft = subtreeContains(left, nodeOne);
        boolean isNodeTwoInLeft = subtreeContains(left, nodeTwo);
        if(isNodeOneInLeft && isNodeTwoInLeft) {
            return findFirstCommonAncestorHelper(left, nodeOne, nodeTwo);
        } else if(!isNodeOneInLeft && !isNodeTwoInLeft) {
            BinaryTreeNode right = root.getRight();
            return findFirstCommonAncestorHelper(right, nodeOne, nodeTwo);
        } else {
            return root;
        }
    }
    private static boolean subtreeContains(BinaryTreeNode current, BinaryTreeNode target) {
        if(current == null || target == null) {
            return false;
        }
        if(current == target) {
            return true;
        }
        boolean left = subtreeContains(current.getLeft(), target);
        boolean right = subtreeContains(current.getRight(), target);
        return left || right;
    }
    public static BinaryTreeNodeWithParent findFirstCommonAncestorWithParent(BinaryTreeNodeWithParent nodeOne, BinaryTreeNodeWithParent nodeTwo) {
        if(nodeOne == null || nodeTwo == null) {
            throw new IllegalArgumentException();
        }
        int nodeOneLength = depth(nodeOne);
        int nodeTwoLength = depth(nodeTwo);
        int difference = Math.abs(nodeOneLength - nodeTwoLength);
        if(nodeOneLength > nodeTwoLength) {
            return findIntersection(nodeOne, nodeTwo, difference);
        } else {
            return findIntersection(nodeTwo, nodeOne, difference);
        }
    }
    private static int depth(BinaryTreeNodeWithParent node) {
        if(node == null) {
            throw new IllegalArgumentException();
        }
        BinaryTreeNodeWithParent current;
        if(node.getParent() != null) {
            current = node;
        } else {
            return 0;
        }
        int i = 0;
        while(current.getParent() != null) {
            current = current.getParent();
            i++;
        }
        return i;
    }
    private static BinaryTreeNodeWithParent findIntersection(BinaryTreeNodeWithParent nodeWithGreaterDepth,
                                                             BinaryTreeNodeWithParent nodeWithLesserDepth,
                                                             int difference) {
        for(int i = 0; i  < difference; i++) {
            nodeWithGreaterDepth = nodeWithGreaterDepth.getParent();
        }
        while(nodeWithGreaterDepth != null) {
            if(nodeWithGreaterDepth == nodeWithLesserDepth) {
                return nodeWithGreaterDepth;
            } else {
                nodeWithGreaterDepth = nodeWithGreaterDepth.getParent();
                nodeWithLesserDepth = nodeWithLesserDepth.getParent();
            }
        }
        return null;
    }
    public static void main(String[] args) {
        BinaryTreeNodeWithParent rootA = new BinaryTreeNodeWithParent(17);
        BinaryTreeNodeWithParent sixA = new BinaryTreeNodeWithParent(6);
        rootA.setLeft(sixA);
        BinaryTreeNodeWithParent twentySevenA = new BinaryTreeNodeWithParent(27);
        rootA.setRight(twentySevenA);
        BinaryTreeNodeWithParent zeroA = new BinaryTreeNodeWithParent(0);
        sixA.setLeft(zeroA);
        BinaryTreeNodeWithParent elevenA = new BinaryTreeNodeWithParent(11);
        sixA.setRight(elevenA);
        BinaryTreeNodeWithParent twentyOneA = new BinaryTreeNodeWithParent(21);
        twentySevenA.setLeft(twentyOneA);
        BinaryTreeNodeWithParent thirtyFiveA = new BinaryTreeNodeWithParent(35);
        twentySevenA.setRight(thirtyFiveA);

        BinaryTreeNodeWithParent resultA = findFirstCommonAncestorWithParent(elevenA, twentySevenA);
        System.out.println("This should be 17: " + resultA.getKey());

        BinaryTreeNode rootB = new BinaryTreeNode(17);
        BinaryTreeNode sixB = new BinaryTreeNode(6);
        rootB.setLeft(sixB);
        BinaryTreeNode twentySevenB = new BinaryTreeNode(27);
        rootB.setRight(twentySevenB);
        BinaryTreeNode zeroB = new BinaryTreeNode(0);
        sixB.setLeft(zeroB);
        BinaryTreeNode elevenB = new BinaryTreeNode(11);
        sixB.setRight(elevenB);
        BinaryTreeNode twentyOneB = new BinaryTreeNode(21);
        twentySevenB.setLeft(twentyOneB);
        BinaryTreeNode thirtyFiveB = new BinaryTreeNode(35);
        twentySevenB.setRight(thirtyFiveB);
        BinaryTreeNode resultB = findFirstCommonAncestor(rootB, twentyOneB, thirtyFiveB).orElseThrow();
        System.out.println("This should be 27: " + resultB.getKey());
    }
}
