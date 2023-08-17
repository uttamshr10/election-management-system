// 1.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {

    private JTextArea textArea;
    private JButton adminButton, voterButton, winnerButton;

    public HomePage() {
        setTitle("Election Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null);

        textArea = new JTextArea(
        		"\n" +
                " Please follow the guidelines below:\n\n" +
                "- Admin can manage candidates and election results.\n\n" +
                "- Users can cast vote for the Mayor Post of Kathmandu City.\n\n" +
                "- Voters who are not registered cannot vote at the moment.\n\n" +
                "- Trying to vote multiple times is against the law.\n" +
                "\n" +
                " ** For any assistance, contact the Election Support Team at 01-5328663. **\n\n" +
                " Created and Designed by Uttam Shrestha and Sumit Singh Thakuri."
        );
        Font textFont = new Font("Arial", Font.BOLD, 16);
        textArea.setFont(textFont);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        adminButton = new JButton("Admin");
        voterButton = new JButton("Voter");
        winnerButton = new JButton("Winner");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(adminButton);
        buttonPanel.add(voterButton);
        buttonPanel.add(winnerButton);
        
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JLabel welcomeLabel = new JLabel("Welcome to the Election Management System!");
        Font welcomeFont = new Font("Arial", Font.BOLD, 24);
        welcomeLabel.setFont(welcomeFont);
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(welcomeLabel);

        contentPanel.add(Box.createVerticalGlue());

        contentPanel.add(textArea);

        contentPanel.add(buttonPanel);

        contentPanel.add(Box.createVerticalGlue());

        add(contentPanel);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
            }
        });

        voterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                UserForm userForm = new UserForm();
                userForm.setVisible(true);
            }
        });
        
        winnerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	WinnerForm winner = new WinnerForm();
                winner.setVisible(true);
            }
        });
        

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HomePage();
            }
        });
    }
}

