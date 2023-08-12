
public class PathsWithSum {
    public static int getPathsWithSumBruteForce(BinaryTreeNode root, int target) {
        return sumAllTotalsBruteForce(root, target);
    }
    private static int calculateTotalBruteForce(BinaryTreeNode node, int target, int pathSum) {
        if(node == null) {
            return 0;
        }
        pathSum += node.getKey();
        int result = pathSum == target ? 1 : 0;
        result += calculateTotalBruteForce(
                node.getLeft(),
                target,
                pathSum);
        result += calculateTotalBruteForce(
                node.getRight(),
                target,
                pathSum);
        return result;
    }
    private static int sumAllTotalsBruteForce(BinaryTreeNode node, int target) {
        if (node == null) {
            return 0;
        }
        int result = sumAllTotalsBruteForce(
                node.getLeft(),
                target);
        result += sumAllTotalsBruteForce(
                node.getRight(),
                target);
        result += calculateTotalBruteForce(
                node,
                target,
                0);
        return result;
    }
    public static void main(String[] args) {
        int[] array = new int[]{4, 8, 2, -7, 3, 9, 10, 7, 7, 25, 21, -20, 14, 32, 1};
        BinaryTreeNode root = MinimalTree.createMinimalTree(array);
        int total = getPathsWithSumBruteForce(root, 12);
        System.out.println("Final Total: " + total);
        printPreOrder(root);
    }
    public static void printPreOrder(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        System.out.println("Node: " + node.getKey());
        printPreOrder(node.getLeft());
        printPreOrder(node.getRight());
    }
}
