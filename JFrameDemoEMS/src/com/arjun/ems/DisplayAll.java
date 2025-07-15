package com.arjun.ems;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class DisplayAll extends JFrame {

    public DisplayAll() {
        setTitle("List of All Employees");
        setSize(600, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel title = new JLabel("LIST OF ALL EMPLOYEES");
        title.setFont(new Font("Monospaced", Font.BOLD, 18));
        title.setBounds(170, 10, 300, 30);
        add(title);

        String[] columns = {"id", "name", "Salary", "Department", "Position"};

        String[][] data = getEmployeeData();

        JTable table = new JTable(data, columns);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 60, 480, 200);
        add(scrollPane);

        JButton backButton = new JButton("BACK");
        backButton.setBounds(240, 280, 100, 30);
        add(backButton);

        backButton.addActionListener(e -> dispose());

        setVisible(true);
    }

    // JDBC method to get data from MySQL
    private String[][] getEmployeeData() {
        String[][] data = new String[10][5]; 
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMS_System", "jdbc", "root1234");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            int row = 0;
            while (rs.next()) {
                data[row][0] = String.valueOf(rs.getInt(1));
                data[row][1] = rs.getString(2);
                data[row][2] = String.valueOf(rs.getInt(3));
                data[row][3] = rs.getString(4);
                data[row][4] = rs.getString(5);
                row++;
            }
            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage());
        }
        return data;
    }
}
