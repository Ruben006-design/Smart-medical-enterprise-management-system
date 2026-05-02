package mini;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.awt.*;
public class SalesUI {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JTextField saleIdField, customerField, productField, dateField;
    JTextField qtyField, priceField, totalField;

    public SalesUI() {

        frame = new JFrame("Sales Management");
        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        frame.setIconImage(icon.getImage());
        Color bgColor = new Color(250, 244, 230);      // soft cream
        Color sideColor = new Color(92, 64, 51);       // brown
        Color buttonColor = new Color(205, 133, 63);   // warm orange
        Color textWhite = Color.WHITE;

        frame.getContentPane().setBackground(bgColor);
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 0, 170, 750);
        sidePanel.setBackground(sideColor);

        JLabel sideTitle = new JLabel("Sales Panel");
        sideTitle.setForeground(textWhite);
        sideTitle.setFont(new Font("Arial", Font.BOLD, 18));
        sideTitle.setBounds(25, 30, 130, 30);

        JButton customerBtn = new JButton("Customer");
        JButton productBtn = new JButton("Product");
        JButton inventoryBtn = new JButton("Inventory");
        JButton backBtn = new JButton("Back");

        customerBtn.setBounds(20, 100, 120, 35);
        productBtn.setBounds(20, 150, 120, 35);
        inventoryBtn.setBounds(20, 200, 120, 35);
        backBtn.setBounds(20, 550, 120, 35);

        JButton[] sideButtons = {
                customerBtn, productBtn,
                inventoryBtn, backBtn
        };

