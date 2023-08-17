// 1.1.3

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateCandidateForm extends JFrame {
    public static void openUpdateCandidateForm() {
        JFrame frame = new JFrame("Update Candidate Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 400);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        try {
            // Establish connection to the database
            Connection con = new Database().getConnection();

            // Query the database to retrieve candidate names and parties from CandidateRegistration_tbl
            String query = "SELECT name, party FROM CandidateRegistration_tbl";
            PreparedStatement pst = con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            // Create a list to store the candidates in the format "Candidate name, Candidate party"
            List<String> candidatesList = new ArrayList<>();

            // Loop through the result set and add candidate details to the list
            while (rs.next()) {
                String name = rs.getString("name");
                String party = rs.getString("party");
                String candidateDetails = name + ", " + party;
                candidatesList.add(candidateDetails);
            }

            // Convert the list to an array of strings
            String[] candidates = candidatesList.toArray(new String[0]);
            int gap = 10;
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

            JLabel titleLabel = new JLabel("Update Candidate Details:");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(titleLabel);
            mainPanel.add(Box.createVerticalStrut(gap));

            // Create checkboxes for each candidate
            JCheckBox[] checkBoxes = new JCheckBox[candidates.length];
            ButtonGroup buttonGroup = new ButtonGroup();
            for (int i = 0; i < candidates.length; i++) {
                checkBoxes[i] = new JCheckBox(candidates[i]);
                buttonGroup.add(checkBoxes[i]);
                mainPanel.add(checkBoxes[i]);
                mainPanel.add(Box.createVerticalStrut(gap));
            }

            // Create the "Update" button
            JButton updateButton = new JButton("Update");
            updateButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(updateButton);
            
            JButton backButton = new JButton("Go Back");
            backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(backButton);
            
            mainPanel.add(updateButton);
            mainPanel.add(Box.createVerticalStrut(15));
            mainPanel.add(backButton);
            // Add vertical glue to center the content vertically
//            mainPanel.add(Box.createVerticalGlue());

            // Create a wrapper panel with GridBagLayout to center the mainPanel vertically
            JPanel wrapperPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            wrapperPanel.add(mainPanel, gbc);

            frame.add(wrapperPanel, BorderLayout.CENTER);

            // Add an event listener for the "Update" button
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check if any candidate is selected
                    boolean selected = false;
                    String selectedCandidateDetails = null;
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            selected = true;
                            selectedCandidateDetails = checkBox.getText();
                            break;
                        }
                    }

                    if (selected) {
                        // Show a dialog to update the candidate details
                        String[] candidateDetailsArray = selectedCandidateDetails.split(", ");
                        String candidateName = candidateDetailsArray[0];
                        String candidateParty = candidateDetailsArray[1];

                        String newName = JOptionPane.showInputDialog(frame, "Enter new name for the candidate:", candidateName);
                        String newParty = JOptionPane.showInputDialog(frame, "Enter new party for the candidate:", candidateParty);

                        // Update the candidate details in the database
                        updateCandidate(candidateName, candidateParty, newName, newParty);

                        JOptionPane.showMessageDialog(frame, "Candidate details updated successfully.");
                    } else {
                        // Error message if no candidate is selected
                        JOptionPane.showMessageDialog(frame, "Please select a candidate to update.");
                    }
                }
            });
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                	frame.setVisible(false);
                	AuthenticatedForm authenticated = new AuthenticatedForm();
                	authenticated.setVisible(true);
                }
            });
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void updateCandidate(String oldName, String oldParty, String newName, String newParty) {
        try {
            // Establish connection to the database
            Connection con = new Database().getConnection();

            // Update the candidate details in the CandidateRegistration_tbl
            String query = "UPDATE CandidateRegistration_tbl SET name = ?, party = ? " +
                           "WHERE name = ? AND party = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, newName);
            pst.setString(2, newParty);
            pst.setString(3, oldName);
            pst.setString(4, oldParty);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UpdateCandidateForm::openUpdateCandidateForm);
    }
}

