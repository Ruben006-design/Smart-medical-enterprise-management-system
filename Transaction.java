package mini;
public class Transaction {
    protected int productId;
    protected int quantity;
    protected String date;
    protected double totalAmount;
    public Transaction(int productId, int quantity, String date) {
        this.productId = productId;
        this.quantity = quantity;
        this.date = date;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDate() {
        return date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setDate(String date) {
        this.date = date;
    }
}