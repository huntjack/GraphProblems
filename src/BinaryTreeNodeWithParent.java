public class BinaryTreeNodeWithParent {
    private int key;
    private BinaryTreeNodeWithParent left;
    private BinaryTreeNodeWithParent right;
    private BinaryTreeNodeWithParent parent;
    public void setKey(int key) {
        this.key = key;
    }
    public int getKey() {
        return key;
    }
    public BinaryTreeNodeWithParent getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNodeWithParent left) {
        this.left = left;
        this
                .left
                .parent = this;
    }

    public BinaryTreeNodeWithParent getRight() {
        return right;
    }

    public void setRight(BinaryTreeNodeWithParent right) {
        this.right = right;
        this
                .right
                .parent = this;
    }
    public BinaryTreeNodeWithParent getParent() {return parent;}
    public boolean hasParent() {
        return parent != null;
    }
    public boolean isParent() {
        return right != null || left != null;
    }
    public boolean hasLeftChild() {
        return left != null;
    }
    public boolean hasRightChild() {
        return right != null;
    }
    public boolean isLeftChild() {
        if(parent != null) {
            return parent.getLeft() == this;
        } else {
            return false;
        }
    }
    public boolean isRightChild() {
        if(parent != null) {
            return parent.getRight() == this;
        } else {
            return false;
        }
    }
    public BinaryTreeNodeWithParent() {}
    public BinaryTreeNodeWithParent(int key) {
        this.setKey(key);
    }
}
