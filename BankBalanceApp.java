import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankBalanceApp extends JFrame{

    // declare private fields
    private JTextField deposit;
    private JTextField withdraw;
    private JLabel balanceLabel;
    // everyone gets a 100$ signing bonus for their new accounts, yay!
    private double accountBalance = 100.0;

    public BankBalanceApp() {
        super("Banking Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        setSize(600, 400);
        // center the window 
        setLocationRelativeTo(null);
    
        // make the main panel
        JPanel mainPanel = new JPanel();
        // two by two grid
        mainPanel.setLayout(new GridLayout(2, 2, 5, 5));
    
        // create components for the main panel
    
        JLabel depositLabel = new JLabel("Amount for deposit:");
        deposit = new JTextField();
    
        JLabel withdrawLabel = new JLabel("Withdraw Amount:");
        withdraw = new JTextField();
    
        // add to mainPanel
        mainPanel.add(depositLabel);
        mainPanel.add(deposit);
        mainPanel.add(withdrawLabel);
        mainPanel.add(withdraw);
    
        // create a second panel for the buttons
        JPanel buttonPanel = new JPanel();
        
        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        

        balanceLabel = new JLabel(String.format("Current Balance: %.2f", accountBalance));
    
        // ad buttons and label to the buttonPanel
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);
        buttonPanel.add(balanceLabel);
        
        // create button listener for deposit button
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act){
                try{
                    double depositAmount = Double.parseDouble(deposit.getText());
                    // make sure deposit is greater than 0
                    if( depositAmount <= 0){
                        JOptionPane.showMessageDialog(BankBalanceApp.this, "Cannot deposit 0 or negative amount");
                        balanceLabel.setText(String.format("Current Balance: %.2f", accountBalance));
                    }else{
                        // add deposit to account balance
                        accountBalance += depositAmount;
                        deposit.setText("");
                        balanceLabel.setText(String.format("Current Balance: %.2f", accountBalance));
                    }

                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(BankBalanceApp.this, e);
                }
            }
        });

        // create withdraw button listener
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent act){
                double withdrawAmount = Double.parseDouble(withdraw.getText());
                try {
                    if(withdrawAmount <=0 ){
                        JOptionPane.showMessageDialog(BankBalanceApp.this, "Cannot withdraw 0 or negative");
                        
                    }else if (withdrawAmount > accountBalance){
                        JOptionPane.showMessageDialog(BankBalanceApp.this, "Insufficient Funds: Cannot withdraw more than you have");
                        
                    }else {
                        accountBalance -= withdrawAmount;
                        withdraw.setText("");
                        balanceLabel.setText(String.format("Current Balance: %.2f", accountBalance));
                    }

                    balanceLabel.setText(String.format("Current Balance: %.2f", accountBalance));
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(BankBalanceApp.this, e);
                }
            }
        });

        // add the panels to the frame
        add(mainPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    
        // MAKE IT SHOW UP ON THE SCREEN!
        setVisible(true);
    }
    
    public static void main(String[] args){
        new BankBalanceApp();
    }
}