package Model;

public class WindowStatistics {
    private long transactionCount;
    private double sumAmountSell;
    private double avgAmountSell;
    private double maxAmountSell;
    private double sumAmountBuy;
    private double avgAmountBuy;
    private double maxAmountBuy;

    public long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public double getSumAmountSell() {
        return sumAmountSell;
    }

    public void setSumAmountSell(double sumAmountSell) {
        this.sumAmountSell = sumAmountSell;
    }

    public double getAvgAmountSell() {
        return avgAmountSell;
    }

    public void setAvgAmountSell(double avgAmountSell) {
        this.avgAmountSell = avgAmountSell;
    }

    public double getMaxAmountSell() {
        return maxAmountSell;
    }

    public void setMaxAmountSell(double maxAmountSell) {
        this.maxAmountSell = maxAmountSell;
    }

    public double getSumAmountBuy() {
        return sumAmountBuy;
    }

    public void setSumAmountBuy(double sumAmountBuy) {
        this.sumAmountBuy = sumAmountBuy;
    }

    public double getAvgAmountBuy() {
        return avgAmountBuy;
    }

    public void setAvgAmountBuy(double avgAmountBuy) {
        this.avgAmountBuy = avgAmountBuy;
    }

    public double getMaxAmountBuy() {
        return maxAmountBuy;
    }

    public void setMaxAmountBuy(double maxAmountBuy) {
        this.maxAmountBuy = maxAmountBuy;
    }
}
