package com.arjun.ems;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class UpdateEmployee extends JFrame {

    private JTextField idField, nameField, salaryField, deptField, positionField;
    private JButton fetchBtn, updateBtn;
    private JPanel formPanel;
    private JButton backButton;

    public UpdateEmployee() {
        setTitle("Update Employee");
        setSize(500, 450);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("UPDATE EMPLOYEE");
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBounds(150, 20, 250, 30);
        getContentPane().add(title);

        JLabel idLabel = new JLabel("Enter Employee ID:");
        idLabel.setBounds(50, 70, 150, 25);
        getContentPane().add(idLabel);

        idField = new JTextField();
        idField.setBounds(200, 70, 150, 25);
        getContentPane().add(idField);

        fetchBtn = new JButton("Fetch");
        fetchBtn.setBounds(370, 70, 80, 25);
        getContentPane().add(fetchBtn);

        formPanel = new JPanel();
        formPanel.setLayout(null);
        formPanel.setBounds(50, 120, 400, 220);
        formPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));
        formPanel.setVisible(false); // initially hidden
        getContentPane().add(formPanel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 30, 100, 25);
        formPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(130, 30, 220, 25);
        formPanel.add(nameField);

        JLabel salaryLabel = new JLabel("Salary:");
        salaryLabel.setBounds(20, 70, 100, 25);
        formPanel.add(salaryLabel);

        salaryField = new JTextField();
        salaryField.setBounds(130, 70, 220, 25);
        formPanel.add(salaryField);

        JLabel deptLabel = new JLabel("Department:");
        deptLabel.setBounds(20, 110, 100, 25);
        formPanel.add(deptLabel);

        deptField = new JTextField();
        deptField.setBounds(130, 110, 220, 25);
        formPanel.add(deptField);

        JLabel posLabel = new JLabel("Position:");
        posLabel.setBounds(20, 150, 100, 25);
        formPanel.add(posLabel);

        positionField = new JTextField();
        positionField.setBounds(130, 150, 220, 25);
        formPanel.add(positionField);

        updateBtn = new JButton("Update");
        updateBtn.setBounds(180, 350, 120, 30);
        updateBtn.setEnabled(false);
        getContentPane().add(updateBtn);
        
        backButton = new JButton("BACK");
        backButton.setBounds(50, 351, 100, 30);
        getContentPane().add(backButton);

        fetchBtn.addActionListener(e -> fetchEmployee());
        updateBtn.addActionListener(e -> updateEmployee());

        setVisible(true);
    }

    private void fetchEmployee() {
        String idStr = idField.getText().trim();
        if (idStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employee ID.");
            return;
        }

        try {
            int empId = Integer.parseInt(idStr);
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_System", "jdbc", "root1234");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM employee WHERE emp_id = ?");
            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                nameField.setText(rs.getString(2));
                salaryField.setText(String.valueOf(rs.getInt(3)));
                deptField.setText(rs.getString(4));
                positionField.setText(rs.getString(5));

                formPanel.setVisible(true);
                updateBtn.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(this, "Employee not found.");
                formPanel.setVisible(false);
                updateBtn.setEnabled(false);
            }

            con.close();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID must be a number.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void updateEmployee() {
        String idStr = idField.getText().trim();
        String name = nameField.getText().trim();
        String salaryStr = salaryField.getText().trim();
        String department = deptField.getText().trim();
        String position = positionField.getText().trim();

        if (name.isEmpty() || salaryStr.isEmpty() || department.isEmpty() || position.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.");
            return;
        }

        try {
            int empId = Integer.parseInt(idStr);
            int salary = Integer.parseInt(salaryStr);

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_System", "jdbc", "root1234");
            PreparedStatement ps = con.prepareStatement(
                "UPDATE employee SET name = ?, salary = ?, dept_name = ?, desgination = ? WHERE emp_id = ?"
            );
            ps.setString(1, name);
            ps.setInt(2, salary);
            ps.setString(3, department);
            ps.setString(4, position);
            ps.setInt(5, empId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                JOptionPane.showMessageDialog(this, "Employee updated successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "Update failed.");
            }

            con.close();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Salary must be a number.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
}

