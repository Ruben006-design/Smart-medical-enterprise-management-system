package mini;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.awt.*;

public class PurchaseUI {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JTextField idField, companyField, productField, dateField;
    JTextField qtyField, priceField, totalField;

    public PurchaseUI() {

        frame = new JFrame("Purchase Management");
        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        frame.setIconImage(icon.getImage());
        Color bgColor = new Color(245, 239, 230);      
        Color panelColor = new Color(133, 88, 111);   
        Color btnColor = new Color(193, 154, 107);     
        Color textColor = Color.WHITE;
        frame.getContentPane().setBackground(bgColor);
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 0, 170, 650);
        sidePanel.setBackground(panelColor);

        JLabel sideTitle = new JLabel("Purchase Panel");
        sideTitle.setForeground(Color.WHITE);
        sideTitle.setFont(new Font("Arial", Font.BOLD, 18));
        sideTitle.setBounds(20, 30, 150, 30);

        JButton companyBtn = new JButton("Company");
        JButton productBtn = new JButton("Product");
        JButton backBtn = new JButton("Back");

        companyBtn.setBounds(20, 100, 120, 35);
        productBtn.setBounds(20, 150, 120, 35);
        backBtn.setBounds(20, 500, 120, 35);

        JButton[] sideButtons = {companyBtn, productBtn, backBtn};

        for (JButton b : sideButtons) {
            b.setBackground(btnColor);
            b.setForeground(Color.BLACK);
            b.setFocusPainted(false);
            sidePanel.add(b);
        }

        sidePanel.add(sideTitle);

        companyBtn.addActionListener(e -> {
            frame.dispose();
            new CompanyUI();
        });

        productBtn.addActionListener(e -> {
            frame.dispose();
            new ProductUI();
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            new MenuUI();
        });

        frame.add(sidePanel);
        JLabel title = new JLabel("Purchase Form");
        JLabel l1 = new JLabel("Purchase ID:");
        JLabel l2 = new JLabel("Company ID:");
        JLabel l3 = new JLabel("Product ID:");
        JLabel l4 = new JLabel("Date (YYYY-MM-DD):");
        JLabel l5 = new JLabel("Quantity:");
        JLabel l6 = new JLabel("Price:");
        JLabel l7 = new JLabel("Total:");

        title.setFont(new Font("Arial", Font.BOLD, 22));
        idField = new JTextField();
        companyField = new JTextField();
        productField = new JTextField();
        dateField = new JTextField();
        qtyField = new JTextField();
        priceField = new JTextField();
        totalField = new JTextField();
        totalField.setEditable(false);

        JTextField[] fields = {
                idField, companyField, productField,
                dateField, qtyField, priceField, totalField
        };

        for (JTextField tf : fields) {
            tf.setFont(new Font("Arial", Font.PLAIN, 14));
        }
        JButton calcBtn = new JButton("Calculate");
        JButton addBtn = new JButton("Create");
        JButton searchBtn = new JButton("Search");
        JButton viewBtn = new JButton("View All");
        JButton clearBtn = new JButton("Clear");
        JButton historyBtn = new JButton("Company History");

        JButton[] buttons = {
                calcBtn, addBtn, searchBtn,
                viewBtn, clearBtn, historyBtn
        };

        for (JButton b : buttons) {
            b.setBackground(btnColor);
            b.setFocusPainted(false);
            b.setFont(new Font("Arial", Font.BOLD, 13));
        }
        model = new DefaultTableModel();
        table = new JTable(model);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane sp = new JScrollPane(table);

        model.addColumn("ID");
        model.addColumn("Company");
        model.addColumn("Product");
        model.addColumn("Date");
        model.addColumn("Qty");
        model.addColumn("Price");
        model.addColumn("Total");
        title.setBounds(300, 10, 200, 30);

        l1.setBounds(220, 60, 150, 25);
        idField.setBounds(370, 60, 150, 28);

        l2.setBounds(220, 100, 150, 25);
        companyField.setBounds(370, 100, 150, 28);

