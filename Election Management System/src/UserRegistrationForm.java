// 1.1.2

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationForm extends JFrame {

    private JLabel nameLabel, citizenshipLabel, mobileLabel, welcomeLabel;
    private JTextField nameField, citizenshipField, mobileField;
    private JButton addButton, backButton;

    public UserRegistrationForm() {
        setTitle("User Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set the gaps: top, left, bottom, right

        welcomeLabel = new JLabel("Create new User.");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center-align the label
        mainPanel.add(welcomeLabel, gbc);
        
        // Create the Label for Name
        nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST; // Right-align the label
        mainPanel.add(nameLabel, gbc);

        // Create the Input Field for Name
        nameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST; // Left-align the field
        mainPanel.add(nameField, gbc);

        // Create the Label for Party
        citizenshipLabel = new JLabel("Citizenship Number:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST; // Right-align the label
        mainPanel.add(citizenshipLabel, gbc);

        citizenshipField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; // Left-align the field
        mainPanel.add(citizenshipField, gbc);

        // Create the Label for Symbol
        mobileLabel = new JLabel("Mobile Number:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST; // Right-align the label
        mainPanel.add(mobileLabel, gbc);

        mobileField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST; // Left-align the field
        mainPanel.add(mobileField, gbc);

        // Create the Add Candidate Button
        addButton = new JButton("Add User");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center-align the button
        mainPanel.add(addButton, gbc);
        
        backButton = new JButton("Back Button");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(backButton, gbc);
        // Add the main panel to the main frame
        add(mainPanel);
        Font textFont = new Font("Arial", Font.BOLD, 16);
        welcomeLabel.setFont(textFont);
        addButton.setFont(textFont);

        
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = nameField.getText();
                String citizenship = citizenshipField.getText();
                String mobile = mobileField.getText();
                if (user == null || user.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(UserRegistrationForm.this, "Please enter a valid user name.");
                    return; // Exit the action listener
                }
                try {
                    long number = Long.parseLong(citizenship);
                    long phone = Long.parseLong(mobile);

                    // Check if the user with the same citizenship number already exists
                    Connection con = new Database().getConnection();
                    String checkQuery = "SELECT * FROM UserRegistration_tbl WHERE citizenship = ?";
                    PreparedStatement checkPst = con.prepareStatement(checkQuery);
                    checkPst.setLong(1, number);
                    ResultSet rs = checkPst.executeQuery();

                    if (rs.next()) {
                        // User with the same citizenship number already exists
                        JOptionPane.showMessageDialog(UserRegistrationForm.this, "User with citizenship number " + number + " is already registered!");
                    } else {
                        // Insert data into the database
                        String insertQuery = "INSERT INTO UserRegistration_tbl (name, citizenship, mobile) VALUES (?, ?, ?)";
                        PreparedStatement pst = con.prepareStatement(insertQuery);
                        pst.setString(1, user);
                        pst.setLong(2, number);
                        pst.setLong(3, phone);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(UserRegistrationForm.this, "User " + user + " is registered!");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(UserRegistrationForm.this, "Please enter valid number.");
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	AuthenticatedForm authenticated = new AuthenticatedForm();
            	authenticated.setVisible(true);
            }
        });        
        
        setVisible(true);
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserRegistrationForm();
            }
        });
    }
}