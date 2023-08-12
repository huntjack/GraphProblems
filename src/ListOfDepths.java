import java.util.*;

public class ListOfDepths {
    private Map<Integer, BinaryTreeNode> nodeMap = new HashMap<>();
    public List<LinkedList<BinaryTreeNode>> createListOfDepthsWithBFS(BinaryTreeNode sourceNode) {
        List<LinkedList<BinaryTreeNode>> levelList = new ArrayList<>();
        LinkedList<BinaryTreeNode> levelOne = new LinkedList<>();
        levelOne.add(sourceNode);
        levelList.add(levelOne);
        List<BinaryTreeNode> frontier = new LinkedList<>();
        frontier.add(sourceNode);
        int i = 1;
        while(!frontier.isEmpty()) {
            List<BinaryTreeNode> next = new LinkedList<>();
            for(BinaryTreeNode frontierNode : frontier) {
                if(levelList.size() <= i) {
                    LinkedList<BinaryTreeNode> nextLevel = new LinkedList<>();
                    levelList.add(nextLevel);
                }
                LinkedList<BinaryTreeNode> current = levelList.get(i);
                if(frontierNode.getLeft() != null) {
                    BinaryTreeNode left = frontierNode.getLeft();
                    current.add(left);
                    next.add(left);
                }
                if(frontierNode.getRight() != null) {
                    BinaryTreeNode right = frontierNode.getRight();
                    current.add(right);
                    next.add(right);
                }

            }
            frontier = next;
            i++;
        }
        return levelList;
    }
    private BinaryTreeNode getNode(int key) {
        return nodeMap.get(key);
    }
    public void addNode(int key) {
        BinaryTreeNode node = new BinaryTreeNode(key);
        nodeMap.put(key, node);
    }
    public void addLeft(int parent, int child) {
        BinaryTreeNode parentNode = getNode(parent);
        BinaryTreeNode childNode = getNode(child);
        parentNode.setLeft(childNode);
    }
    public void addRight(int parent, int child) {
        BinaryTreeNode parentNode = getNode(parent);
        BinaryTreeNode childNode = getNode(child);
        parentNode.setRight(childNode);
    }
    public static void main(String[] args) {
        ListOfDepths listOfDepthsInstance = new ListOfDepths();
        listOfDepthsInstance.addNode(17);
        listOfDepthsInstance.addNode(6);
        listOfDepthsInstance.addNode(27);
        listOfDepthsInstance.addNode(0);
        listOfDepthsInstance.addNode(11);
        listOfDepthsInstance.addNode(21);
        listOfDepthsInstance.addNode(35);
        listOfDepthsInstance.addNode(3);
        listOfDepthsInstance.addNode(9);
        listOfDepthsInstance.addNode(14);
        listOfDepthsInstance.addNode(24);
        listOfDepthsInstance.addNode(32);
        listOfDepthsInstance.addNode(39);

        listOfDepthsInstance.addLeft(17, 6);
        listOfDepthsInstance.addRight(17, 27);
        listOfDepthsInstance.addLeft(6, 0);
        listOfDepthsInstance.addRight(6, 11);
        listOfDepthsInstance.addLeft(27, 21);
        listOfDepthsInstance.addRight(27, 35);
        listOfDepthsInstance.addRight(0, 3);
        listOfDepthsInstance.addLeft(11, 9);
        listOfDepthsInstance.addRight(11, 14);
        listOfDepthsInstance.addRight(21, 24);
        listOfDepthsInstance.addLeft(35, 32);
        listOfDepthsInstance.addRight(35, 39);

        BinaryTreeNode sourceNode = listOfDepthsInstance.getNode(17);
        List<LinkedList<BinaryTreeNode>> listOfDepths = listOfDepthsInstance.createListOfDepthsWithBFS(sourceNode);
        int i = 0;
        for(LinkedList<BinaryTreeNode> level : listOfDepths) {
            System.out.println("Level: " + i);
            for(BinaryTreeNode node : level) {
                System.out.println("Node: " + node.getKey());
            }
            i++;
        }
    }
}
