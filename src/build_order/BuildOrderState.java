package build_order;

import java.util.LinkedList;
import java.util.Map;

public class BuildOrderState {
    private Map<String, DfsStatus> status;

    private LinkedList<String> result;
    public BuildOrderState(Map<String, DfsStatus> status, LinkedList<String> result) {
        this.status = status;
        this.result = result;
    }
    public boolean containsKey(String key) {
        return status.containsKey(key);
    }
    public boolean isProcessing(String key) {
        DfsStatus keyStatus = status.get(key);
        return keyStatus == DfsStatus.PROCESSING;
    }
    public void setStatus(String key, DfsStatus keyStatus) {
        status.put(key, keyStatus);
    }
    public void addResult(String key) {
        result.addFirst(key);
    }
    public LinkedList<String> getResult() {
        return result;
    }
}
