/*package mini;
import javax.swing.*;
public class MenuUI {
    JFrame frame;
    public MenuUI() {
        frame = new JFrame("Vinayak Enterprises - Menu");
        JLabel title = new JLabel("Welcome to Vinayak Enterprises Management System!");
        title.setBounds(40, 20, 500, 30);
        JButton compBtn = new JButton("1. Company Management");
        JButton prodBtn = new JButton("2. Product Management");
        JButton custBtn = new JButton("3. Customer Management");
        JButton purchaseBtn = new JButton("4. Purchase Management");
        JButton salesBtn = new JButton("5. Sales Management");
        JButton inventoryBtn = new JButton("6. Inventory Management");
        JButton reportBtn = new JButton("7. Report Management");
        //JButton empBtn = new JButton("8. Employee Management");
        JButton logoutBtn = new JButton("Logout");
        compBtn.setBounds(80, 60, 250, 30);
        prodBtn.setBounds(80, 100, 250, 30);
        custBtn.setBounds(80, 140, 250, 30);
        purchaseBtn.setBounds(80, 180, 250, 30);
        salesBtn.setBounds(80, 220, 250, 30);
        inventoryBtn.setBounds(80, 260, 250, 30);
        reportBtn.setBounds(80, 300, 250, 30);
        //empBtn.setBounds(80, 340, 250, 30);
        logoutBtn.setBounds(150, 380, 120, 30);
        frame.add(title);
        frame.add(compBtn);
        frame.add(prodBtn);
        frame.add(custBtn);
        frame.add(purchaseBtn);
        frame.add(salesBtn);
        frame.add(inventoryBtn);
        frame.add(reportBtn);
        //frame.add(empBtn);
        frame.add(logoutBtn); 
        compBtn.addActionListener(e ->{
                JOptionPane.showMessageDialog(frame, "Company Management Module Opened");
                frame.dispose();
                new CompanyUI();});
        prodBtn.addActionListener(e ->{
                JOptionPane.showMessageDialog(frame, "Product Management Module Opened");
                frame.dispose();
                new ProductUI();});
        custBtn.addActionListener(e ->{
                JOptionPane.showMessageDialog(frame, "Customer Management Module Opened");
                frame.dispose();
                new CustomerUI();});
        purchaseBtn.addActionListener(e ->{
                JOptionPane.showMessageDialog(frame, "Purchase Management Module Opened");
                frame.dispose();
                new PurchaseUI();});
        salesBtn.addActionListener(e ->{
                JOptionPane.showMessageDialog(frame, "Sales Management Module Opened");
                frame.dispose();
                new SalesUI();});
        inventoryBtn.addActionListener(e ->{
                JOptionPane.showMessageDialog(frame, "Inventory Management Module Opened");
                frame.dispose();
                new InventoryUI();});
        reportBtn.addActionListener(e ->{
                JOptionPane.showMessageDialog(frame, "Profit Analysis Module Opened");
                frame.dispose();
                new ReportUI();});
        //empBtn.addActionListener(e ->
          //      JOptionPane.showMessageDialog(frame, "Employee Management Module Opened"));
        logoutBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to logout?",
                    "Logout",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose();      
                new AdminLoginUI();       
            }
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 480);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}*/
package mini;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MenuUI extends JFrame {
    private JPanel mainPanel;

    public MenuUI() {
        setupFrame();
        createComponents();
    }

    private void setupFrame() {
        setTitle("Vinayak Enterprises - Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        setIconImage(icon.getImage());
        setSize(700, 750);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createComponents() {
        // Main panel with gradient background
        mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(236, 240, 241),
                        0, getHeight(), new Color(189, 195, 199));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Menu Panel
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = createFooterPanel();
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GradientPaint gradient = new GradientPaint(0, 0, new Color(41, 128, 185),
                        0, getHeight(), new Color(52, 152, 219));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(25, 30, 25, 30));

        // Company Logo/Icon
        JLabel logoLabel = new JLabel("🏢");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Company Name
        JLabel companyName = new JLabel("VINAYAK ENTERPRISES");
        companyName.setFont(new Font("Arial", Font.BOLD, 28));
        companyName.setForeground(Color.WHITE);
        companyName.setAlignmentX(Component.CENTER_ALIGNMENT);

        // System Title
        JLabel systemTitle = new JLabel("Management System");
        systemTitle.setFont(new Font("Arial", Font.PLAIN, 16));
        systemTitle.setForeground(new Color(200, 220, 240));
        systemTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(companyName);
        headerPanel.add(Box.createVerticalStrut(5));
        headerPanel.add(systemTitle);

        return headerPanel;
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setOpaque(false);
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBorder(new EmptyBorder(30, 60, 30, 60));

        // Create menu items
        String[][] menuItems = {
                {"1. Company Management", "Manage company details and information"},
                {"2. Product Management", "Add, update, and manage products"},
                {"3. Customer Management", "Manage customer information and records"},
                {"4. Purchase Management", "Handle purchase orders and suppliers"},
                {"5. Sales Management", "Manage sales transactions and orders"},
                {"6. Inventory Management", "Track and manage inventory levels"},
                {"7. Report Management", "Generate profit analysis and reports"}
        };

        for (String[] item : menuItems) {
            JPanel buttonPanel = createMenuButtonPanel(item[0], item[1]);
            menuPanel.add(buttonPanel);
            menuPanel.add(Box.createVerticalStrut(12));
        }

        return menuPanel;
    }

    private JPanel createMenuButtonPanel(String title, String description) {
        JPanel buttonPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.setColor(Color.WHITE);
                g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
                g2d.setColor(new Color(200, 200, 200));
                g2d.setStroke(new BasicStroke(1.5f));
                g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            }
        };
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(new EmptyBorder(12, 15, 12, 15));
        buttonPanel.setMaximumSize(new Dimension(600, 60));
        buttonPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Title label
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        titleLabel.setForeground(new Color(41, 128, 185));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        // Description label
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        descLabel.setForeground(new Color(100, 100, 100));
        descLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        buttonPanel.add(titleLabel);
        buttonPanel.add(Box.createVerticalStrut(3));
        buttonPanel.add(descLabel);

        // Extract module name from title
        String moduleName = extractModuleName(title);

        // Add click functionality
        buttonPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buttonPanel.setBackground(new Color(41, 128, 185));
                titleLabel.setForeground(Color.WHITE);
                descLabel.setForeground(new Color(220, 220, 220));
                buttonPanel.setOpaque(true);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buttonPanel.setOpaque(false);
                titleLabel.setForeground(new Color(41, 128, 185));
                descLabel.setForeground(new Color(100, 100, 100));
            }

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleMenuClick(moduleName);
            }
        });

        return buttonPanel;
    }

    private String extractModuleName(String title) {
        // Extract module name from title
        if (title.contains("Company")) return "Company";
        if (title.contains("Product")) return "Product";
        if (title.contains("Customer")) return "Customer";
        if (title.contains("Purchase")) return "Purchase";
        if (title.contains("Sales")) return "Sales";
        if (title.contains("Inventory")) return "Inventory";
        if (title.contains("Report")) return "Report";
        return "";
    }

    private void handleMenuClick(String module) {
        JOptionPane.showMessageDialog(this,
                module + " Management Module Opened",
                "Module Opening",
                JOptionPane.INFORMATION_MESSAGE);
        
        dispose();
        
        try {
            switch (module) {
                case "Company":
                    new CompanyUI();
                    break;
                case "Product":
                    new ProductUI();
                    break;
                case "Customer":
                    new CustomerUI();
                    break;
                case "Purchase":
                    new PurchaseUI();
                    break;
                case "Sales":
                    new SalesUI();
                    break;
                case "Inventory":
                    new InventoryUI();
                    break;
                case "Report":
                    new ReportUI();
                    break;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error opening module: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            new MenuUI(); // Reopen menu on error
        }
    }

    private JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setOpaque(false);
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.X_AXIS));
        footerPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Spacer
        footerPanel.add(Box.createHorizontalGlue());

        // Logout Button
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Arial", Font.BOLD, 13));
        logoutBtn.setBackground(new Color(231, 76, 60));
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        logoutBtn.setFocusPainted(false);
        logoutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutBtn.setMaximumSize(new Dimension(120, 40));

        logoutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutBtn.setBackground(new Color(192, 57, 43));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutBtn.setBackground(new Color(231, 76, 60));
            }
        });

        logoutBtn.addActionListener(e -> handleLogout());

        footerPanel.add(logoutBtn);

        return footerPanel;
    }

    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Confirm Logout",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new AdminLoginUI();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuUI());
    }
}
