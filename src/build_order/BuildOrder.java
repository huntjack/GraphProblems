package build_order;

import common.BasicNode;

import java.util.*;

public class BuildOrder {
    public static List<String> createBuildOrder(String[] projects, String[][] dependencies) {
        if(projects == null || dependencies == null) {
            throw new IllegalArgumentException();
        }
        Map<String, BasicNode<String>> nodeMap = new HashMap<>();
        nodeMap = addProjects(projects, nodeMap);
        nodeMap = addDependencies(dependencies, nodeMap);
        return topologicalSort(nodeMap);
    }
    private static Map<String, BasicNode<String>> addProjects(String[] projects, Map<String, BasicNode<String>> nodeMap) {
        for(String project : projects) {
            BasicNode<String> node = new BasicNode<>(project);
            nodeMap.put(project, node);
        }
        return nodeMap;
    }
    private static Map<String, BasicNode<String>> addDependencies(String[][] dependencies, Map<String, BasicNode<String>> nodeMap) {
        for(String[] dependency : dependencies) {
            String source = dependency[0];
            String destination = dependency[1];
            BasicNode<String> sourceNode = nodeMap.get(source);
            BasicNode<String> destinationNode = nodeMap.get(destination);
            sourceNode.addNeighbor(destinationNode);
        }
        return nodeMap;
    }
    private static List<String> topologicalSort(Map<String, BasicNode<String>> nodeMap) {
        Map<String, DfsStatus> status = new HashMap<>();
        LinkedList<String> result = new LinkedList<>();
        BuildOrderState state = new BuildOrderState(status, result);
        for(Map.Entry<String, BasicNode<String>> entry : nodeMap.entrySet()) {
            String entryKey = entry.getKey();
            if(!state.containsKey(entryKey)) {
                state.setStatus(entryKey, DfsStatus.PROCESSING);
                BasicNode<String> entryNode = entry.getValue();
                state = topologicalSortVisit(entryNode, state);
            }
        }
        return state.getResult();
    }
    private static BuildOrderState topologicalSortVisit(BasicNode<String> node, BuildOrderState state) {
        for(BasicNode<String> neighbor : node.getNeighbors()) {
            String neighborKey = neighbor.getKey();
            if(state.isProcessing(neighborKey)) {
                throw new IllegalStateException();
            } else if(!state.containsKey(neighborKey)) {
                state.setStatus(neighborKey, DfsStatus.PROCESSING);
                state = topologicalSortVisit(neighbor, state);
            }
        }
        String nodeKey = node.getKey();
        state.setStatus(nodeKey, DfsStatus.VISITED);
        state.addResult(nodeKey);
        return state;
    }
    public static void main(String[] args) {
        String[] projects = {"a", "b", "c", "d", "e", "f"};
        String[][] acyclicDependencies = {{"a", "d"},{"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}};
        List<String> resultOne = createBuildOrder(projects, acyclicDependencies);
        printResult(resultOne);
        String[][] cyclicDependencies = {{"a", "d"},{"f", "b"}, {"b", "d"}, {"f", "a"}, {"d", "c"}, {"c", "b"}};
        List<String> resultlTwo = createBuildOrder(projects, cyclicDependencies);
        printResult(resultlTwo);
    }
    private static void printResult(List<String> result) {
        StringBuilder output = new StringBuilder("Output: ");
        for(String project : result) {
            output.append(project);
        }
        System.out.println(output);
    }
}
