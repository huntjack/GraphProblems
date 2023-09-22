package common;

public class BinaryTreeNode {
    private int key;
    private BinaryTreeNode left;
    private BinaryTreeNode right;
    public void setKey(int key) {
        this.key = key;
    }
    public int getKey() {
        return key;
    }
    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
    public BinaryTreeNode() {}
    public BinaryTreeNode(int key) {
        this.setKey(key);
    }
}
