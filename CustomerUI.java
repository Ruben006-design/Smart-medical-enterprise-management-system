package mini;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.ResultSet;

public class CustomerUI {

    JFrame frame;
    JLabel title, idLbl, nameLbl, phoneLbl, emailLbl;
    JTextField idField, nameField, phoneField, emailField;
    JButton addBtn, updateBtn, deleteBtn, clearBtn;
    JButton salesBtn, preferredBtn, activeBtn;

    public CustomerUI() {
        frame = new JFrame("Customer Management System");
        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        frame.setIconImage(icon.getImage());
        frame.setSize(950, 650);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(245, 245, 245));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBounds(0, 0, 950, 70);
        topPanel.setBackground(new Color(25, 25, 112)); // Dark Blue

        JLabel mainTitle = new JLabel("CUSTOMER MANAGEMENT SYSTEM");
        mainTitle.setBounds(230, 15, 500, 35);
        mainTitle.setFont(new Font("Arial", Font.BOLD, 26));
        mainTitle.setForeground(Color.WHITE);

        topPanel.add(mainTitle);
        frame.add(topPanel);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 70, 180, 580);
        sidePanel.setBackground(new Color(44, 62, 80));

        JButton salesModuleBtn = new JButton("Sales");
        JButton reportBtn = new JButton("Report");
        JButton inventoryBtn = new JButton("Inventory");
        JButton productBtn = new JButton("Products");
        JButton backBtn = new JButton("Back");

        salesModuleBtn.setBounds(20, 40, 140, 35);
        reportBtn.setBounds(20, 90, 140, 35);
        inventoryBtn.setBounds(20, 140, 140, 35);
        productBtn.setBounds(20, 190, 140, 35);
        backBtn.setBounds(20, 240, 140, 35);

        sidePanel.add(salesModuleBtn);
        sidePanel.add(reportBtn);
        sidePanel.add(inventoryBtn);
        sidePanel.add(productBtn);
        sidePanel.add(backBtn);

        frame.add(sidePanel);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(190, 80, 730, 520);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        title = new JLabel("Customer Module");
        title.setBounds(250, 10, 250, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(25, 25, 112));
        idLbl = new JLabel("Customer ID:");
        nameLbl = new JLabel("Name:");
        phoneLbl = new JLabel("Phone:");
        emailLbl = new JLabel("Email:");

        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        idLbl.setFont(labelFont);
        nameLbl.setFont(labelFont);
        phoneLbl.setFont(labelFont);
        emailLbl.setFont(labelFont);

        idLbl.setBounds(50, 70, 120, 25);
        nameLbl.setBounds(50, 110, 120, 25);
        phoneLbl.setBounds(50, 150, 120, 25);
        emailLbl.setBounds(50, 190, 120, 25);

        idField = new JTextField();
        nameField = new JTextField();
        phoneField = new JTextField();
        emailField = new JTextField();

        idField.setBounds(180, 70, 180, 28);
        nameField.setBounds(180, 110, 180, 28);
        phoneField.setBounds(180, 150, 180, 28);
        emailField.setBounds(180, 190, 180, 28);
        addBtn = new JButton("Add");
        updateBtn = new JButton("Update");
        deleteBtn = new JButton("Delete");
        clearBtn = new JButton("Clear");
        JButton searchBtn = new JButton("Search");

        salesBtn = new JButton("Sales History");
        preferredBtn = new JButton("Preferred Products");
        activeBtn = new JButton("Active Customers");

        addBtn.setBounds(420, 70, 120, 35);
        updateBtn.setBounds(560, 70, 120, 35);

        deleteBtn.setBounds(420, 120, 120, 35);
        searchBtn.setBounds(560, 120, 120, 35);

        clearBtn.setBounds(490, 170, 120, 35);

        salesBtn.setBounds(50, 280, 180, 35);
        preferredBtn.setBounds(250, 280, 200, 35);
        activeBtn.setBounds(470, 280, 180, 35);
        backBtn.addActionListener(e -> {
            frame.dispose();
            new MenuUI();
        });

        salesModuleBtn.addActionListener(e -> {
            frame.dispose();
            new SalesUI();
        });

        productBtn.addActionListener(e -> {
            frame.dispose();
            new ProductUI();
        });

        inventoryBtn.addActionListener(e -> {
            frame.dispose();
            new InventoryUI();
        });

        reportBtn.addActionListener(e -> {
            frame.dispose();
            new ReportUI();
        });
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                String phone = phoneField.getText();
                String email = emailField.getText();

                if (phone.length() != 10) {
                    throw new InvalidPhoneException(
                            "Phone number must be 10 digits!"
                    );
                }

                if (!email.contains("@")) {
                    throw new InvalidEmailException(
                            "Invalid Email Format!"
                    );
                }

                Customer c = new Customer(
                        id,
                        nameField.getText(),
                        phone,
                        email,
                        ""
                );

                CustomerDAO dao = new CustomerDAO();
                dao.addCustomer(c);

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer Added Successfully!"
                );

            } catch (InvalidPhoneException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );

            } catch (InvalidEmailException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Input!"
                );
            }
        });
        updateBtn.addActionListener(e -> {
            try {
                Customer c = new Customer(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        phoneField.getText(),
                        emailField.getText(),
                        ""
                );

                CustomerDAO dao = new CustomerDAO();
                dao.updateCustomer(c);

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer Updated!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Update Failed!"
                );
            }
        });
        deleteBtn.addActionListener(e -> {
            try {

                int id = Integer.parseInt(idField.getText());

                CustomerDAO dao = new CustomerDAO();
                dao.deleteCustomer(id);

                JOptionPane.showMessageDialog(
                        frame,
                        "Customer Deleted!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Delete Failed!"
                );
            }
        });

        searchBtn.addActionListener(e -> {
            try {

                int id = Integer.parseInt(idField.getText());

                CustomerDAO dao = new CustomerDAO();
                Customer c = dao.searchCustomer(id);

                if (c != null) {
                    nameField.setText(c.getCustomerName());
                    phoneField.setText(c.getPhone());
                    emailField.setText(c.getEmail());
                } else {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Customer Not Found!"
                    );
                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        "Search Failed!"
                );
            }
        });
        clearBtn.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            phoneField.setText("");
            emailField.setText("");
        });
        salesBtn.addActionListener(e -> {
    try {
        int customerId = Integer.parseInt(idField.getText());

        ResultSet rs = new SalesDAO().getOrderHistory(customerId);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Sale ID");
        model.addColumn("Product Name");
        model.addColumn("Date");
        model.addColumn("Qty");
        model.addColumn("Price");
        model.addColumn("Total");

        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("sale_id"),
                    rs.getString("product_name"),
                    rs.getString("sale_date"),
                    rs.getInt("quantity"),
                    rs.getDouble("selling_price"),
                    rs.getDouble("total_amount")
            });
        }

        JOptionPane.showMessageDialog(frame, new JScrollPane(table),
                "Sales History", JOptionPane.PLAIN_MESSAGE);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "Enter valid Customer ID!");
    }
});
        preferredBtn.addActionListener(e -> {
    try {
        int customerId = Integer.parseInt(idField.getText());

        ResultSet rs = new SalesDAO().getPreferredProducts(customerId);

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Product ID");
        model.addColumn("Product Name");
        model.addColumn("Total Bought");

        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("product_id"),
                    rs.getString("product_name"),
                    rs.getInt("total_bought")
            });
        }

        JOptionPane.showMessageDialog(frame, new JScrollPane(table),
                "Preferred Products", JOptionPane.PLAIN_MESSAGE);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "Enter valid Customer ID!");
    }
});
        activeBtn.addActionListener(e -> {
    try {
        ResultSet rs = new ReportDAO().getActiveCustomers();

        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        model.addColumn("Customer ID");
        model.addColumn("Customer Name");
        model.addColumn("Total Orders");
        model.addColumn("Total Amount");

        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("customer_id"),
                    rs.getString("customer_name"),
                    rs.getInt("total_orders"),
                    rs.getDouble("total_purchase_amount")
            });
        }

        JOptionPane.showMessageDialog(frame, new JScrollPane(table),
                "Active Customers", JOptionPane.PLAIN_MESSAGE);

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(frame, "Error loading data!");
    }
});

        mainPanel.add(title);

        mainPanel.add(idLbl);
        mainPanel.add(idField);

        mainPanel.add(nameLbl);
        mainPanel.add(nameField);

        mainPanel.add(phoneLbl);
        mainPanel.add(phoneField);

        mainPanel.add(emailLbl);
        mainPanel.add(emailField);

        mainPanel.add(addBtn);
        mainPanel.add(updateBtn);
        mainPanel.add(deleteBtn);
        mainPanel.add(searchBtn);
        mainPanel.add(clearBtn);

        mainPanel.add(salesBtn);
        mainPanel.add(preferredBtn);
        mainPanel.add(activeBtn);

        frame.add(mainPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerUI();
    }
}