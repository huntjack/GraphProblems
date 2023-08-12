import java.util.Optional;
import java.util.Random;

public class RandomNodeBst {
    private BinaryTreeNodeWithSize root;

    public void insert(int key) {
        if(isEmpty()) {
            BinaryTreeNodeWithSize node = new BinaryTreeNodeWithSize(key);
            root = node;
            root.incrementSize();
        } else {
            insertNode(root, key);
        }
    }
    private boolean isEmpty() {
        return root == null;
    }
    private static void insertNode(BinaryTreeNodeWithSize current, int keyToBeInserted) {
        if(keyToBeInserted < current.getKey()) {
            if(!current.hasLeft()) {
                BinaryTreeNodeWithSize left = new BinaryTreeNodeWithSize(keyToBeInserted);
                left.incrementSize();
                current.setLeft(left);
            } else {
                BinaryTreeNodeWithSize left = current.getLeft();
                insertNode(left, keyToBeInserted);
            }
        } else {
            if(!current.hasRight()) {
                BinaryTreeNodeWithSize right = new BinaryTreeNodeWithSize(keyToBeInserted);
                right.incrementSize();
                current.setRight(right);
            } else {
                BinaryTreeNodeWithSize right = current.getRight();
                insertNode(right, keyToBeInserted);
            }
        }
        current.incrementSize();
    }
    public Optional<BinaryTreeNodeWithSize> getRandomNode() {
        if(isEmpty()) {
            return Optional.empty();
        }
        Random random = new Random();
        BinaryTreeNodeWithSize result = getRandomNode(root, random);
        return Optional.of(result);
    }
    private static BinaryTreeNodeWithSize getRandomNode(BinaryTreeNodeWithSize node, Random random) {
        int currentSize = node.getSize();
        int randomNumber = random.nextInt(currentSize);
        if(randomNumber == 0) {
            return node;
        }
        BinaryTreeNodeWithSize right = node.getRight();
        if(node.hasLeft()) {
            BinaryTreeNodeWithSize left = node.getLeft();
            if(randomNumber <= left.getSize()) {
                return getRandomNode(left, random);
            } else {
                return getRandomNode(right, random);
            }
        } else {
            return getRandomNode(right, random);
        }
    }
    public Optional<BinaryTreeNodeWithSize> find(int key) {
        return find(root, key);
    }
    private Optional<BinaryTreeNodeWithSize> find(BinaryTreeNodeWithSize current, int targetKey) {
        if(current == null) {
            return Optional.empty();
        }
        int currentKey = current.getKey();
        if(targetKey == currentKey) {
            return Optional.of(current);
        } else if(targetKey < currentKey) {
            if(!current.hasLeft()) {
                return Optional.empty();
            } else {
                BinaryTreeNodeWithSize left = current.getLeft();
                return find(left, targetKey);
            }
        } else {
            if(!current.hasRight()) {
                return Optional.empty();
            } else {
                BinaryTreeNodeWithSize right = current.getRight();
                return find(right, targetKey);
            }
        }
    }
    public void delete(int key) {
        Optional<BinaryTreeNodeWithSize> target = find(key);
        if(target.isEmpty()) {
            return;
        }
        Optional<BinaryTreeNodeWithSize> optionalRoot = delete(root, key);
        BinaryTreeNodeWithSize newRoot = null;
        if(optionalRoot.isPresent()) {
            newRoot = optionalRoot.get();
        }
        root = newRoot;
    }
    private Optional<BinaryTreeNodeWithSize> delete(BinaryTreeNodeWithSize current, int key) {
        if(current == null) {
            return Optional.empty();
        }
        int currentKey = current.getKey();
        if(key < currentKey) {
            Optional<BinaryTreeNodeWithSize> optionalLeft =
                    delete(current.getLeft(), key);
            BinaryTreeNodeWithSize left = null;
            if(optionalLeft.isPresent())  {
                left = optionalLeft.get();
            }
            current.setLeft(left);
            current.decrementSize();
        } else if(key > currentKey) {
            Optional<BinaryTreeNodeWithSize> optionalRight =
                    delete(current.getRight(), key);
            BinaryTreeNodeWithSize right = null;
            if(optionalRight.isPresent()) {
                right = optionalRight.get();
            }
            current.setRight(right);
            current.decrementSize();
        } else {
            if(!current.hasLeft() && !current.hasRight()) {
                current = null;
            } else if(!current.hasLeft()) {
                current = current.getRight();
            } else if(!current.hasRight()) {
                current = current.getLeft();
            } else {
                BinaryTreeNodeWithSize successor =
                        getSubtreeFirst(current.getRight());
                int successorKey = successor.getKey();
                current.setKey(successorKey);
                Optional<BinaryTreeNodeWithSize> optionalRight =
                        delete(current.getRight(), successorKey);
                BinaryTreeNodeWithSize right = null;
                if(optionalRight.isPresent()) {
                    right = optionalRight.get();
                }
                current.setRight(right);
                current.decrementSize();
            }
        }
        return Optional.ofNullable(current);
    }
    private static BinaryTreeNodeWithSize getSubtreeFirst(BinaryTreeNodeWithSize node) {
        if(node.hasLeft()) {
            return getSubtreeFirst(node.getLeft());
        } else {
            return node;
        }
    }
    public void printInOrder() {
        printInOrder(root);
    }
    private static void printInOrder(BinaryTreeNodeWithSize node) {
        if(node == null) {
            return;
        }
        printInOrder(node.getLeft());
        System.out.println("Node: " + node.getKey() + "  size: " + node.getSize());
        printInOrder(node.getRight());
    }
    public static void main(String[] args) {
        RandomNodeBst randomNodeBst = new RandomNodeBst();
        randomNodeBst.insert(17);
        randomNodeBst.insert(6);
        randomNodeBst.insert(27);
        randomNodeBst.insert(0);
        randomNodeBst.insert(11);
        randomNodeBst.insert(21);
        randomNodeBst.insert(35);
        randomNodeBst.insert(3);
        randomNodeBst.insert(9);
        randomNodeBst.insert(14);
        randomNodeBst.insert(24);
        randomNodeBst.insert(32);
        randomNodeBst.insert(39);

        randomNodeBst.delete(27);
        randomNodeBst.printInOrder();

        BinaryTreeNodeWithSize randomNode =
                randomNodeBst
                        .getRandomNode()
                        .orElseThrow();
        System.out.println("Random Node: " + randomNode.getKey());
        BinaryTreeNodeWithSize target =
                randomNodeBst.find(32).orElseThrow();
        System.out.println("This should be 32: " + target.getKey());
    }
}
