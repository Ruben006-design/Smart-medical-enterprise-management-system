
package mini;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;

public class ReportUI {

    JFrame frame;
    JTable table;
    DefaultTableModel model;

    JButton loadBtn, bestSellingBtn, suppliersBtn, customersBtn, backBtn;

    public ReportUI() {

        frame = new JFrame("Report Module");
        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        frame.setIconImage(icon.getImage());
        frame.setSize(850, 650);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(28, 28, 40)); // dark theme
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font titleFont = new Font("Segoe UI", Font.BOLD, 22);
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 13);
        Font tableFont = new Font("Segoe UI", Font.PLAIN, 13);

        JLabel title = new JLabel("Business Report Analysis");
        title.setFont(titleFont);
        title.setForeground(Color.WHITE);
        title.setBounds(270, 20, 320, 30);
        frame.add(title);


        loadBtn = createButton("Profit / Loss Report", buttonFont);
        bestSellingBtn = createButton("Best Selling Products", buttonFont);
        suppliersBtn = createButton("Active Suppliers", buttonFont);
        customersBtn = createButton("Active Customers", buttonFont);
        backBtn = createButton("Back", buttonFont);

        loadBtn.setBounds(30, 80, 180, 38);
        bestSellingBtn.setBounds(220, 80, 190, 38);
        suppliersBtn.setBounds(420, 80, 160, 38);
        customersBtn.setBounds(590, 80, 170, 38);

        backBtn.setBounds(360, 560, 110, 38);

        frame.add(loadBtn);
        frame.add(bestSellingBtn);
        frame.add(suppliersBtn);
        frame.add(customersBtn);
        frame.add(backBtn);

        model = new DefaultTableModel();
        table = new JTable(model);

        setProfitColumns();

        table.setRowHeight(28);
        table.setFont(tableFont);
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);

        table.getTableHeader().setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        // GREEN TABLE HEADER
        table.getTableHeader().setBackground(
                new Color(46, 125, 50)
        );
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(20, 140, 790, 390);
        frame.add(sp);
        loadBtn.addActionListener(e -> loadProfitReport());
        bestSellingBtn.addActionListener(e -> loadBestSellingProducts());
        suppliersBtn.addActionListener(e -> loadActiveSuppliers());
        customersBtn.addActionListener(e -> loadActiveCustomers());
        backBtn.addActionListener(e -> {
            frame.dispose();
            new MenuUI();
        });

        frame.setVisible(true);
    }

    private JButton createButton(String text, Font font) {
        JButton btn = new JButton(text);

        btn.setFont(font);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(46, 125, 50));
        btn.setForeground(Color.WHITE);

        btn.setBorder(BorderFactory.createEmptyBorder());

        return btn;
    }
    private void loadProfitReport() {
        try {
            ResultSet rs = new ReportDAO().getFullReport();

            setProfitColumns();
            model.setRowCount(0);

            while (rs.next()) {

                int productId = rs.getInt("product_id");
                String productName = rs.getString("product_name");

                double purchase = rs.getDouble("purchase_total");
                double sales = rs.getDouble("sales_total");

                double profit = sales - purchase;

                double margin = 0;
                if (sales != 0) {
                    margin = (profit / sales) * 100;
                }

                model.addRow(new Object[]{
                        productId,
                        productName,
                        purchase,
                        sales,
                        profit,
                        margin
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBestSellingProducts() {
        try {
            ResultSet rs = new ReportDAO().bestSellingProducts();

            model.setRowCount(0);
            model.setColumnCount(0);

            model.addColumn("Product ID");
            model.addColumn("Product Name");
            model.addColumn("Total Sold");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getInt("total_sold")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadActiveSuppliers() {
        try {
            ResultSet rs = new ReportDAO().getActiveSuppliers();

            model.setRowCount(0);
            model.setColumnCount(0);

            model.addColumn("Company ID");
            model.addColumn("Company Name");
            model.addColumn("Total Purchases");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("company_id"),
                        rs.getString("company_name"),
                        rs.getInt("total_purchases")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadActiveCustomers() {
        try {
            ResultSet rs = new ReportDAO().getActiveCustomers();

            model.setRowCount(0);
            model.setColumnCount(0);

            model.addColumn("Customer ID");
            model.addColumn("Customer Name");
            model.addColumn("Total Orders");
            model.addColumn("Total Purchase Amount");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("customer_id"),
                        rs.getString("customer_name"),
                        rs.getInt("total_orders"),
                        rs.getDouble("total_purchase_amount")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void setProfitColumns() {
        model.setColumnCount(0);

        model.addColumn("Product ID");
        model.addColumn("Product Name");
        model.addColumn("Purchase Cost");
        model.addColumn("Sales Revenue");
        model.addColumn("Profit/Loss");
        model.addColumn("Profit Margin %");
    }

    public static void main(String[] args) {
        new ReportUI();
    }
}