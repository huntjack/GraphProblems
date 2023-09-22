import common.BinaryTreeNode;

import java.util.LinkedList;
import java.util.List;

public class CheckSubtree {
    public static boolean checkSubtree(BinaryTreeNode bigTreeRoot, BinaryTreeNode smallTreeRoot) {
        if(bigTreeRoot == null || smallTreeRoot == null) {
            return false;
        }
        List<BinaryTreeNode> subtreeCandidates = findCandidateRootsInBigTree(bigTreeRoot, smallTreeRoot);
        for(BinaryTreeNode subtreeCandidate : subtreeCandidates) {
            boolean result = validateSubtree(subtreeCandidate, smallTreeRoot);
            if(result) {
                return true;
            }
        }
        return false;
    }
    private static List<BinaryTreeNode> findCandidateRootsInBigTree(BinaryTreeNode bigTreeNode, BinaryTreeNode smallTreeNode) {
        List<BinaryTreeNode> result = new LinkedList<>();
        LinkedList<BinaryTreeNode> nextQueue = new LinkedList<>();
        nextQueue.addLast(bigTreeNode);
        while(!nextQueue.isEmpty()) {
            BinaryTreeNode current = nextQueue.removeFirst();
            if(areSame(current, smallTreeNode)) {
                result.add(current);
            }
            if(current.getLeft() != null) {
                BinaryTreeNode left = current.getLeft();
                nextQueue.addLast(left);
            }
            if(current.getRight() != null) {
                BinaryTreeNode right = current.getRight();
                nextQueue.addLast(right);
            }
        }
        return result;
    }
    private static boolean areSame(BinaryTreeNode bigTreeNode, BinaryTreeNode smallTreeNode) {
        if(bigTreeNode == null && smallTreeNode == null) {
            return true;
        } else if(bigTreeNode == null && smallTreeNode != null ||
                bigTreeNode != null && smallTreeNode == null) {
            return false;
        } else {
            return bigTreeNode.getKey() == smallTreeNode.getKey();
        }
    }
    private static boolean validateSubtree(BinaryTreeNode candidateRoot, BinaryTreeNode smallTreeRoot) {
        if(candidateRoot == null && smallTreeRoot == null) {
            return true;
        }
        if (!areSame(candidateRoot, smallTreeRoot)) {
            return false;
        }
        boolean left = validateSubtree(candidateRoot.getLeft(), smallTreeRoot.getLeft());
        boolean right = validateSubtree(candidateRoot.getRight(), smallTreeRoot.getRight());
        return left && right;
    }
    public static void main(String[] args) {
        int[] bigTreeArray = new int[] {0, 3, 6, 9, 11, 14, 17, 21, 24, 27, 32, 35, 39};
        BinaryTreeNode bigTreeRoot = MinimalTree.createMinimalTree(bigTreeArray);
        int[] smallTreeArray = new int[] {21, 24, 27, 32, 35, 39};
        BinaryTreeNode smallTreeRoot = MinimalTree.createMinimalTree(smallTreeArray);
        boolean resultOne = checkSubtree(bigTreeRoot, smallTreeRoot);
        System.out.println("This should be true: " + resultOne);

        int[] differentTreeArray = new int[] {21, 24, 27, 27, 27, 35};
        BinaryTreeNode differentTreeRoot = MinimalTree.createMinimalTree(differentTreeArray);
        boolean resultTwo = checkSubtree(bigTreeRoot, differentTreeRoot);
        System.out.println("This should be false: " + resultTwo);
    }
}
