
package mini;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
public class InventoryUI {
    JFrame frame;
    JTable table;
    DefaultTableModel model;
    JTextField invIdField, productField, stockField, reorderField, dateField;
    public InventoryUI() {
        frame = new JFrame("Inventory Management");
        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        frame.setIconImage(icon.getImage());
        frame.setSize(550, 650);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(35, 35, 55)); // dark theme
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 13);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 13);

        JLabel title = new JLabel("Inventory Management");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(150, 15, 250, 30);
        frame.add(title);

        JLabel l1 = new JLabel("Inventory ID:");
        JLabel l2 = new JLabel("Product ID:");
        JLabel l3 = new JLabel("Stock Quantity:");
        JLabel l4 = new JLabel("Reorder Level:");
        JLabel l5 = new JLabel("Last Updated:");

        JLabel[] labels = {l1, l2, l3, l4, l5};

        int y = 70;
        for (JLabel lbl : labels) {
            lbl.setFont(labelFont);
            lbl.setForeground(Color.WHITE);
            lbl.setBounds(50, y, 150, 25);
            frame.add(lbl);
            y += 45;
        }
        invIdField = new JTextField();
        productField = new JTextField();
        stockField = new JTextField();
        reorderField = new JTextField();
        dateField = new JTextField();

        JTextField[] fields = {
                invIdField, productField,
                stockField, reorderField, dateField
        };

        y = 70;
        for (JTextField tf : fields) {
            tf.setBounds(220, y, 180, 28);
            tf.setFont(fieldFont);
            tf.setBackground(new Color(245, 245, 245));
            tf.setBorder(BorderFactory.createLineBorder(
                    new Color(180, 120, 255), 2));
            frame.add(tf);
            y += 45;
        }

        JButton updateBtn = createButton("Update", buttonFont);
        JButton lowBtn = createButton("Low Stock", buttonFont);
        JButton viewBtn = createButton("View All", buttonFont);
        JButton clearBtn = createButton("Clear", buttonFont);
        JButton backBtn = createButton("Back", buttonFont);

        updateBtn.setBounds(30, 310, 100, 35);
        lowBtn.setBounds(145, 310, 120, 35);
        viewBtn.setBounds(280, 310, 100, 35);
        clearBtn.setBounds(395, 310, 100, 35);
        backBtn.setBounds(420, 20, 90, 30);

        frame.add(updateBtn);
        frame.add(lowBtn);
        frame.add(viewBtn);
        frame.add(clearBtn);
        frame.add(backBtn);

        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("Inventory ID");
        model.addColumn("Product ID");
        model.addColumn("Stock");
        model.addColumn("Reorder");
        model.addColumn("Updated");

        table.setRowHeight(25);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13));
        table.getTableHeader().setBackground(
                new Color(180, 120, 255));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 380, 500, 180);
        frame.add(sp);


        backBtn.addActionListener(e -> {
            frame.dispose();
            new MenuUI();
        });

        updateBtn.addActionListener(e -> {
            try {
                int stock = Integer.parseInt(stockField.getText());
                int reorder = Integer.parseInt(reorderField.getText());

                if (stock <= 0 || reorder <= 0) {
                    throw new InvalidQuantityException(
                            "Stock and Reorder must be greater than 0!"
                    );
                }

                if (stock < reorder) {
                    throw new LowStockException(
                            "Warning: Product is in Low Stock!"
                    );
                }

                if (dateField.getText().trim().isEmpty()) {
                    throw new InvalidDateException(
                            "Date cannot be empty!"
                    );
                }

                Inventory i = new Inventory(
                        Integer.parseInt(invIdField.getText()),
                        Integer.parseInt(productField.getText()),
                        stock,
                        reorder,
                        dateField.getText()
                );

                new InventoryDAO().updateInventory(i);

                JOptionPane.showMessageDialog(
                        frame,
                        "Inventory Updated Successfully!"
                );

                loadTable();

            } catch (InvalidQuantityException |
                     InvalidDateException |
                     LowStockException ex) {

                JOptionPane.showMessageDialog(
                        frame,
                        ex.getMessage()
                );

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Update Error!"
                );
            }
        });

        lowBtn.addActionListener(e -> {
            try {
                ResultSet rs = new InventoryDAO()
                        .getLowStockProducts();

                model.setRowCount(0);

                while (rs.next()) {
                    model.addRow(new Object[]{
                            rs.getInt("inventory_id"),
                            rs.getInt("product_id"),
                            rs.getInt("stock_quantity"),
                            rs.getInt("reorder_level"),
                            rs.getString("last_updated")
                    });
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        viewBtn.addActionListener(e -> loadTable());

        clearBtn.addActionListener(e -> {
            invIdField.setText("");
            productField.setText("");
            stockField.setText("");
            reorderField.setText("");
            dateField.setText("");
        });

        frame.setVisible(true);
    }

    private JButton createButton(String text, Font font) {
        JButton btn = new JButton(text);
        btn.setFont(font);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(180, 120, 255));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder());
        return btn;
    }

    private void loadTable() {
        try {
            ResultSet rs = new InventoryDAO().getAllInventory();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("inventory_id"),
                        rs.getInt("product_id"),
                        rs.getInt("stock_quantity"),
                        rs.getInt("reorder_level"),
                        rs.getString("last_updated")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InventoryUI();
    }
}