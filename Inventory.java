package mini;

public class Inventory {

    private int inventoryId;
    private int productId;
    private int stockQuantity;
    private int reorderLevel;
    private String lastUpdated; 

    // Constructor
    public Inventory(int inventoryId, int productId, int stockQuantity,
                     int reorderLevel, String lastUpdated) {

        this.inventoryId = inventoryId;
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.reorderLevel = reorderLevel;
        this.lastUpdated = lastUpdated;
    }

    public int getInventoryId() {
        return inventoryId;
    }

    public int getProductId() {
        return productId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // ================= BUSINESS LOGIC =================

    // Increase stock
    public void incStock(int amount) {
        this.stockQuantity += amount;
    }

    // Decrease stock
    public void decStock(int amount) {
        this.stockQuantity -= amount;
        if (this.stockQuantity < 0) {
            this.stockQuantity = 0;
        }
    }

    // Check if reorder needed
    public boolean needsReorder() {
        return stockQuantity <= reorderLevel;
    }

    // Get current stock
    public int currentStock() {
        return stockQuantity;
    }
}