package mini;

import java.sql.*;

public class PurchaseDAO {
    Connection con;
    public PurchaseDAO() {
        con = DBConnection.getConnection();
    }
    public void createPurchase(Purchase p) {
        try {
            String sql = "INSERT INTO purchase VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, p.getPurchaseId());
            ps.setInt(2, p.getCompanyId());
            ps.setString(3, p.getPurchaseDate()); // YYYY-MM-DD
            ps.setInt(4, p.getProductId());
            ps.setInt(5, p.getQuantity());
            ps.setDouble(6, p.getPurchasePrice());
            ps.setDouble(7, p.getTotalAmount());

            ps.executeUpdate();
            new InventoryDAO().increaseStock(p.getProductId(),p.getQuantity());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Purchase searchPurchase(int id) {
        try {
            String sql = "SELECT * FROM purchase WHERE purchase_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Purchase(
                        rs.getInt("purchase_id"),
                        rs.getInt("company_id"),
                        rs.getString("purchase_date"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("purchase_price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public ResultSet getAllPurchases() {
        try {
            String sql = "SELECT * FROM purchase";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getPurchaseHistoryByCompany(int companyId) {
    try {
        String sql =
            "SELECT p.purchase_id, pr.product_name, " +
            "p.purchase_date, p.quantity, " +
            "p.purchase_price, p.total_amount " +
            "FROM purchase p " +
            "JOIN product pr ON p.product_id = pr.product_id " +
            "WHERE p.company_id = ? " +
            "ORDER BY p.purchase_date DESC";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, companyId);

        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }}
}