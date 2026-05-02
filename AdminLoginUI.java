package mini;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AdminLoginUI extends JFrame {

    private JTextField userField;
    private JPasswordField passField;
    private JButton loginBtn;
    private JLabel errorLabel;

    public AdminLoginUI() {
        setupFrame();
        createComponents();
        addListeners();
    }

    private void setupFrame() {
        setTitle("Login - VINAYAK ENTERPRISES");

        ImageIcon icon = new ImageIcon(getClass().getResource("IMG-20260501-WA0012.jpg"));
        setIconImage(icon.getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void createComponents() {

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                GradientPaint gradient = new GradientPaint(
                        0, 0, new Color(41, 128, 185),
                        0, getHeight(), new Color(52, 152, 219)
                );

                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        mainPanel.setLayout(new BorderLayout());

        JPanel headerPanel = createHeaderPanel();
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = createFormPanel();
        mainPanel.add(formPanel, BorderLayout.CENTER);

        add(mainPanel);
    }

    private JPanel createHeaderPanel() {

        JPanel headerPanel = new JPanel();
        headerPanel.setOpaque(false);
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBorder(new EmptyBorder(30, 20, 20, 20));

        JLabel logoLabel = new JLabel("🏢");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 48));
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titleLabel = new JLabel("VINAYAK ENTERPRISES");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Management System");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(200, 220, 240));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(logoLabel);
        headerPanel.add(Box.createVerticalStrut(10));
        headerPanel.add(titleLabel);
        headerPanel.add(subtitleLabel);

        return headerPanel;
    }

    private JPanel createFormPanel() {

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(new EmptyBorder(30, 40, 40, 40));

        JLabel userLabel = new JLabel("Username");
        userLabel.setFont(new Font("Arial", Font.BOLD, 12));
        userLabel.setForeground(Color.WHITE);
        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        userField = new JTextField();
        userField.setMaximumSize(new Dimension(300, 40));
        userField.setFont(new Font("Arial", Font.PLAIN, 14));
        userField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        userField.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel passLabel = new JLabel("Password");
        passLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passLabel.setForeground(Color.WHITE);
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        passField = new JPasswordField();
        passField.setMaximumSize(new Dimension(300, 40));
        passField.setFont(new Font("Arial", Font.PLAIN, 14));
        passField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 150, 200), 2),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        passField.setAlignmentX(Component.LEFT_ALIGNMENT);

        errorLabel = new JLabel();
        errorLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        errorLabel.setForeground(new Color(230, 100, 100));
        errorLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginBtn = new JButton("Login");
        loginBtn.setMaximumSize(new Dimension(300, 45));
        loginBtn.setFont(new Font("Arial", Font.BOLD, 14));
        loginBtn.setBackground(new Color(46, 204, 113));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        loginBtn.setFocusPainted(false);
        loginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginBtn.setAlignmentX(Component.LEFT_ALIGNMENT);

        loginBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(39, 174, 96));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginBtn.setBackground(new Color(46, 204, 113));
            }
        });

        formPanel.add(userLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(userField);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(passLabel);
        formPanel.add(Box.createVerticalStrut(8));
        formPanel.add(passField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(errorLabel);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(loginBtn);

        return formPanel;
    }

    private void addListeners() {

        loginBtn.addActionListener(e -> handleLogin());

        KeyListener enterListener = new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    handleLogin();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}

            @Override
            public void keyTyped(KeyEvent e) {}
        };

        userField.addKeyListener(enterListener);
        passField.addKeyListener(enterListener);
    }

    private void handleLogin() {

        String username = userField.getText().trim();
        String password = new String(passField.getPassword());

        errorLabel.setText("");

        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("⚠ Please fill in all fields");
            return;
        }

        if (username.equals("admin") && password.equals("1234")) {
            JOptionPane.showMessageDialog(this,
                    "Welcome to Vinayak Enterprises Management System!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE);

            dispose();
            new MenuUI();

        } else {
            errorLabel.setText("⚠ Invalid Username or Password!");
            passField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminLoginUI());
    }
}