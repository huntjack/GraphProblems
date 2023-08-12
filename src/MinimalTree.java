public class MinimalTree {
    public static BinaryTreeNode createMinimalTree(int[] intArray) {
        BinaryTreeNode rootNode = new BinaryTreeNode();
        return createMinimalTree(intArray, rootNode, 0, intArray.length - 1);
    }
    private static BinaryTreeNode createMinimalTree(int[] intArray, BinaryTreeNode node, int left, int right) {
        if (left <= right) {
            int medianIndex = getMedian(left, right);
            int medianValue = intArray[medianIndex];
            node.setKey(medianValue);
            if (left <= medianIndex - 1) {
                BinaryTreeNode newNode = new BinaryTreeNode();
                node.setLeft(newNode);
                createMinimalTree(intArray, newNode, left, medianIndex - 1);
            }
            if (medianIndex + 1 <= right) {
                BinaryTreeNode newNode = new BinaryTreeNode();
                node.setRight(newNode);
                createMinimalTree(intArray, newNode, medianIndex + 1, right);
            }
        }
        return node;
    }
    private static int getMedian(int left, int right) {
        return (left + right) / 2;
    }
    public static void printInOrder(BinaryTreeNode node) {
        if(node == null) {
            return;
        }
        printInOrder(node.getLeft());
        System.out.println("Node: " + node.getKey());
        printInOrder(node.getRight());
    }
    public static void main(String[] args) {
        int[] array = new int[]{0, 3, 6, 9, 11, 14, 17, 21, 24, 27, 32, 35, 39};
        BinaryTreeNode node = createMinimalTree(array);
        printInOrder(node);
    }
}
