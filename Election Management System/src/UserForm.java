// 1.2

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserForm extends JFrame {

    public UserForm() {
        setTitle("User Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(750, 400);

        // Create the main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Set the gaps: top, left, bottom, right

        JLabel titleLabel = new JLabel("Welcome to User Panel");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Use all available columns
        gbc.anchor = GridBagConstraints.CENTER; // Center-align the label
        mainPanel.add(titleLabel, gbc);

        JButton button1 = new JButton("VOTE NOW");
        JButton button2 = new JButton("Read User Guidelines");
        JButton button3 = new JButton("Go Back");

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER; // Center-align the buttons
        mainPanel.add(button1, gbc);

        gbc.gridx = 1;
        mainPanel.add(button2, gbc);
        
        gbc.gridx = 2;
        mainPanel.add(button3, gbc);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	CitizenshipCheckerForm check = new CitizenshipCheckerForm();
            	check.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	UserGuidelinesForm userguideline = new UserGuidelinesForm();
            	userguideline.setVisible(true);
            }
        });
        
        button3.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		setVisible(false);
        		HomePage mainpage = new HomePage();
        		mainpage.setVisible(true);
        	}
        });

        setContentPane(mainPanel);
        setLocationRelativeTo(null); // Center the form on the screen
               
        
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserForm();
            }
        });
    }
}

