package common;

import java.util.LinkedList;

public class BasicNode<T> {
    private T key;
    private LinkedList<BasicNode<T>> neighbors = new LinkedList<>();
    public BasicNode(T key) {
        this.key = key;
    }
    public void addNeighbor(BasicNode<T> node) {
        neighbors.add(node);
    }
    public LinkedList<BasicNode<T>> getNeighbors() {
        return neighbors;
    }
    public T getKey() {
        return key;
    }
}
