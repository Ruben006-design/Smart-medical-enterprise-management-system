package mini;

import java.sql.*;

import javax.swing.JOptionPane;

public class SalesDAO {

    Connection con;

    public SalesDAO() {
        con = DBConnection.getConnection();
    }
    public void placeOrder(Sales s) {
    try {
        int stock = new InventoryDAO().getCurrentStock(s.getProductId());
        if (stock < s.getQuantity()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Not enough stock!",
                    "Stock Alert",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }
        String sql = "INSERT INTO sales VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, s.getSaleId());
        ps.setInt(2, s.getCustomerId());
        ps.setString(3, s.getSaleDate());
        ps.setInt(4, s.getProductId());
        ps.setInt(5, s.getQuantity());
        ps.setDouble(6, s.getSellingPrice());
        ps.setDouble(7, s.getTotalAmount());
        ps.executeUpdate();
        new InventoryDAO().decreaseStock(
                s.getProductId(),
                s.getQuantity()
        );
        JOptionPane.showMessageDialog(
                null,
                "Sale placed successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                null,
                "Error while placing order!",
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
        e.printStackTrace();
    }}
    public Sales searchSale(int id) {
        try {
            String sql = "SELECT * FROM sales WHERE sale_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Sales(
                        rs.getInt("sale_id"),
                        rs.getInt("customer_id"),
                        rs.getString("sale_date"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("selling_price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ================= CANCEL SALE =================
    public void cancelSale(int id) {
        try {
            String sql = "DELETE FROM sales WHERE sale_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= VIEW ALL SALES =================
    public ResultSet getAllSales() {
        try {
            String sql = "SELECT * FROM sales";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getOrderHistory(int customerId) {
    try {
        String sql =
                "SELECT s.sale_id, p.product_name, " +
                "s.sale_date, s.quantity, " +
                "s.selling_price, s.total_amount " +
                "FROM sales s " +
                "JOIN product p ON s.product_id = p.product_id " +
                "WHERE s.customer_id = ? " +
                "ORDER BY s.sale_date DESC";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, customerId);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
public ResultSet getPreferredProducts(int customerId) {
    try {
        String sql =
                "SELECT p.product_id, p.product_name, " +
                "SUM(s.quantity) AS total_bought " +
                "FROM sales s " +
                "JOIN product p ON s.product_id = p.product_id " +
                "WHERE s.customer_id = ? " +
                "GROUP BY p.product_id, p.product_name " +
                "ORDER BY total_bought DESC";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, customerId);
        return ps.executeQuery();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

}