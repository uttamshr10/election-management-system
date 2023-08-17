// 1.1.6

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class AdminGuidelinesForm extends JFrame {
	private JButton backButton;
    public AdminGuidelinesForm() {
        setTitle("Admin Guidelines Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null);

        // Create the main panel with vertical BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the title label with font size 16 and bold
        JLabel titleLabel = new JLabel("Guidelines for Administrator");
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f).deriveFont(Font.BOLD));
        mainPanel.add(titleLabel);

        // Create a method to create guideline items with font size 14
        Font guidelineFont = new Font("Arial", Font.PLAIN, 14);

        // Candidate Registration
        createGuidelineItem(mainPanel, guidelineFont, "Candidate Registration:\n" ,
                "- Register new candidates by providing their Name, Party, and Symbol.",
                "- Ensure all candidate information is accurate and up-to-date.",
                "- Avoid registering duplicate candidates or candidates with incomplete information.");

        // User Registration
        createGuidelineItem(mainPanel, guidelineFont, "User Registration:",
                "- Register new voters by providing their Name, unique Citizenship Number, and Mobile Number.",
                "- Verify the Citizenship Number to prevent multiple registrations from the same person.",
                "- Ensure voter details are correctly entered to maintain an accurate voter list.");

        // Edit/Update Candidates and Voters
        createGuidelineItem(mainPanel, guidelineFont, "Edit/Update Candidates and Voters:",
                "- Use the \"Edit/Update\" functionality to modify candidate and voter information as needed.",
                "- Make sure to update details promptly if there are any changes or corrections.");

        // Remove Candidate
        createGuidelineItem(mainPanel, guidelineFont, "Remove Candidate:",
                "- Carefully consider before removing a candidate from the election.",
                "- Only remove a candidate if there are valid reasons, such as withdrawal from the election.");


        backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                AuthenticatedForm authenticated = new AuthenticatedForm();
                authenticated.setVisible(true);
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add the main panel to the main frame
        add(mainPanel);

        setVisible(true);
    }

    private void createGuidelineItem(JPanel mainPanel, Font font, String title, String... items) {
        // Create the title label for the guideline item with font size 14 and bold
        JLabel titleLabel = new JLabel(title);
        Font titleFont = font.deriveFont(Font.BOLD);
        titleLabel.setFont(titleFont);
        mainPanel.add(titleLabel);

        // Create the list of guideline items
        for (String item : items) {
            JLabel itemLabel = new JLabel(item);
            itemLabel.setFont(font);
            mainPanel.add(itemLabel);
        }

        // Add some spacing between guideline items
        mainPanel.add(Box.createVerticalStrut(10));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminGuidelinesForm();
            }
        });
    }
}
