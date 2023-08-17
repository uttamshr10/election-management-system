// 1.2.2

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

public class UserGuidelinesForm extends JFrame {
	private JButton backButton;
    public UserGuidelinesForm() {
        setTitle("User Guidelines Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null);

        // Create the main panel with vertical BoxLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Create the title label with font size 16 and bold
        JLabel titleLabel = new JLabel("Guidelines for User");
        titleLabel.setFont(titleLabel.getFont().deriveFont(18f).deriveFont(Font.BOLD));
        mainPanel.add(titleLabel);

        // Create a method to create guideline items with font size 14
        Font guidelineFont = new Font("Arial", Font.PLAIN, 14);

        // Candidate Registration
        createGuidelineItem(mainPanel, guidelineFont, "Voter Registration:\n" ,
                "- Ensure you are registered as a voter with your unique Citizenship Number.",
                "- Verify your details on the voter list before the election.");

        // Edit/Update Candidates and Voters
        createGuidelineItem(mainPanel, guidelineFont, "Voting Process:",
                "- Cast your vote for the Mayor Post of Kathmandu City by selecting one candidate only.",
                "- Review your choice carefully before confirming your vote.");


        // Data Security
        createGuidelineItem(mainPanel, guidelineFont, "Voting Only Once:",
                "- Remember that each voter is allowed to vote only once in the election.",
                "- Voting multiple times is against the law and may lead to legal consequences.");
        
        createGuidelineItem(mainPanel, guidelineFont, "Respect the Election Process:",
                "- Abide by the election rules and guidelines to maintain fairness and integrity.",
                "- Refrain from any activity that may disrupt or manipulate the election.");
        
        createGuidelineItem(mainPanel, guidelineFont, "Report Issues:",
                "- If you encounter any issues or discrepancies during the voting process, inform the election authorities.");
        
        backButton = new JButton("Go Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	UserForm userForm = new UserForm();
                userForm.setVisible(true);
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
                new UserGuidelinesForm();
            }
        });
    }
}
