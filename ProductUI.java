package mini;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ProductUI {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JTextField idField, nameField, companyIdField,
            mrpField, purchaseField, sellingField;

    public ProductUI() {
        frame = new JFrame("Product Management");
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

        JLabel title = new JLabel("PRODUCT MANAGEMENT SYSTEM");
        title.setBounds(250, 15, 500, 35);
        title.setFont(new Font("Arial", Font.BOLD, 26));
        title.setForeground(Color.WHITE);

        topPanel.add(title);
        frame.add(topPanel);

        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 70, 180, 580);
        sidePanel.setBackground(new Color(44, 62, 80)); // Dark Gray Blue

        JButton companyBtn = new JButton("Company");
        JButton purchaseBtn = new JButton("Purchase");
        JButton inventoryBtn = new JButton("Inventory");
        JButton reportBtn = new JButton("Report");
        JButton backBtn = new JButton("Back");

        companyBtn.setBounds(20, 40, 140, 35);
        purchaseBtn.setBounds(20, 90, 140, 35);
        inventoryBtn.setBounds(20, 140, 140, 35);
        reportBtn.setBounds(20, 190, 140, 35);
        backBtn.setBounds(20, 240, 140, 35);

        sidePanel.add(companyBtn);
        sidePanel.add(purchaseBtn);
        sidePanel.add(inventoryBtn);
        sidePanel.add(reportBtn);
        sidePanel.add(backBtn);

        frame.add(sidePanel);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBounds(190, 80, 730, 520);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel formTitle = new JLabel("Product Form");
        formTitle.setBounds(280, 10, 200, 30);
        formTitle.setFont(new Font("Arial", Font.BOLD, 22));
        formTitle.setForeground(new Color(25, 25, 112));

        JLabel l1 = new JLabel("Product ID:");
        JLabel l2 = new JLabel("Product Name:");
        JLabel l3 = new JLabel("Company ID:");
        JLabel l4 = new JLabel("MRP:");
        JLabel l5 = new JLabel("Purchase Price:");
        JLabel l6 = new JLabel("Selling Price:");

        Font labelFont = new Font("Arial", Font.PLAIN, 14);

        l1.setFont(labelFont);
        l2.setFont(labelFont);
        l3.setFont(labelFont);
        l4.setFont(labelFont);
        l5.setFont(labelFont);
        l6.setFont(labelFont);
        idField = new JTextField();
        nameField = new JTextField();
        companyIdField = new JTextField();
        mrpField = new JTextField();
        purchaseField = new JTextField();
        sellingField = new JTextField();

        l1.setBounds(50, 60, 130, 25);
        idField.setBounds(180, 60, 180, 28);

        l2.setBounds(50, 100, 130, 25);
        nameField.setBounds(180, 100, 180, 28);

        l3.setBounds(50, 140, 130, 25);
        companyIdField.setBounds(180, 140, 180, 28);

        l4.setBounds(50, 180, 130, 25);
        mrpField.setBounds(180, 180, 180, 28);

        l5.setBounds(50, 220, 130, 25);
        purchaseField.setBounds(180, 220, 180, 28);

        l6.setBounds(50, 260, 130, 25);
        sellingField.setBounds(180, 260, 180, 28);
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton clearBtn = new JButton("Clear");
        JButton viewBtn = new JButton("View All");

        addBtn.setBounds(420, 60, 120, 35);
        updateBtn.setBounds(560, 60, 120, 35);

        deleteBtn.setBounds(420, 110, 120, 35);
        searchBtn.setBounds(560, 110, 120, 35);

        clearBtn.setBounds(420, 160, 120, 35);
        viewBtn.setBounds(560, 160, 120, 35);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Company ID");
        model.addColumn("MRP");
        model.addColumn("Purchase");
        model.addColumn("Selling");

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 320, 660, 170);
        mainPanel.add(sp);
        companyBtn.addActionListener(e -> {
            frame.dispose();
            new CompanyUI();
        });

        purchaseBtn.addActionListener(e -> {
            frame.dispose();
            new PurchaseUI();
        });

        inventoryBtn.addActionListener(e -> {
            frame.dispose();
            new InventoryUI();
        });

        reportBtn.addActionListener(e -> {
            frame.dispose();
            new ReportUI();
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            new MenuUI();
        });
        viewBtn.addActionListener(e -> loadTable());
        addBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int companyId = Integer.parseInt(companyIdField.getText());

                double mrp = Double.parseDouble(mrpField.getText());
                double purchasePrice =
                        Double.parseDouble(purchaseField.getText());
                double sellingPrice =
                        Double.parseDouble(sellingField.getText());

                if (purchasePrice > sellingPrice) {
                    throw new InvalidPriceException(
                            "Purchase Price cannot be greater than Selling Price!"
                    );
                }

                if (sellingPrice > mrp) {
                    throw new InvalidPriceException(
                            "Selling Price cannot be greater than MRP!"
                    );
                }

                Product p = new Product(
                        id,
                        nameField.getText(),
                        companyId,
                        mrp,
                        purchasePrice,
                        sellingPrice
                );

                new ProductDAO().addProduct(p);

                JOptionPane.showMessageDialog(
                        frame,
                        "Product Added Successfully!"
                );

                loadTable();

            } catch (InvalidPriceException ex) {
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
        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                new ProductDAO().deleteProduct(id);

                JOptionPane.showMessageDialog(
                        frame,
                        "Product Deleted!"
                );

                loadTable();

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

                Product p = new ProductDAO().searchProduct(id);

                if (p != null) {
                    nameField.setText(p.getProductName());
                    companyIdField.setText(
                            String.valueOf(p.getCompanyId())
                    );
                    mrpField.setText(
                            String.valueOf(p.getMrp())
                    );
                    purchaseField.setText(
                            String.valueOf(p.getPurchasePrice())
                    );
                    sellingField.setText(
                            String.valueOf(p.getSellingPrice())
                    );
                } else {
                    JOptionPane.showMessageDialog(
                            frame,
                            "Product Not Found!"
                    );
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Search Failed!"
                );
            }
        });
        updateBtn.addActionListener(e -> {
            try {
                Product p = new Product(
                        Integer.parseInt(idField.getText()),
                        nameField.getText(),
                        Integer.parseInt(companyIdField.getText()),
                        Double.parseDouble(mrpField.getText()),
                        Double.parseDouble(purchaseField.getText()),
                        Double.parseDouble(sellingField.getText())
                );

                new ProductDAO().updateProduct(p);

                JOptionPane.showMessageDialog(
                        frame,
                        "Product Updated!"
                );

                loadTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Update Failed!"
                );
            }
        });

        clearBtn.addActionListener(e -> {
            idField.setText("");
            nameField.setText("");
            companyIdField.setText("");
            mrpField.setText("");
            purchaseField.setText("");
            sellingField.setText("");
        });

        mainPanel.add(formTitle);

        mainPanel.add(l1);
        mainPanel.add(idField);

        mainPanel.add(l2);
        mainPanel.add(nameField);

        mainPanel.add(l3);
        mainPanel.add(companyIdField);

        mainPanel.add(l4);
        mainPanel.add(mrpField);

        mainPanel.add(l5);
        mainPanel.add(purchaseField);

        mainPanel.add(l6);
        mainPanel.add(sellingField);

        mainPanel.add(addBtn);
        mainPanel.add(updateBtn);
        mainPanel.add(deleteBtn);
        mainPanel.add(searchBtn);
        mainPanel.add(clearBtn);
        mainPanel.add(viewBtn);

        mainPanel.add(sp);

        frame.add(mainPanel);

        frame.setVisible(true);
    }

    private void loadTable() {
        try {
            ResultSet rs = new ProductDAO().getAllProducts();

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("company_id"),
                        rs.getDouble("mrp"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("selling_price")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new ProductUI();
    }
}