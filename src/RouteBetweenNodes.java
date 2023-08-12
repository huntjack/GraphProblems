import java.util.*;

public class RouteBetweenNodes<T> {
    private Map<T, BasicNode<T>> nodeMap = new HashMap<>();
    private BasicNode<T> getNode(T value) {
        return nodeMap.get(value);
    }
    public void addNode(T value) {
        BasicNode<T> node = new BasicNode<>(value);
        nodeMap.put(value, node);
    }
    public void addEdge(T source, T destination) {
        BasicNode<T> sourceNode = getNode(source);
        BasicNode<T> destinationNode = getNode(destination);
        sourceNode.addNeighbor(destinationNode);
    }
    public Optional<List<BasicNode<T>>> findPath(T source, T destination) {
        return findPath(getNode(source), getNode(destination));
    }
    private Optional<List<BasicNode<T>>> findPath(BasicNode<T> source, BasicNode<T> destination) {
        LinkedList<BasicNode<T>> nodesToVisit = new LinkedList<>();
        Set<BasicNode<T>> visited = new HashSet<>();
        Map<BasicNode<T>, BasicNode<T>> parentMap = new HashMap<>();
        parentMap.put(source, null);
        nodesToVisit.addLast(source);
        while(!nodesToVisit.isEmpty()) {
            BasicNode<T> node = nodesToVisit.removeFirst();
            if(node.equals(destination)) {
                return Optional.of(getShortestPath(destination, parentMap));
            }
            for(BasicNode<T> child : node.getNeighbors()) {
                if(!visited.contains(child)) {
                    visited.add(child);
                    parentMap.put(child, node);
                    nodesToVisit.addLast(child);
                }
            }
        }
        return Optional.empty();
    }
    private List<BasicNode<T>> getShortestPath(BasicNode<T> destination,  Map<BasicNode<T>, BasicNode<T>> parentMap) {
        LinkedList<BasicNode<T>> shortestPath = new LinkedList<>();
        BasicNode<T> current = destination;
        while(current != null) {
            shortestPath.addFirst(current);
            current = parentMap.get(current);
        }
        return shortestPath;
    }
    public static void main(String[] args) {
        RouteBetweenNodes<Character> routeBetweenNodes = new RouteBetweenNodes<>();
        routeBetweenNodes.addNode(Character.valueOf('s'));
        routeBetweenNodes.addNode(Character.valueOf('a'));
        routeBetweenNodes.addNode(Character.valueOf('z'));
        routeBetweenNodes.addNode(Character.valueOf('x'));
        routeBetweenNodes.addNode(Character.valueOf('d'));
        routeBetweenNodes.addNode(Character.valueOf('c'));
        routeBetweenNodes.addNode(Character.valueOf('f'));
        routeBetweenNodes.addNode(Character.valueOf('v'));
        routeBetweenNodes.addEdge('s', 'a');
        routeBetweenNodes.addEdge('a', 'z');
        routeBetweenNodes.addEdge('s', 'x');
        routeBetweenNodes.addEdge('x', 'c');
        routeBetweenNodes.addEdge('c', 'd');
        routeBetweenNodes.addEdge('d', 'x');
        routeBetweenNodes.addEdge('d', 'f');
        routeBetweenNodes.addEdge('c', 'f');
        routeBetweenNodes.addEdge('f', 'v');
        routeBetweenNodes.addEdge('v', 'c');
        Optional<List<BasicNode<Character>>> optionalShortestPath =
                routeBetweenNodes.findPath('s', 'v');
        List<BasicNode<Character>> shortestPath = optionalShortestPath.orElseThrow();
        String element = new String("Element: ");
        for(BasicNode<Character> node : shortestPath) {
            StringBuilder stringBuilder = new StringBuilder(element);
            stringBuilder.append(node.getKey());
            System.out.println(stringBuilder);
        }
    }
}
