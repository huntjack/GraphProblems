public class BinaryTreeNodeWithSize {
    private int key;
    private int size = 0;
    private BinaryTreeNodeWithSize left;
    private BinaryTreeNodeWithSize right;
    public void setKey(int key) {
        this.key = key;
    }
    public int getKey() {
        return key;
    }
    public BinaryTreeNodeWithSize getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNodeWithSize left) {
        this.left = left;
    }

    public BinaryTreeNodeWithSize getRight() {
        return right;
    }

    public void setRight(BinaryTreeNodeWithSize right) {
        this.right = right;
    }
    public int getSize() {return size;}
    public void incrementSize() {
        size++;
    }
    public void decrementSize() {
        size--;
    }
    public boolean hasLeft() {
        return left != null;
    }
    public boolean hasRight() {
        return right != null;
    }
    public BinaryTreeNodeWithSize() {}
    public BinaryTreeNodeWithSize(int key) {
        this.setKey(key);
    }
}
