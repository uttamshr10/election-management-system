// 1.1.1
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;
import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CandidateRegistrationForm extends JFrame {

    private JLabel nameLabel, partyLabel, welcomeLabel;
    private JTextField nameField;
    private JComboBox<String> partyDropdown;
    private JButton addButton, backButton;

    public CandidateRegistrationForm() {
        setTitle("Candidate Registration Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Set the gaps: top, left, bottom, right

        welcomeLabel = new JLabel("Create new Candidate.");
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
        partyLabel = new JLabel("Party:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST; // Right-align the label
        mainPanel.add(partyLabel, gbc);

        // Create the Dropdown Field for Party
        String[] partyOptions = {"Independent Candidate", "Nepali Congress", "Unified Yemale", "Rastriya Prajatantra Party", "Nepal Communist Party", "Rastriya Swotantra Party", "Right to Reject"};
        partyDropdown = new JComboBox<>(partyOptions);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST; // Left-align the field
        mainPanel.add(partyDropdown, gbc);


        // Create the Add Candidate Button
        addButton = new JButton("Add Candidate");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center-align the button
        mainPanel.add(addButton, gbc);

        backButton = new JButton("Go Back");
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
                String candidate = nameField.getText();
                String party = partyDropdown.getSelectedItem().toString();
                if (candidate == null || candidate.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(CandidateRegistrationForm.this, "Please enter a valid candidate name.");
                    return; // Exit the action listener
                }
                try {
                    // Check if the party already exists in the database
                    Connection con = new Database().getConnection();
                    String checkQuery = "SELECT COUNT(*) FROM CandidateRegistration_tbl WHERE party = ?";
                    PreparedStatement checkPst = con.prepareStatement(checkQuery);
                    checkPst.setString(1, party);
                    ResultSet rs = checkPst.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);

                    if (count > 0) {
                        JOptionPane.showMessageDialog(CandidateRegistrationForm.this, "Candidate with  " + party + " already exists!");
                    } else {
                        // Insert data into the database
                        String insertQuery = "INSERT INTO CandidateRegistration_tbl (name, party) VALUES (?, ?)";
                        PreparedStatement pst = con.prepareStatement(insertQuery);
                        pst.setString(1, candidate);
                        pst.setString(2, party);
                        pst.executeUpdate();

                        JOptionPane.showMessageDialog(CandidateRegistrationForm.this, "Candidate " + candidate + " is registered!");
                    }
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
                new CandidateRegistrationForm();
            }
        });
    }
}
