import java.util.Optional;

public class Successor {
    public static Optional<BinaryTreeNodeWithParent> findSuccessor(BinaryTreeNodeWithParent node) {
        if(node == null) {
            return Optional.empty();
        }
        if(node.hasRightChild()) {
            return min(node.getRight());
        } else {
            return findSuccessorParent(node);
        }
    }
    private static Optional<BinaryTreeNodeWithParent> findSuccessorParent(BinaryTreeNodeWithParent node) {
        if(node == null || !node.hasParent()) {
            return Optional.empty();
        }
        if(node.isLeftChild()) {
            return Optional.of(
                    node.getParent());
        }
        BinaryTreeNodeWithParent current = node;
        while(current.isRightChild()) {
            current = current.getParent();
        }
        if(current.isLeftChild()) {
            return Optional.of(
                    current.getParent());
        } else {
            return Optional.empty();
        }
    }
    private static Optional<BinaryTreeNodeWithParent> min(BinaryTreeNodeWithParent node) {
        if(node == null) {
            return Optional.empty();
        }
        if(node.hasLeftChild()) {
            return min(node.getLeft());
        } else {
            return Optional.of(node);
        }
    }
    public static void main(String[] args) {
        BinaryTreeNodeWithParent root = new BinaryTreeNodeWithParent(17);
        BinaryTreeNodeWithParent six = new BinaryTreeNodeWithParent(6);
        root.setLeft(six);
        BinaryTreeNodeWithParent twentySeven = new BinaryTreeNodeWithParent(27);
        root.setRight(twentySeven);
        BinaryTreeNodeWithParent zero = new BinaryTreeNodeWithParent(0);
        six.setLeft(zero);
        BinaryTreeNodeWithParent eleven = new BinaryTreeNodeWithParent(11);
        six.setRight(eleven);
        BinaryTreeNodeWithParent twentyOne = new BinaryTreeNodeWithParent(21);
        twentySeven.setLeft(twentyOne);
        BinaryTreeNodeWithParent thirtyFive = new BinaryTreeNodeWithParent(35);
        twentySeven.setRight(thirtyFive);

        BinaryTreeNodeWithParent successorOfZero = findSuccessor(zero).orElseThrow();
        System.out.println("This should be 6: " +
                successorOfZero.getKey());
        BinaryTreeNodeWithParent successorOfEleven = findSuccessor(eleven).orElseThrow();
        System.out.println("This should be 17: " +
                successorOfEleven.getKey());
        BinaryTreeNodeWithParent successorOfRoot = findSuccessor(root).orElseThrow();
        System.out.println("This should be 21: " +
                successorOfRoot.getKey());
    }
}
