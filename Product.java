package mini;

public class Product {
    private int productId;
    private String productName;
    private int companyId;
    private double mrp;
    private double purchasePrice;
    private double sellingPrice;
    public Product(int productId, String productName, int companyId,
                   double mrp, double purchasePrice, double sellingPrice) {
        this.productId = productId;
        this.productName = productName;
        this.companyId = companyId;
        this.mrp = mrp;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
    }
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getCompanyId() { return companyId; }
    public double getMrp() { return mrp; }
    public double getPurchasePrice() { return purchasePrice; }
    public double getSellingPrice() { return sellingPrice; }
}