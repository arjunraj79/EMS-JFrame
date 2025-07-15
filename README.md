# Employee Management System (EMS)

A Java Swing-based desktop application for managing employees. This project provides a graphical user interface (GUI) for performing basic CRUD operations on employee data, backed by a MySQL database using JDBC.
    <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/ems_main.png" width="500"> </p>

---

## 🚀 Features

- ✅ View All Employees
  
  <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/displayAll.png" width="500"> </p>
  
- 🔍 Search Employee by ID

    <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/viewEmp.png" width="500"> </p>
  
- ➕ Add New Employee (Auto-increment ID)

    <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/addEmp.png" width="500"> </p>

    <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/addEmp2.png" width="500"> </p>


- 📝 Update Existing Employee

  <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/updateEmp.png" width="500"> </p>

- ❌ Delete Employee with Confirmation

  <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/deleteEmp.png" width="500"> </p>

---

## 💻 Technologies Used

- Java (Swing)
- MySQL
- JDBC
- Eclipse IDE

---

## 🛠️ Setup Instructions

1. **Clone the repo**
   ```
   git clone https://github.com/your-username/JFrame-EMS.git
   cd JFrame-EMS
   ```
2. **Import into Eclipse**
```
File > Import > Existing Projects into Workspace
```

3. Configure MySQL Database

     <br> <p align="center"> <img src="https://github.com/arjunraj79/EMS-JFrame/blob/main/JFrameDemoEMS/src/com/arjun/ems/resources/mysqlDB.png" width="500"> </p>

```
Create a database:

CREATE DATABASE ems;
```

Create the employees table:

```
CREATE TABLE employees (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  salary INT,
  department VARCHAR(50),
  position VARCHAR(50)
);
```

4. **Add MySQL JDBC Driver**

```
Download from: https://dev.mysql.com/downloads/connector/j/

Add mysql-connector-java-X.X.X.jar to your project’s build path
```

5. **Update DB Credentials**

Inside DBConnection.java or wherever you're connecting:
```
String url = "jdbc:mysql://localhost:3306/ems";
String user = "your_username";
String password = "your_password";
```


📌 Author
Arjun – @arjunraj79
