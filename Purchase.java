package mini;

public class Purchase extends Transaction {

    private int purchaseId;
    private int companyId;
    private double purchasePrice;

    public Purchase(int purchaseId, int companyId, String purchaseDate,
                    int productId, int quantity, double purchasePrice) {

        super(productId, quantity, purchaseDate);

        this.purchaseId = purchaseId;
        this.companyId = companyId;
        this.purchasePrice = purchasePrice;
        this.totalAmount = calculateTotalAmount();
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public String getPurchaseDate() {
        return date;   
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.date = purchaseDate;   // parent variable
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
        this.totalAmount = calculateTotalAmount();
    }

    @Override
    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalAmount = calculateTotalAmount();
    }

    public double calculateTotalAmount() {
        return quantity * purchasePrice;
    }
}