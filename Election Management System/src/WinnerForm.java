import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WinnerForm extends JFrame {
	private JButton backButton;
	public WinnerForm() {
        setTitle("Election Winner");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 5, 10); // Add vertical gap between components

        JLabel winnerLabel = new JLabel("Announcement");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(winnerLabel, gbc);

        gbc.insets = new Insets(30, 10, 5, 10); // Add a vertical gap of 30 pixels

        try {
            Connection con = new Database().getConnection();

            String candidateCheckQuery = "SELECT COUNT(*) AS count FROM UserRegistration_tbl " +
                                         "WHERE citizenship NOT IN (SELECT DISTINCT citizenship FROM VoteCount_tbl)";
            PreparedStatement candidateCheckPst = con.prepareStatement(candidateCheckQuery);
            ResultSet candidateCheckRs = candidateCheckPst.executeQuery();

            if (candidateCheckRs.next()) {
                int count = candidateCheckRs.getInt("count");

                if (count == 0) { // All registered citizenship numbers exist in VoteCount_tbl
                    String voteQuery = "SELECT candidate, party, COUNT(candidate) AS votes FROM VoteCount_tbl " +
                                       "GROUP BY candidate, party ORDER BY votes DESC";
                    PreparedStatement votePst = con.prepareStatement(voteQuery);
                    ResultSet voteRs = votePst.executeQuery();

                    if (voteRs.next()) {
                        String winnerName = voteRs.getString("candidate");
                        String winnerParty = voteRs.getString("party");
                        int totalVotes = voteRs.getInt("votes");

                        JLabel resultLabel = new JLabel("Winner: " + winnerName + " (" + winnerParty + ") with " + totalVotes + " votes.");
                        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
                        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        add(resultLabel, gbc);
                    } else {
                        // No votes recorded in the VoteCount_tbl
                        JLabel resultLabel = new JLabel("No votes recorded yet.");
                        resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
                        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        add(resultLabel, gbc);
                    }
                } else {
                    JLabel resultLabel = new JLabel("Election is not officially over yet.");
                    resultLabel.setFont(new Font("Arial", Font.BOLD, 20));
                    resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    add(resultLabel, gbc);
                }
            }

            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                HomePage mainpage = new HomePage(); // Create an instance of the authenticated view
                mainpage.setVisible(true); // Show the authenticated view
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        add(buttonPanel, gbc);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WinnerForm());
    }
}




