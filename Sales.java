package mini;

public class Sales extends Transaction {

    private int saleId;
    private int customerId;
    private double sellingPrice;

    // Constructor
    public Sales(int saleId, int customerId, String saleDate,
                 int productId, int quantity, double sellingPrice) {

        // Parent class constructor call
        super(productId, quantity, saleDate);

        this.saleId = saleId;
        this.customerId = customerId;
        this.sellingPrice = sellingPrice;

        // Auto calculate total amount
        this.totalAmount = calculateTotalAmount();
    }

    // ================= GETTERS =================

    public int getSaleId() {
        return saleId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getSaleDate() {
        return date;   // from Transaction class
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setSaleDate(String saleDate) {
        this.date = saleDate;   // parent variable
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
        this.totalAmount = calculateTotalAmount();
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalAmount = calculateTotalAmount();
    }

    public double calculateTotalAmount() {
        return quantity * sellingPrice;
    }
}