        l3.setBounds(220, 140, 150, 25);
        productField.setBounds(370, 140, 150, 28);

        l4.setBounds(220, 180, 150, 25);
        dateField.setBounds(370, 180, 150, 28);

        l5.setBounds(220, 220, 150, 25);
        qtyField.setBounds(370, 220, 150, 28);

        l6.setBounds(220, 260, 150, 25);
        priceField.setBounds(370, 260, 150, 28);

        l7.setBounds(220, 300, 150, 25);
        totalField.setBounds(370, 300, 150, 28);

        calcBtn.setBounds(200, 350, 110, 35);
        addBtn.setBounds(320, 350, 100, 35);
        searchBtn.setBounds(430, 350, 100, 35);

        historyBtn.setBounds(540, 350, 150, 35);

        viewBtn.setBounds(280, 400, 110, 35);
        clearBtn.setBounds(410, 400, 110, 35);

        sp.setBounds(190, 460, 520, 160);

        calcBtn.addActionListener(e -> {
            try {
                int qty = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());
                totalField.setText(String.valueOf(qty * price));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid Input!");
            }
        });

        addBtn.addActionListener(e -> {
            try {
                int purchaseId = Integer.parseInt(idField.getText());
                int companyId = Integer.parseInt(companyField.getText());
                int productId = Integer.parseInt(productField.getText());
                int quantity = Integer.parseInt(qtyField.getText());
                double price = Double.parseDouble(priceField.getText());

                if (quantity <= 0) {
                    throw new InvalidQuantityException(
                            "Quantity must be greater than zero!"
                    );
                }

                if (price <= 0) {
                    throw new InvalidPriceException(
                            "Purchase price must be greater than zero!"
                    );
                }

                Purchase p = new Purchase(
                        purchaseId,
                        companyId,
                        dateField.getText(),
                        productId,
                        quantity,
                        price
                );

                new PurchaseDAO().createPurchase(p);

                JOptionPane.showMessageDialog(
                        frame,
                        "Purchase Added Successfully!"
                );

                loadTable();

            } catch (InvalidQuantityException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());

            } catch (InvalidPriceException ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Error Adding Purchase!"
                );
            }
        });

        searchBtn.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText());

                Purchase p = new PurchaseDAO().searchPurchase(id);

                if (p != null) {
                    companyField.setText(String.valueOf(p.getCompanyId()));
                    productField.setText(String.valueOf(p.getProductId()));
                    dateField.setText(p.getPurchaseDate());
                    qtyField.setText(String.valueOf(p.getQuantity()));
                    priceField.setText(String.valueOf(p.getPurchasePrice()));
                    totalField.setText(String.valueOf(p.getTotalAmount()));
                } else {
                    JOptionPane.showMessageDialog(frame, "Not Found!");
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Search Error!");
            }
        });

        viewBtn.addActionListener(e -> loadTable());

        clearBtn.addActionListener(e -> {
            idField.setText("");
            companyField.setText("");
            productField.setText("");
            dateField.setText("");
            qtyField.setText("");
            priceField.setText("");
            totalField.setText("");
        });

        frame.add(title);

        frame.add(l1); frame.add(idField);
        frame.add(l2); frame.add(companyField);
        frame.add(l3); frame.add(productField);
        frame.add(l4); frame.add(dateField);
        frame.add(l5); frame.add(qtyField);
        frame.add(l6); frame.add(priceField);
        frame.add(l7); frame.add(totalField);

        frame.add(calcBtn);
        frame.add(addBtn);
        frame.add(searchBtn);
        frame.add(viewBtn);
        frame.add(clearBtn);
        frame.add(historyBtn);

        frame.add(sp);

        frame.setSize(750, 680);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadTable() {
        try {
            ResultSet rs = new PurchaseDAO().getAllPurchases();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("purchase_id"),
                        rs.getInt("company_id"),
                        rs.getInt("product_id"),
                        rs.getString("purchase_date"),
                        rs.getInt("quantity"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("total_amount")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new PurchaseUI();
    }
}