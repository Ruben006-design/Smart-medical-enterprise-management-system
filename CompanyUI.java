
package mini;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class CompanyUI {

    public CompanyUI() {

        JFrame f = new JFrame("Manufacturing Management System");
        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        f.setIconImage(icon.getImage());
        f.setSize(800, 600);
        f.setLayout(null);
        JLabel header = new JLabel("MANUFACTURING MANAGEMENT SYSTEM", JLabel.CENTER);
        header.setBounds(0, 0, 800, 50);
        header.setOpaque(true);
        header.setBackground(new Color(0, 51, 102));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Arial", Font.BOLD, 18));
        f.add(header);
        JPanel sidePanel = new JPanel();
        sidePanel.setBounds(0, 50, 180, 550);
        sidePanel.setLayout(null);
        sidePanel.setBackground(new Color(0, 51, 102));
        f.add(sidePanel);

        JButton companyBtn = new JButton("Company");
        JButton productBtn = new JButton("Product");
        JButton purchaseBtn = new JButton("Purchase");
        JButton backBtn = new JButton("Main Menu");

        companyBtn.setBounds(20, 50, 140, 30);
        productBtn.setBounds(20, 100, 140, 30);
        purchaseBtn.setBounds(20, 150, 140, 30);
        backBtn.setBounds(20, 200, 140, 30);

        sidePanel.add(companyBtn);
        sidePanel.add(productBtn);
        sidePanel.add(purchaseBtn);
        sidePanel.add(backBtn);

        productBtn.addActionListener(e -> {
            f.dispose();
            new ProductUI();
        });

        purchaseBtn.addActionListener(e -> {
            f.dispose();
            new PurchaseUI();
        });

        backBtn.addActionListener(e -> {
            f.dispose();
            new MenuUI();
        });
        JPanel mainPanel = new JPanel();
        mainPanel.setBounds(180, 50, 620, 550);
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);
        f.add(mainPanel);
        JLabel title = new JLabel("Company Management");
        title.setBounds(200, 10, 200, 30);
        mainPanel.add(title);
        JLabel l1 = new JLabel("Company ID:");
        JLabel l2 = new JLabel("Company Name:");
        JLabel l3 = new JLabel("Contact Person:");
        JLabel l4 = new JLabel("Phone:");
        JLabel l5 = new JLabel("Email:");
        JLabel l6 = new JLabel("Address:");
        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JTextField t5 = new JTextField();
        JTextField t6 = new JTextField();

        l1.setBounds(50, 60, 120, 25); t1.setBounds(180, 60, 150, 25);
        l2.setBounds(50, 100, 120, 25); t2.setBounds(180, 100, 150, 25);
        l3.setBounds(50, 140, 120, 25); t3.setBounds(180, 140, 150, 25);
        l4.setBounds(50, 180, 120, 25); t4.setBounds(180, 180, 150, 25);
        l5.setBounds(50, 220, 120, 25); t5.setBounds(180, 220, 150, 25);
        l6.setBounds(50, 260, 120, 25); t6.setBounds(180, 260, 150, 25);

        mainPanel.add(l1); mainPanel.add(t1);
        mainPanel.add(l2); mainPanel.add(t2);
        mainPanel.add(l3); mainPanel.add(t3);
        mainPanel.add(l4); mainPanel.add(t4);
        mainPanel.add(l5); mainPanel.add(t5);
        mainPanel.add(l6); mainPanel.add(t6);
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");
        JButton viewBtn = new JButton("View All");

        addBtn.setBounds(20, 310, 80, 30);
        updateBtn.setBounds(110, 310, 90, 30);
        deleteBtn.setBounds(210, 310, 90, 30);
        searchBtn.setBounds(310, 310, 90, 30);
        clearBtn.setBounds(150, 350, 100, 30);
        viewBtn.setBounds(270, 350, 100, 30);

        mainPanel.add(addBtn);
        mainPanel.add(updateBtn);
        mainPanel.add(deleteBtn);
        mainPanel.add(searchBtn);
        mainPanel.add(clearBtn);
        mainPanel.add(viewBtn);
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);

        model.addColumn("Company ID");
        model.addColumn("Company Name");
        model.addColumn("Address");
        model.addColumn("Phone");
        model.addColumn("Email");

        sp.setBounds(20, 400, 560, 120);
        mainPanel.add(sp);

        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(t1.getText());
                String phone = t4.getText();
                String email = t5.getText();

                if (phone.length() != 10) {
                    throw new InvalidPhoneException("Phone must be 10 digits!");
                }

                if (!email.contains("@")) {
                    throw new InvalidEmailException("Invalid Email!");
                }

                Company c = new Company(
                        id, t2.getText(), t3.getText(),
                        phone, email, t6.getText()
                );

                new CompanyDAO().addCompany(c);
                JOptionPane.showMessageDialog(f, "Company Added!");

            } catch (InvalidPhoneException | InvalidEmailException ex) {
                JOptionPane.showMessageDialog(f, ex.getMessage());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Invalid Input!");
            }
        });
        viewBtn.addActionListener(e -> {
    try {
        ResultSet rs = new CompanyDAO().getAllCompanies();

        model.setRowCount(0);

        while (rs.next()) {
            model.addRow(new Object[]{
        rs.getInt("id"),
        rs.getString("name"),
        rs.getString("address"),
        rs.getString("phone"),
        rs.getString("email")
});
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(f,
                    "No Company Records Found!");
        }

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(f,
                "Error loading company details!");
    }
});
        updateBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(t1.getText());
                Company c = new Company(
                        id, t2.getText(), t3.getText(),
                        t4.getText(), t5.getText(), t6.getText()
                );

                CompanyDAO dao = new CompanyDAO();
                dao.updateCompany(c);

                JOptionPane.showMessageDialog(f, "Company Updated!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Update Failed!");
            }
        });
        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(t1.getText());

                CompanyDAO dao = new CompanyDAO();
                dao.deleteCompany(id);

                JOptionPane.showMessageDialog(f, "Company Deleted!");

                // Clear fields
                t1.setText(""); t2.setText(""); t3.setText("");
                t4.setText(""); t5.setText(""); t6.setText("");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Delete Failed!");
            }
        });
        searchBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(t1.getText());

                CompanyDAO dao = new CompanyDAO();
                Company c = dao.searchCompany(id);

                if (c != null) {
                    t2.setText(c.getName());
                    t3.setText(c.getContactPerson());
                    t4.setText(c.getPhone());
                    t5.setText(c.getEmail());
                    t6.setText(c.getAddress());
                } else {
                    JOptionPane.showMessageDialog(f, "Company Not Found!");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(f, "Search Failed!");
            }
        });
        clearBtn.addActionListener(e -> {
            t1.setText(""); t2.setText(""); t3.setText("");
            t4.setText(""); t5.setText(""); t6.setText("");
        });
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new CompanyUI();
    }
}