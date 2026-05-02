package mini;
import java.sql.*;
import java.time.LocalDate;
public class ProductDAO {

    Connection con;

    public ProductDAO() {
        con = DBConnection.getConnection();
    }
    public void addProduct(Product p) {
        try {
            String sql = "INSERT INTO product VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, p.getProductId());
            ps.setString(2, p.getProductName());
            ps.setInt(3, p.getCompanyId());
            ps.setDouble(4, p.getMrp());
            ps.setDouble(5, p.getPurchasePrice());
            ps.setDouble(6, p.getSellingPrice());
            ps.executeUpdate();
            Inventory inv = new Inventory(
            p.getProductId(),  
            p.getProductId(),   
            0,                 
            10,                
            LocalDate.now().toString());
            new InventoryDAO().addInventory(inv);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Product searchProduct(int id) {
        try {
            String sql = "SELECT * FROM product WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                return new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("company_id"),
                        rs.getDouble("mrp"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("selling_price")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteProduct(int id) {
        try {
            String sql1 = "DELETE FROM inventory WHERE product_id = ?";
            PreparedStatement ps1 = con.prepareStatement(sql1);
            ps1.setInt(1, id);
            ps1.executeUpdate();
            String sql = "DELETE FROM product WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateProduct(Product p) {
        try {
            String sql = "UPDATE product SET product_name=?, company_id=?, mrp=?, purchase_price=?, selling_price=? WHERE product_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, p.getProductName());
            ps.setInt(2, p.getCompanyId());
            ps.setDouble(3, p.getMrp());
            ps.setDouble(4, p.getPurchasePrice());
            ps.setDouble(5, p.getSellingPrice());
            ps.setInt(6, p.getProductId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet getAllProducts() {
    try {
        String sql = "SELECT * FROM product";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}
}