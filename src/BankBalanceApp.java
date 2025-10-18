import javax.swing.*;
import java.awt.event.*;

public class BankBalanceApp extends JFrame {
    public static void main(String[] args) {
        // Create a window (frame)
        JFrame frame = new JFrame("Bank Balance");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel to hold everything
        JPanel panel = new JPanel();

        // Create components
        JLabel balanceLabel = new JLabel("Balance: $0.00");
        JTextField amountField = new JTextField(10);
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton closeButton = new JButton("Close");

        // Add components to panel
        panel.add(balanceLabel);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);
        panel.add(depositButton);
        panel.add(withdrawButton);
        panel.add(closeButton);

        // Add panel to frame
        frame.add(panel);
        frame.setVisible(true);

        // Create a variable to hold the balance
        final double[] balance = {0.0};

        // Deposit button action
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                balance[0] += amount;
                balanceLabel.setText("Balance: $" + String.format("%.2f", balance[0]));
                amountField.setText("");//Clear input
            }
        });

        // Withdraw button action
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(amountField.getText());
                if (amount <= balance[0]) {
                    balance[0] -= amount;
                    balanceLabel.setText("Balance: $" + String.format("%.2f", balance[0]));
                } else {
                    JOptionPane.showMessageDialog(frame, "Not enough funds!");
                    amountField.setText("");//Clear Input
                }
            }
        });

        //Close button action
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Final Balance: $" + String.format("%.2f", balance[0]));
                JOptionPane.showMessageDialog(frame, "Thank you for using Bank Balance App!");
                System.exit(0); //Exit the Program
            }
        });
    }
}