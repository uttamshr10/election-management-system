// 1.1.0

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthenticatedForm extends JFrame {

    public AuthenticatedForm() {
        setTitle("Authenticated Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);
        setLocationRelativeTo(null); // Center the JFrame on the screen
        setLayout(new BorderLayout());

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome to Admin Panel:");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));

        JButton button1 = new JButton("Candidate Registration");
        JButton button2 = new JButton("User Registration");
        JButton button3 = new JButton("Update Candidate");
        JButton button4 = new JButton("Update User");
        JButton button5 = new JButton("Remove Candidate");
        JButton button6 = new JButton("Read Admin Guidelines");
        JButton backButton = new JButton("Go Back");

        int gap = 15; // Adjust the gap value as desired

        centerPanel.add(titleLabel);
        centerPanel.add(Box.createVerticalStrut(gap));
        centerPanel.add(button1);
        centerPanel.add(Box.createVerticalStrut(gap));
        centerPanel.add(button2);
        centerPanel.add(Box.createVerticalStrut(gap));
        centerPanel.add(button3);
        centerPanel.add(Box.createVerticalStrut(gap));
        centerPanel.add(button4);
        centerPanel.add(Box.createVerticalStrut(gap));
        centerPanel.add(button5);
        centerPanel.add(Box.createVerticalStrut(gap));
        centerPanel.add(button6);
        centerPanel.add(Box.createVerticalStrut(gap));
        centerPanel.add(backButton);
        // Add centerPanel to another JPanel to center it in BorderLayout.CENTER
        JPanel wrapperPanel = new JPanel(new GridBagLayout());
        wrapperPanel.add(centerPanel);

        add(wrapperPanel, BorderLayout.CENTER);
        
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	CandidateRegistrationForm candidate = new CandidateRegistrationForm();
            	candidate.setVisible(true);
            }
        });
        
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	UserRegistrationForm user = new UserRegistrationForm();
            	user.setVisible(true);
            }
        });
        
        
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	UpdateCandidateForm updatecandidate = new UpdateCandidateForm();
                updatecandidate.openUpdateCandidateForm(); // Call the instance method here
            }
        });
        
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	UpdateUserForm updateuser = new UpdateUserForm();
            	updateuser.openUpdateUserForm();
            }
        });
        
       
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                RemoveCandidateForm remove = new RemoveCandidateForm();
                remove.openRemoveCandidateForm(); // Call the instance method here
            }
        });

        
        button6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	AdminGuidelinesForm guideline = new AdminGuidelinesForm();
            	guideline.setVisible(true);
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	HomePage home = new HomePage();
            	home.setVisible(true);
            }
        });
        
        setVisible(true);
    }
}
