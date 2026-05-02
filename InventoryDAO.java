package mini;
import java.sql.*;
public class InventoryDAO {
    Connection con;
    public InventoryDAO() {
        con = DBConnection.getConnection();
    }
    public void addInventory(Inventory i) {
        try {
            String sql = "INSERT INTO inventory VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, i.getInventoryId());
            ps.setInt(2, i.getProductId());
            ps.setInt(3, i.getStockQuantity());
            ps.setInt(4, i.getReorderLevel());
            ps.setString(5, i.getLastUpdated());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateInventory(Inventory i) {
        try {
            String sql = "UPDATE inventory SET product_id=?, stock_quantity=?, reorder_level=?, last_updated=? WHERE inventory_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, i.getProductId());
            ps.setInt(2, i.getStockQuantity());
            ps.setInt(3, i.getReorderLevel());
            ps.setString(4, i.getLastUpdated());
            ps.setInt(5, i.getInventoryId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Inventory searchInventory(int id) {
        try {
            String sql = "SELECT * FROM inventory WHERE inventory_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Inventory(
                        rs.getInt("inventory_id"),
                        rs.getInt("product_id"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("reorder_level"),
                        rs.getString("last_updated")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void increaseStock(int productId, int amount) {
        try {
            String sql = "UPDATE inventory SET stock_quantity = stock_quantity + ?, last_updated = CURDATE() WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, amount);
            ps.setInt(2, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void decreaseStock(int productId, int amount) {
        try {
            String sql = "UPDATE inventory SET stock_quantity = stock_quantity - ?, last_updated = CURDATE() WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, amount);
            ps.setInt(2, productId);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCurrentStock(int productId) {
        try {
            String sql = "SELECT stock_quantity FROM inventory WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("stock_quantity");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public ResultSet getLowStockProducts() {
        try {
            String sql = "SELECT * FROM inventory WHERE stock_quantity <= reorder_level";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet getAllInventory() {
        try {
            String sql = "SELECT * FROM inventory";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}