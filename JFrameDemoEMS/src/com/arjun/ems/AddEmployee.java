package com.arjun.ems;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class AddEmployee extends JFrame {

    private JTextField nameField, salaryField, deptField, positionField;

    public AddEmployee() {
        setTitle("Add New Employee");
        setSize(400, 350);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("ADD NEW EMPLOYEE");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(100, 20, 250, 30);
        getContentPane().add(title);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 70, 100, 25);
        getContentPane().add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(150, 70, 180, 25);
        getContentPane().add(nameField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(50, 110, 100, 25);
        getContentPane().add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(150, 110, 180, 25);
        getContentPane().add(salaryField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(50, 150, 100, 25);
        getContentPane().add(deptLabel);

        deptField = new JTextField();
        deptField.setBounds(150, 150, 180, 25);
        getContentPane().add(deptField);

        JLabel posLabel = new JLabel("Position:");
        posLabel.setBounds(50, 190, 100, 25);
        getContentPane().add(posLabel);

        positionField = new JTextField();
        positionField.setBounds(150, 190, 180, 25);
        getContentPane().add(positionField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(150, 240, 100, 30);
        getContentPane().add(addBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(276, 240, 100, 30);
        getContentPane().add(cancelBtn);
        
        JButton backButton = new JButton("BACK");
        backButton.setBounds(20, 240, 100, 30);
        getContentPane().add(backButton);
        
        backButton.addActionListener(e -> dispose());
        addBtn.addActionListener(e -> addEmployee());
        cancelBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void addEmployee() {
        String name = nameField.getText().trim();
        String salaryStr = salaryField.getText().trim();
        String department = deptField.getText().trim();
        String position = positionField.getText().trim();

        if (name.isEmpty() || salaryStr.isEmpty() || department.isEmpty() || position.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            int salary = Integer.parseInt(salaryStr);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_System", "jdbc", "root1234");
            PreparedStatement ps = con.prepareStatement("INSERT INTO employee (name, salary, dept_name, desgination) VALUES (?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setInt(2, salary);
            ps.setString(3, department);
            ps.setString(4, position);
            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Employee added successfully.");
                nameField.setText("");
                salaryField.setText("");
                deptField.setText("");
                positionField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add employee.");
            }

            con.close();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salary must be a number.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}

