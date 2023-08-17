// 1.2.1.1.1


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListCandidateForm {
    public static void openListCandidateForm(String citizenshipNumber) {
        JFrame frame = new JFrame("List Candidate Form");
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
            ButtonGroup buttonGroup = new ButtonGroup();
            for (int i = 0; i < candidates.length; i++) {
                checkBoxes[i] = new JCheckBox(candidates[i]);
                buttonGroup.add(checkBoxes[i]);
                mainPanel.add(checkBoxes[i]);
                mainPanel.add(Box.createVerticalStrut(gap));
            }

            JButton voteButton = new JButton("Vote");
            voteButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            mainPanel.add(voteButton);

            // Add vertical glue to center the content vertically
            mainPanel.add(Box.createVerticalGlue());

            // Create a wrapper panel with GridBagLayout to center the mainPanel vertically
            JPanel wrapperPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.CENTER;
            wrapperPanel.add(mainPanel, gbc);

            frame.add(wrapperPanel, BorderLayout.CENTER);

            // Add an event listener for the "Vote" button
            voteButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Check if any candidate is selected
                    boolean selected = false;
                    String selectedCandidateName = null;
                    String selectedParty = null;
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            selected = true;
                            String selectedCandidateDetails = checkBox.getText();
                            String[] candidateDetailsArray = selectedCandidateDetails.split(", ");
                            selectedCandidateName = candidateDetailsArray[0];
                            selectedParty = candidateDetailsArray[1];
                            break;
                        }
                    }

                    if (selected) {
                        // Insert the vote details into the database
                        insertVote(citizenshipNumber, selectedCandidateName, selectedParty);

                        // Thank you message for voting
                        JOptionPane.showMessageDialog(frame, "Thank you for your precious vote.");
                        System.exit(0);
                    } else {
                        // Error message if no candidate is selected
                        JOptionPane.showMessageDialog(frame, "Please select a candidate before voting.");
                    }
                }
            });

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void insertVote(String citizenshipNumber, String candidateName, String party) {
        try {
            // Establish connection to the database
            Connection con = new Database().getConnection();

            // Insert the vote details into the VoteCount_tbl
            String query = "INSERT INTO VoteCount_tbl (citizenship, candidate, party) VALUES (?, ?, ?)";
            PreparedStatement pst = con.prepareStatement(query);
            pst.setString(1, citizenshipNumber);
            pst.setString(2, candidateName);
            pst.setString(3, party);
            pst.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}


