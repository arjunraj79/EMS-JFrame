package com.arjun.ems;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class ViewEmployee extends JFrame {

    private JTextField idField;
    private JPanel resultPanel;

    public ViewEmployee() {
        setTitle("View Employee");
        setSize(500, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("Enter Employee ID:");
        title.setBounds(50, 30, 150, 25);
        add(title);

        idField = new JTextField();
        idField.setBounds(200, 30, 150, 25);
        add(idField);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(100, 70, 100, 30);
        add(searchBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(220, 70, 100, 30);
        add(backBtn);

        // Panel to show result
        resultPanel = new JPanel();
        resultPanel.setBounds(50, 120, 380, 180);
        resultPanel.setLayout(null);
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        add(resultPanel);

        searchBtn.addActionListener(e -> searchEmployee());
        backBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void searchEmployee() {
        resultPanel.removeAll();
        String empId = idField.getText().trim();

        if (empId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
            return;
        }

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_System", "jdbc", "root1234");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE emp_id = ?");
            ps.setInt(1, Integer.parseInt(empId));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Color cardColor = (rs.getInt(1) % 2 == 0) ? new Color(173, 216, 230) : new Color(255, 182, 193); 

                JPanel card = new JPanel();
                card.setLayout(new GridLayout(5, 1, 10, 5));
                card.setBounds(10, 20, 360, 140);
                card.setBackground(cardColor);
                card.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 	

                card.add(new JLabel("ID: " + rs.getInt(1)));
                card.add(new JLabel("Name: " + rs.getString(2)));
                card.add(new JLabel("Salary: " + rs.getInt(3)));
                card.add(new JLabel("Dept: " + rs.getString(4)));
                card.add(new JLabel("Pos: " + rs.getString(5)));

                resultPanel.add(card);
            } else {
                JLabel notFound = new JLabel("Employee not found.");
                notFound.setBounds(100, 60, 200, 30);
                notFound.setForeground(Color.RED);
                resultPanel.add(notFound);
            }

            resultPanel.revalidate();
            resultPanel.repaint();
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
