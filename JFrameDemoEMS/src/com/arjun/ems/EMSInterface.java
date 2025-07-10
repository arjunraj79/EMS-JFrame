package com.arjun.ems;

import javax.swing.*;
import java.awt.*;

public class EMSInterface {

    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("EMS");
        frame.getContentPane().setBackground(new Color(255, 255, 128));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.getContentPane().setLayout(null); // Absolute positioning
        
        @SuppressWarnings("unused")
		ImageIcon icon = new ImageIcon(EMSInterface.class.getResource("/com/arjun/ems/resources/icon.png"));
        
        ImageIcon logoIcon = new ImageIcon(EMSInterface.class.getResource("/com/arjun/ems/resources/icon.png"));
        Image scaledLogo = logoIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setBounds(20, 25, 90, 100); // Adjust for your layout
        frame.getContentPane().add(logoLabel);

        
        // Title Label
        JLabel title = new JLabel("Employee Management");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(new Color(128, 128, 255));
        title.setBounds(121, 33, 341, 40);
        frame.getContentPane().add(title);

        // Buttons
        JButton viewAllBtn = new JButton("VIEW ALL Employees");
        viewAllBtn.setBounds(50, 166, 180, 40);
        frame.getContentPane().add(viewAllBtn);
        //Action
        viewAllBtn.addActionListener(e -> {
            new DisplayAll(); // open the new window
        });


        JButton viewBtn = new JButton("VIEW Employee");
        viewBtn.setBounds(260, 166, 180, 40);
        frame.getContentPane().add(viewBtn);
        //Action
        viewBtn.addActionListener(e -> {
            new ViewEmployee();
        });

        JButton addBtn = new JButton("ADD Employee");
        addBtn.setBounds(50, 226, 180, 40);
        frame.getContentPane().add(addBtn);
        //Action
        addBtn.addActionListener(e -> {
            new AddEmployee();
        });

        JButton deleteBtn = new JButton("Delete Employee");
        deleteBtn.setBounds(260, 226, 180, 40);
        frame.getContentPane().add(deleteBtn);
        //Action
        deleteBtn.addActionListener(e -> {
            new DeleteEmployee();
        });

        JButton updateBtn = new JButton("Update Employee");
        updateBtn.setBounds(155, 286, 180, 40);
        frame.getContentPane().add(updateBtn);
        
        JLabel lblSystem = new JLabel("System");
        lblSystem.setForeground(new Color(128, 128, 255));
        lblSystem.setFont(new Font("Arial", Font.BOLD, 30));
        lblSystem.setBounds(203, 71, 115, 40);
        frame.getContentPane().add(lblSystem);
        //Action
        updateBtn.addActionListener(e -> {
            new UpdateEmployee();
        });

        // Show the frame
        frame.setVisible(true);
    }
}