        for (JButton b : sideButtons) {
            b.setBackground(buttonColor);
            b.setForeground(Color.BLACK);
            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 12));
            sidePanel.add(b);
        }

        sidePanel.add(sideTitle);
        frame.add(sidePanel);

        customerBtn.addActionListener(e -> {
            frame.dispose();
            new CustomerUI();
        });

        productBtn.addActionListener(e -> {
            frame.dispose();
            new ProductUI();
        });

        inventoryBtn.addActionListener(e -> {
            frame.dispose();
            new InventoryUI();
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            new MenuUI();
        });

        JLabel title = new JLabel("Sales Form");
        JLabel l1 = new JLabel("Sale ID:");
        JLabel l2 = new JLabel("Customer ID:");
        JLabel l3 = new JLabel("Product ID:");
        JLabel l4 = new JLabel("Date (YYYY-MM-DD):");
        JLabel l5 = new JLabel("Quantity:");
        JLabel l6 = new JLabel("Selling Price:");
        JLabel l7 = new JLabel("Total Amount:");

        title.setFont(new Font("Arial", Font.BOLD, 22));
        saleIdField = new JTextField();
        customerField = new JTextField();
        productField = new JTextField();
        dateField = new JTextField();
        qtyField = new JTextField();
        priceField = new JTextField();
        totalField = new JTextField();

        totalField.setEditable(false);

        JTextField[] fields = {
                saleIdField, customerField, productField,
                dateField, qtyField, priceField, totalField
        };

        for (JTextField tf : fields) {
            tf.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        JButton calcBtn = new JButton("Calculate");
        JButton addBtn = new JButton("Place Order");
        JButton searchBtn = new JButton("Search");
        JButton deleteBtn = new JButton("Cancel Sale");
        JButton viewBtn = new JButton("View All");
        JButton clearBtn = new JButton("Clear");
        JButton historyBtn = new JButton("Order History");
        JButton preferredBtn = new JButton("Preferred Products");

        JButton[] buttons = {
                calcBtn, addBtn, searchBtn,
                deleteBtn, viewBtn, clearBtn,
                historyBtn, preferredBtn
        };

        for (JButton b : buttons) {
            b.setBackground(buttonColor);
            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 12));
        }
        model = new DefaultTableModel();
        table = new JTable(model);

        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 13)
        );
        JScrollPane sp = new JScrollPane(table);
        setDefaultColumns();
        title.setBounds(320, 10, 200, 30);

        l1.setBounds(220, 60, 150, 25);
        saleIdField.setBounds(380, 60, 150, 28);

        l2.setBounds(220, 100, 150, 25);
        customerField.setBounds(380, 100, 150, 28);

        l3.setBounds(220, 140, 150, 25);
        productField.setBounds(380, 140, 150, 28);

        l4.setBounds(220, 180, 150, 25);
        dateField.setBounds(380, 180, 150, 28);

        l5.setBounds(220, 220, 150, 25);
        qtyField.setBounds(380, 220, 150, 28);

        l6.setBounds(220, 260, 150, 25);
        priceField.setBounds(380, 260, 150, 28);

        l7.setBounds(220, 300, 150, 25);
        totalField.setBounds(380, 300, 150, 28);

        calcBtn.setBounds(210, 350, 110, 35);
        addBtn.setBounds(330, 350, 130, 35);
        searchBtn.setBounds(470, 350, 100, 35);

        historyBtn.setBounds(210, 400, 140, 35);
        preferredBtn.setBounds(360, 400, 170, 35);
        viewBtn.setBounds(540, 400, 100, 35);

        deleteBtn.setBounds(260, 450, 130, 35);
        clearBtn.setBounds(410, 450, 100, 35);

        sp.setBounds(190, 510, 520, 170);
        calcBtn.addActionListener(e -> {
            try {
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());
                totalField.setText(String.valueOf(qty * price));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid Input!"
                );
            }
        });
        addBtn.addActionListener(e -> {
    try {
        int saleId = Integer.parseInt(saleIdField.getText());
        int customerId = Integer.parseInt(customerField.getText());
        int productId = Integer.parseInt(productField.getText());
        int quantity = Integer.parseInt(qtyField.getText());
        double sellingPrice = Double.parseDouble(priceField.getText());
        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be greater than zero!"
            );
        }
        if (sellingPrice <= 0) {
            throw new InvalidPriceException(
                    "Selling price must be greater than zero!"
            );
        }
        int stock = new InventoryDAO().getCurrentStock(productId);

        if (stock < quantity) {
            throw new InsufficientStockException(
                    "Not enough stock available!"
            );
        }

        Sales s = new Sales(
                saleId,
                customerId,
                dateField.getText(),
                productId,
                quantity,
                sellingPrice
        );

        new SalesDAO().placeOrder(s);

        JOptionPane.showMessageDialog(
                frame,
                "Order Placed Successfully!"
        );

        loadTable();

    } catch (InvalidQuantityException ex) {
        JOptionPane.showMessageDialog(
                frame,
                ex.getMessage()
        );

    } catch (InvalidPriceException ex) {
        JOptionPane.showMessageDialog(
                frame,
                ex.getMessage()
        );

    } catch (InsufficientStockException ex) {
        JOptionPane.showMessageDialog(
                frame,
                ex.getMessage()
        );

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(
                frame,
                "Error while placing order!"
        );
    }
});

        searchBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(saleIdField.getText());

                Sales s = new SalesDAO().searchSale(id);

                if (s != null) {
                    customerField.setText(String.valueOf(s.getCustomerId()));
                    productField.setText(String.valueOf(s.getProductId()));
                    dateField.setText(s.getSaleDate());
                    qtyField.setText(String.valueOf(s.getQuantity()));
                    priceField.setText(String.valueOf(s.getSellingPrice()));
                    totalField.setText(String.valueOf(s.getTotalAmount()));
                } else {
                    JOptionPane.showMessageDialog(frame, "Not Found!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Search Error!");
            }
        });

        deleteBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(saleIdField.getText());
                new SalesDAO().cancelSale(id);

                JOptionPane.showMessageDialog(frame, "Sale Cancelled!");
                loadTable();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Delete Error!");
            }
        });

        viewBtn.addActionListener(e -> loadTable());

        historyBtn.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerField.getText());

                ResultSet rs = new SalesDAO().getOrderHistory(customerId);

                model.setRowCount(0);
                model.setColumnCount(0);

                model.addColumn("Sale ID");
                model.addColumn("Product Name");
                model.addColumn("Sale Date");
                model.addColumn("Quantity");
                model.addColumn("Selling Price");
                model.addColumn("Total Amount");

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

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "No Order History Found!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Enter valid Customer ID!");
            }
        });
        preferredBtn.addActionListener(e -> {
            try {
                int customerId = Integer.parseInt(customerField.getText());

                ResultSet rs = new SalesDAO().getPreferredProducts(customerId);
                model.setRowCount(0);
                model.setColumnCount(0);
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

                if (model.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(frame,
                            "No Preferred Products Found!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame,
                        "Enter valid Customer ID!");
            }
        });

        clearBtn.addActionListener(e -> {
            saleIdField.setText("");
            customerField.setText("");
            productField.setText("");
            dateField.setText("");
            qtyField.setText("");
            priceField.setText("");
            totalField.setText("");

            setDefaultColumns();
            model.setRowCount(0);
        });

        viewBtn.addActionListener(e -> loadTable());

        frame.add(title);

        frame.add(l1); frame.add(saleIdField);
        frame.add(l2); frame.add(customerField);
        frame.add(l3); frame.add(productField);
        frame.add(l4); frame.add(dateField);
        frame.add(l5); frame.add(qtyField);
        frame.add(l6); frame.add(priceField);
        frame.add(l7); frame.add(totalField);

        frame.add(calcBtn);
        frame.add(addBtn);
        frame.add(searchBtn);
        frame.add(historyBtn);
        frame.add(preferredBtn);
        frame.add(viewBtn);
        frame.add(deleteBtn);
        frame.add(clearBtn);

        frame.add(sp);

        frame.setSize(760, 760);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setDefaultColumns() {
        model.setColumnCount(0);

        model.addColumn("Sale ID");
        model.addColumn("Customer ID");
        model.addColumn("Product ID");
        model.addColumn("Date");
        model.addColumn("Qty");
        model.addColumn("Price");
        model.addColumn("Total");
    }

    private void loadTable() {
        try {
            ResultSet rs = new SalesDAO().getAllSales();

            setDefaultColumns();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("sale_id"),
                        rs.getInt("customer_id"),
                        rs.getInt("product_id"),
                        rs.getString("sale_date"),
                        rs.getInt("quantity"),
                        rs.getDouble("selling_price"),
                        rs.getDouble("total_amount")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new SalesUI();
    }
}