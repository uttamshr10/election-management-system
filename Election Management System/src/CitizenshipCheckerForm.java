// 1.2.1.1

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CitizenshipCheckerForm extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CitizenshipCheckerForm();
            }
        });
    }

    public CitizenshipCheckerForm() {
        setTitle("Citizenship Checker Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the heading title label
        JLabel titleLabel = new JLabel("Check your name in voter list");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        // Create the text label for entering citizenship number
        JLabel inputLabel = new JLabel("Enter your citizenship number:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 15));
        inputLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        int gap = 30;
        mainPanel.add(Box.createVerticalStrut(gap));
        mainPanel.add(inputLabel);

        // Create the input field for citizenship number
        JTextField citizenshipField = new JTextField(20);
        mainPanel.add(Box.createVerticalStrut(gap));
        citizenshipField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(citizenshipField);

        // Add some vertical spacing between components
        mainPanel.add(Box.createVerticalStrut(gap));

        // Create the "Check name" button
        JButton checkButton = new JButton("Check name");
        checkButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(checkButton);
        
        JButton backButton = new JButton("Go Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(backButton);
        
        mainPanel.add(checkButton);
        mainPanel.add(Box.createVerticalStrut(15)); // Add a vertical gap of 10 pixels
        mainPanel.add(backButton);
        
        // Add an event listener for the "Check name" button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                // Add your code to handle the "Check name" button action here
                String citizenshipNumber = citizenshipField.getText();
                try {
                    long checked = Long.parseLong(citizenshipNumber);

                    // Establish connection to the database
                    Connection con = new Database().getConnection();

                    // Query the database to check if the user is registered
                    String queryRegistered = "SELECT citizenship FROM UserRegistration_tbl WHERE citizenship = ?";
                    PreparedStatement pstRegistered = con.prepareStatement(queryRegistered);
                    pstRegistered.setLong(1, checked);
                    ResultSet rsRegistered = pstRegistered.executeQuery();

                    // Query the database to check if the user has already voted
                    String queryVoted = "SELECT citizenship FROM VoteCount_tbl WHERE citizenship = ?";
                    PreparedStatement pstVoted = con.prepareStatement(queryVoted);
                    pstVoted.setLong(1, checked);
                    ResultSet rsVoted = pstVoted.executeQuery();

                    if (rsRegistered.next()) { // If the user is registered
                        if (!rsVoted.next()) { // If the user has not voted
                            ListCandidateForm.openListCandidateForm(citizenshipNumber);
                        } else {
                        	JOptionPane.showMessageDialog(CitizenshipCheckerForm.this,
                        		    "<html><font color='red'>Warning! You've already voted and this is against the law.</font></html>");
                        }
                    } else {
                        JOptionPane.showMessageDialog(CitizenshipCheckerForm.this, "You are not registered.");
                    }

                    con.close(); // Close the database connection
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(CitizenshipCheckerForm.this, "Please enter a valid number.");
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	UserForm user = new UserForm();
            	user.setVisible(true);
            }
        });

        // Create a wrapper panel to center mainPanel vertically
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(mainPanel);

        add(wrapperPanel, BorderLayout.CENTER);
        setVisible(true);
    }
}
