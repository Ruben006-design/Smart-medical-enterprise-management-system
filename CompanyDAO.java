package mini;
import java.sql.*;
public class CompanyDAO {
    Connection con;
    public CompanyDAO() {
        con =DBConnection.getConnection();
    }
    
    public void addCompany(Company c) {
        try {
            String sql = "INSERT INTO company VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, c.getId());
            ps.setString(2, c.getName());
            ps.setString(3, c.getContactPerson());
            ps.setString(4, c.getPhone());
            ps.setString(5, c.getEmail());
            ps.setString(6, c.getAddress());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Company searchCompany(int id) {
        try {
            String sql = "SELECT * FROM company WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Company(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contactPerson"),
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
    public void deleteCompany(int id) {
        try {
            String sql = "DELETE FROM company WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateCompany(Company c) {
        try {
            String sql = "UPDATE company SET name=?, contactPerson=?, phone=?, email=?, address=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, c.getName());
            ps.setString(2, c.getContactPerson());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getEmail());
            ps.setString(5, c.getAddress());
            ps.setInt(6, c.getId());

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public ResultSet getAllCompanies() {
    try {
        String sql = "SELECT * FROM company";
        PreparedStatement ps = con.prepareStatement(sql);
        return ps.executeQuery();

    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }}
}