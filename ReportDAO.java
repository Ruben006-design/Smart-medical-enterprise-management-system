package mini;
import java.sql.*;
public class ReportDAO {
    Connection con;

    public ReportDAO() {
        con = DBConnection.getConnection();
    }
    public double getTotalPurchaseCost(int productId) {
        double total = 0;

        try {
            String sql = "SELECT SUM(total_amount) AS total FROM purchase WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    public double getTotalSalesRevenue(int productId) {
        double total = 0;

        try {
            String sql = "SELECT SUM(total_amount) AS total FROM sales WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, productId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                total = rs.getDouble("total");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
    public double getProfitAmount(int productId) {
        return getTotalSalesRevenue(productId)
                - getTotalPurchaseCost(productId);
    }
    public double getProfitMargin(int productId) {
        double sales = getTotalSalesRevenue(productId);
        double profit = getProfitAmount(productId);

        if (sales == 0)
            return 0;

        return (profit / sales) * 100;
    }
    public ResultSet getFullReport() {
        try {
            String sql =
                    "SELECT p.product_id, p.product_name, " +
                    "COALESCE((SELECT SUM(total_amount) FROM purchase pu WHERE pu.product_id = p.product_id), 0) AS purchase_total, " +
                    "COALESCE((SELECT SUM(total_amount) FROM sales s WHERE s.product_id = p.product_id), 0) AS sales_total " +
                    "FROM product p";

            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet bestSellingProducts() {
        try {
            String sql =
                    "SELECT p.product_id, p.product_name, " +
                    "SUM(s.quantity) AS total_sold " +
                    "FROM sales s " +
                    "JOIN product p ON s.product_id = p.product_id " +
                    "GROUP BY p.product_id, p.product_name " +
                    "ORDER BY total_sold DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getActiveSuppliers() {
        try {
            String sql =
                    "SELECT c.company_id, c.company_name, " +
                    "COUNT(p.purchase_id) AS total_purchases " +
                    "FROM company c " +
                    "JOIN purchase p ON c.company_id = p.company_id " +
                    "GROUP BY c.company_id, c.company_name " +
                    "ORDER BY total_purchases DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public ResultSet getActiveCustomers() {
        try {
            String sql =
                    "SELECT c.customer_id, c.customer_name, " +
                    "COUNT(s.sale_id) AS total_orders, " +
                    "SUM(s.total_amount) AS total_purchase_amount " +
                    "FROM customer c " +
                    "JOIN sales s ON c.customer_id = s.customer_id " +
                    "GROUP BY c.customer_id, c.customer_name " +
                    "ORDER BY total_purchase_amount DESC";

            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}