// 1.1.5

//import javax.swing.*;
//import java.awt.*;
//import java.sql.ResultSet;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class RemoveCandidateForm extends JFrame {
//    public void openRemoveCandidateForm() {
//        JFrame frame = new JFrame("Remove Candidate Form");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(750, 400);
//        frame.setLayout(new BorderLayout());
//        frame.setLocationRelativeTo(null);
//
//        try {
//            // Establish connection to the database
//            Connection con = new Database().getConnection();
//
//            // Query the database to retrieve candidate names and parties from CandidateRegistration_tbl
//            String query = "SELECT name, party FROM CandidateRegistration_tbl";
//            PreparedStatement pst = con.prepareStatement(query);
//            ResultSet rs = pst.executeQuery();
//
//            // Create a list to store the candidates in the format "Candidate name, Candidate party"
//            List<String> candidatesList = new ArrayList<>();
//
//            // Loop through the result set and add candidate details to the list
//            while (rs.next()) {
//                String name = rs.getString("name");
//                String party = rs.getString("party");
//                String candidateDetails = name + ", " + party;
//                candidatesList.add(candidateDetails);
//            }
//
//            // Convert the list to an array of strings
//            String[] candidates = candidatesList.toArray(new String[0]);
//            int gap = 10;
//            JPanel mainPanel = new JPanel();
//            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
//
//            JLabel titleLabel = new JLabel("All Mayor Candidates:");
//            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
//            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
//            mainPanel.add(titleLabel);
//            mainPanel.add(Box.createVerticalStrut(gap));
//            // Create checkboxes for each candidate
//            JCheckBox[] checkBoxes = new JCheckBox[candidates.length];
//            for (int i = 0; i < candidates.length; i++) {
//                checkBoxes[i] = new JCheckBox(candidates[i]);
//                mainPanel.add(checkBoxes[i]);
//                mainPanel.add(Box.createVerticalStrut(gap));
//            }
//
//            JButton removeButton = new JButton("Remove");
//            removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
//            mainPanel.add(removeButton);
//
//            // Add vertical glue to center the content vertically
//            mainPanel.add(Box.createVerticalGlue());
//
//            // Create a wrapper panel with GridBagLayout to center the mainPanel vertically
//            JPanel wrapperPanel = new JPanel(new GridBagLayout());
//            GridBagConstraints gbc = new GridBagConstraints();
//            gbc.weighty = 1.0;
//            gbc.anchor = GridBagConstraints.CENTER;
//            wrapperPanel.add(mainPanel, gbc);
//
//            frame.add(wrapperPanel, BorderLayout.CENTER);
//
//            // Add an event listener for the "Remove" button
//            removeButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    // Check if any candidate is selected
//                    for (JCheckBox checkBox : checkBoxes) {
//                        if (checkBox.isSelected()) {
//                            String selectedCandidateDetails = checkBox.getText();
//                            String[] candidateDetailsArray = selectedCandidateDetails.split(", ");
//                            String selectedCandidateName = candidateDetailsArray[0];
//                            String selectedParty = candidateDetailsArray[1];
//                            
//                            // Remove the candidate from the CandidateRegistration_tbl
//                            removeCandidate(selectedCandidateName, selectedParty);
//                            JOptionPane.showMessageDialog(frame, "Candidate successfully removed.");
//                            // Update the list of candidates to reflect the changes
//                            openRemoveCandidateForm();
//                            frame.dispose(); // Close the current form after removing the candidate
//                            return;
//                        }
//                    }
//
//                    // Error message if no candidate is selected
//                    JOptionPane.showMessageDialog(frame, "Please select a candidate to remove.");
//                }
//            });
//
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    private void removeCandidate(String candidateName, String party) {
//        try {
//            // Establish connection to the database
//            Connection con = new Database().getConnection();
//
//            // Remove the candidate from the CandidateRegistration_tbl
//            String query = "DELETE FROM CandidateRegistration_tbl WHERE name = ? AND party = ?";
//            PreparedStatement pst = con.prepareStatement(query);
//            pst.setString(1, candidateName);
//            pst.setString(2, party);
//            pst.executeUpdate();
//        } catch (ClassNotFoundException | SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
//}
//

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

public class RemoveCandidateForm extends JFrame {
    public void openRemoveCandidateForm() {
        JFrame frame = new JFrame("Remove Candidate Form");
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

            JLabel titleLabel = new JLabel("All Mayor Candidates:");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(titleLabel);
            mainPanel.add(Box.createVerticalStrut(gap));
            // Create checkboxes for each candidate
            JCheckBox[] checkBoxes = new JCheckBox[candidates.length];
            for (int i = 0; i < candidates.length; i++) {
                checkBoxes[i] = new JCheckBox(candidates[i]);
                mainPanel.add(checkBoxes[i]);
                mainPanel.add(Box.createVerticalStrut(gap));
            }

            JButton removeButton = new JButton("Remove");
            removeButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(removeButton);

            JButton backButton = new JButton("Go Back");
            backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(backButton);
            
            mainPanel.add(removeButton);
            mainPanel.add(Box.createVerticalStrut(15));
            mainPanel.add(backButton);
            
            // Add vertical glue to center the content vertically
            mainPanel.add(Box.createVerticalGlue());

            // Create a wrapper panel with GridBagLayout to center the mainPanel vertically
            JPanel wrapperPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            wrapperPanel.add(mainPanel, gbc);

            frame.add(wrapperPanel, BorderLayout.CENTER);

            // Add an event listener for the "Remove" button
            removeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check if any candidate is selected
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            String selectedCandidateDetails = checkBox.getText();
                            String[] candidateDetailsArray = selectedCandidateDetails.split(", ");
                            String selectedCandidateName = candidateDetailsArray[0];
                            String selectedParty = candidateDetailsArray[1];

                            // Remove the candidate from the CandidateRegistration_tbl
                            removeCandidate(selectedCandidateName, selectedParty);

                            // Remove the votes of the removed candidate from the VoteCount_tbl
                            removeCandidateVotes(selectedCandidateName, selectedParty);

                            JOptionPane.showMessageDialog(frame, "Candidate successfully removed.");
                            // Update the list of candidates to reflect the changes
                            openRemoveCandidateForm();
                            frame.dispose(); // Close the current form after removing the candidate
                            return;
                        }
                    }

                    // Error message if no candidate is selected
                    JOptionPane.showMessageDialog(frame, "Please select a candidate to remove.");
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

    private void removeCandidate(String candidateName, String party) {
        try {
            // Establish connection to the database
            Connection con = new Database().getConnection();

            // Remove the candidate from the CandidateRegistration_tbl
            String query = "DELETE FROM CandidateRegistration_tbl WHERE name = ? AND party = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, candidateName);
            pst.setString(2, party);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void removeCandidateVotes(String candidateName, String party) {
        try {
            // Establish connection to the database
            Connection con = new Database().getConnection();

            // Remove the votes of the candidate from the VoteCount_tbl
            String query = "DELETE FROM VoteCount_tbl WHERE candidate = ? AND party = ?";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, candidateName);
            pst.setString(2, party);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}
