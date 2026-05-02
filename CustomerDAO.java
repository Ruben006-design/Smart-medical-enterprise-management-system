package mini;
import java.sql.*;
public class CustomerDAO {
    Connection con;
    public CustomerDAO() {
        con = DBConnection.getConnection();
    }
    public void addCustomer(Customer c) {
        try {
            String sql = "INSERT INTO customer VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, c.getCustomerId());
            ps.setString(2, c.getCustomerName());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getAddress());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Customer searchCustomer(int id) {
        try {
            String sql = "SELECT * FROM customer WHERE customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("address")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateCustomer(Customer c) {
        try {
            String sql = "UPDATE customer SET customer_name=?, phone=?, email=?, address=? WHERE customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, c.getCustomerName());
            ps.setString(2, c.getPhone());
            ps.setString(3, c.getEmail());
            ps.setString(4, c.getAddress());
            ps.setInt(5, c.getCustomerId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void deleteCustomer(int id) {
        try {
            String sql = "DELETE FROM customer WHERE customer_id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getAllCustomers() {
        try {
            String sql = "SELECT * FROM customer";
            PreparedStatement ps = con.prepareStatement(sql);
            return ps.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}