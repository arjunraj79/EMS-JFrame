package com.arjun.ems;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class DeleteEmployee extends JFrame {

    private JTextField idField;
    private JPanel resultPanel;
    private JButton confirmDeleteBtn;

    private int currentEmpId = -1; // To store the loaded employee ID

    public DeleteEmployee() {
        setTitle("Delete Employee");
        setSize(500, 400);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("DELETE EMPLOYEE");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(150, 20, 250, 30);
        getContentPane().add(title);

        JLabel idLabel = new JLabel("Enter Employee ID:");
        idLabel.setBounds(50, 70, 150, 25);
        getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 70, 150, 25);
        getContentPane().add(idField);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(370, 70, 80, 25);
        getContentPane().add(searchBtn);

        resultPanel = new JPanel();
        resultPanel.setLayout(null);
        resultPanel.setBounds(50, 110, 400, 150);
        resultPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        getContentPane().add(resultPanel);

        confirmDeleteBtn = new JButton("Confirm Delete");
        confirmDeleteBtn.setBounds(170, 280, 140, 30);
        confirmDeleteBtn.setEnabled(false); // initially disabled
        getContentPane().add(confirmDeleteBtn);
        
        JButton backButton = new JButton("BACK");
        backButton.setBounds(50, 280, 100, 30);
        getContentPane().add(backButton);
        
        backButton.addActionListener(e -> dispose());

        searchBtn.addActionListener(e -> fetchEmployee());
        confirmDeleteBtn.addActionListener(e -> confirmDelete());

        setVisible(true);
    }

    private void fetchEmployee() {
        resultPanel.removeAll();
        currentEmpId = -1;
        confirmDeleteBtn.setEnabled(false);

        String idStr = idField.getText().trim();

        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an Employee ID.");
            return;
        }

        try {
            int empId = Integer.parseInt(idStr);

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_System", "jdbc", "root1234");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE emp_id = ?");
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                currentEmpId = empId;

                JPanel card = new JPanel(new GridLayout(5, 1));
                card.setBounds(10, 10, 380, 120);
                card.setBackground(new Color(255, 228, 225));
                card.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

                Font font = new Font("SansSerif", Font.BOLD, 15);
                JLabel idLabel = new JLabel("ID: " + rs.getInt(1));
                JLabel nameLabel = new JLabel("Name: " + rs.getString(2));
                JLabel salaryLabel = new JLabel("Salary: " + rs.getInt(3));
                JLabel deptLabel = new JLabel("Department: " + rs.getString(4));
                JLabel posLabel = new JLabel("Position: " + rs.getString(5));

                for (JLabel label : new JLabel[]{idLabel, nameLabel, salaryLabel, deptLabel, posLabel}) {
                    label.setFont(font);
                    card.add(label);
                }

                resultPanel.add(card);
                confirmDeleteBtn.setEnabled(true);

            } else {
                JLabel notFound = new JLabel("Employee not found.");
                notFound.setBounds(100, 40, 200, 30);
                notFound.setForeground(Color.RED);
                resultPanel.add(notFound);
            }

            resultPanel.revalidate();
            resultPanel.repaint();
            con.close();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a number.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void confirmDelete() {
        if (currentEmpId == -1) return;

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this employee?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_System", "jdbc", "root1234");
            PreparedStatement ps = con.prepareStatement("DELETE FROM employee WHERE emp_id = ?");
            ps.setInt(1, currentEmpId);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Employee deleted successfully.");
                idField.setText("");
                resultPanel.removeAll();
                resultPanel.repaint();
                confirmDeleteBtn.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found or already deleted.");
            }

            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}
