import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BankBalanceApp extends JFrame {

    private JTextField deposit;
    private JTextField withdraw;
    private JLabel balanceLabel;
    private double accountBalance = 100.0;

    /**
     * main app window, lays out components and
     * sets up event listeners for buttons clicked
     */
    public BankBalanceApp() {
        super("Banking Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel depositLabel = new JLabel("Amount for deposit:");
        deposit = new JTextField();

        JLabel withdrawLabel = new JLabel("Withdraw Amount:");
        withdraw = new JTextField();

        mainPanel.add(depositLabel);
        mainPanel.add(deposit);
        mainPanel.add(withdrawLabel);
        mainPanel.add(withdraw);

        JPanel buttonPanel = new JPanel();

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");

        balanceLabel = new JLabel(String.format("Current Balance: %.2f", accountBalance));

        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(balanceLabel);

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act) {
                try {
                    double depositAmount = Double.parseDouble(deposit.getText());
                    if (depositAmount <= 0) {
                        JOptionPane.showMessageDialog(BankBalanceApp.this, "Cannot deposit 0 or negative amount");
                    } else {
                        accountBalance += depositAmount;
                        deposit.setText("");
                    }
                    balanceLabel.setText(String.format("Current Balance: %.2f", accountBalance));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(BankBalanceApp.this, e);
                }
            }
        });

        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act) {
                try {
                    double withdrawAmount = Double.parseDouble(withdraw.getText());
                    if (withdrawAmount <= 0) {
                        JOptionPane.showMessageDialog(BankBalanceApp.this, "Cannot withdraw 0 or negative");
                    } else if (withdrawAmount > accountBalance) {
                        JOptionPane.showMessageDialog(BankBalanceApp.this, "Insufficient Funds: Cannot withdraw more than you have");
                    } else {
                        accountBalance -= withdrawAmount;
                        withdraw.setText("");
                    }
                    balanceLabel.setText(String.format("Current Balance: %.2f", accountBalance));
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(BankBalanceApp.this, e);
                }
            }
        });

        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }


    public static void main(String[] args) {
        new BankBalanceApp();
    }
}