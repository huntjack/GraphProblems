public class BalancePartialResult {
    private int height;
    private boolean isBalanced;
    public BalancePartialResult(int height, boolean isBalanced) {
        this.height = height;
        this.isBalanced = isBalanced;
    }
    public int getHeight() {
        return height;
    }

    public boolean isBalanced() {
        return isBalanced;
    }

}
