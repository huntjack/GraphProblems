import common.BalancePartialResult;
import common.BinaryTreeNode;

public class CheckBalanced {
    private static BalancePartialResult checkBalance(BinaryTreeNode node) {
        if(node == null) {
            return new BalancePartialResult(-1, true);
        }
        BalancePartialResult left = checkBalance(node.getLeft());
        BalancePartialResult right = checkBalance(node.getRight());
        boolean isBalanced = false;
        if(left.isBalanced() && right.isBalanced()) {
            int difference = getAbsoluteDifference(left.getHeight(), right.getHeight());
            isBalanced = difference > 1 ? false : true;
        }
        int currentHeight = Math.max(left.getHeight(), right.getHeight()) + 1;
        return new BalancePartialResult(currentHeight, isBalanced);
    }
    public static boolean isBalanced(BinaryTreeNode node) {
        return checkBalance(node)
                .isBalanced();
    }
    private static int getAbsoluteDifference(int num1, int num2) {
        return Math.abs(num1 - num2);
    }
    public static void main(String[] args) {
        int[] array = new int[]{0, 3, 6, 9, 11, 14, 17, 21, 24, 27, 32, 35, 39};
        BinaryTreeNode balancedTreeRootNode = MinimalTree.createMinimalTree(array);
        System.out.println("Is balanced tree balanced: " + isBalanced(balancedTreeRootNode));

        BinaryTreeNode unbalancedTreeRootNode = new BinaryTreeNode(3);
        BinaryTreeNode one = new BinaryTreeNode(1);
        unbalancedTreeRootNode.setLeft(one);
        BinaryTreeNode two = new BinaryTreeNode(2);
        one.setRight(two);
        BinaryTreeNode four = new BinaryTreeNode(4);
        unbalancedTreeRootNode.setRight(four);
        BinaryTreeNode six = new BinaryTreeNode(6);
        four.setRight(six);
        BinaryTreeNode five = new BinaryTreeNode(5);
        six.setLeft(five);
        System.out.println("Is unbalanced tree balanced: " + isBalanced(unbalancedTreeRootNode));
    }
}
