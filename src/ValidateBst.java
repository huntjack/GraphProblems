
public class ValidateBst {
    public static boolean validateBst(BinaryTreeNode node) {
        return validateBst(node, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    private static boolean validateBst(BinaryTreeNode node, int min, int max) {
        if(node == null) {
            return true;
        }
        if(node.getKey() <= min | node.getKey() > max) {
            return false;
        }
        boolean left = validateBst(node.getLeft(), min, node.getKey());
        boolean right = validateBst(node.getRight(), node.getKey(), max);
        return left && right;
    }

    public static void main(String[] args) {
        int[] array = new int[]{0, 3, 6, 9, 11, 14, 17, 21, 24, 27, 32, 35, 39};
        BinaryTreeNode bstRootNode = MinimalTree.createMinimalTree(array);
        System.out.println("Should be true: " + validateBst(bstRootNode));

        BinaryTreeNode notBstRootNode = new BinaryTreeNode(13);
        BinaryTreeNode ten = new BinaryTreeNode(10);
        notBstRootNode.setLeft(ten);
        BinaryTreeNode fourteen = new BinaryTreeNode(14);
        notBstRootNode.setRight(fourteen);
        BinaryTreeNode eight = new BinaryTreeNode(8);
        ten.setLeft(eight);
        BinaryTreeNode twelve = new BinaryTreeNode(12);
        ten.setRight(twelve);
        BinaryTreeNode fifteen = new BinaryTreeNode(15);
        fourteen.setLeft(fifteen);
        BinaryTreeNode seventeen = new BinaryTreeNode(17);
        fourteen.setRight(seventeen);

        System.out.println("Should be false: " + validateBst(notBstRootNode));
    }
}
