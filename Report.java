package mini;

public class Report {

    private int productId;
    private String productName;
    private double totalPurchaseCost;
    private double totalSalesRevenue;
    private double profitAmount;
    private double profitMargin;

    public Report(int productId, String productName,
                  double totalPurchaseCost,
                  double totalSalesRevenue,
                  double profitAmount,
                  double profitMargin) {

        this.productId = productId;
        this.productName = productName;
        this.totalPurchaseCost = totalPurchaseCost;
        this.totalSalesRevenue = totalSalesRevenue;
        this.profitAmount = profitAmount;
        this.profitMargin = profitMargin;
    }
    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public double getTotalPurchaseCost() {
        return totalPurchaseCost;
    }

    public double getTotalSalesRevenue() {
        return totalSalesRevenue;
    }

    public double getProfitAmount() {
        return profitAmount;
    }

    public double getProfitMargin() {
        return profitMargin;
    }
}