import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankBalanceApp {

    public static void main(String[] args) {
        showLoginScreen();
    }

    public static void showLoginScreen() {
        // Show login instructions
        JOptionPane.showMessageDialog(null, "Welcome!\nPlease log in with:\nUsername: username\nPassword: 1234");

        // Create login window
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(500, 120);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Use FlowLayout for horizontal layout
        JPanel loginPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Username and password fields
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(10);
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");

        // Add components to panel
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        // Add panel to frame
        loginFrame.add(loginPanel);
        loginFrame.setVisible(true);

        // Login button action
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword()).trim();

                System.out.println("Username entered: '" + username + "'");
                System.out.println("Password entered: '" + password + "'");

                if (username.equals("username") && password.equals("1234")) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful!");
                    loginFrame.dispose();
                    showBankBalanceUI();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid credentials!");
                }
            }
        });
    }

    public static void showBankBalanceUI() {
        JFrame frame = new JFrame("Bank Balance");
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();

        JLabel balanceLabel = new JLabel("Balance: $0.00");
        JTextField amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton closeButton = new JButton("Close");

        panel.add(balanceLabel);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(closeButton);
        frame.add(panel);
        frame.setVisible(true);

        final double[] balance = {0.0};

        depositButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            balance[0] += amount;
            balanceLabel.setText("Balance: $" + String.format("%.2f", balance[0]));
            amountField.setText("");
        });

        withdrawButton.addActionListener(e -> {
            double amount = Double.parseDouble(amountField.getText());
            if (amount <= balance[0]) {
                balance[0] -= amount;
                balanceLabel.setText("Balance: $" + String.format("%.2f", balance[0]));
            } else {
                JOptionPane.showMessageDialog(frame, "Not enough funds!");
                amountField.setText("");
            }
        });

        closeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Final Balance: $" + String.format("%.2f", balance[0]));
            JOptionPane.showMessageDialog(frame, "Thank you for using Bank Balance App!");
            System.exit(0);
        });
    }
}
