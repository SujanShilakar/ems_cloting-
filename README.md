=====================================================================
               EMS CLOTHING STORE – PROJECT DOCUMENTATION
=====================================================================

🧑‍🎓 STUDENT DETAILS
---------------------------------------------------------------------
Name: Roshan Neupane
Student ID: (Add your ID here)
Course: Master of Information Technology
Institute: Victorian Institute of Technology (VIT), Adelaide
Project Title: EMS Clothing Store – E-Commerce Web Application


⚙️ SYSTEM REQUIREMENTS
---------------------------------------------------------------------
• Java Development Kit (JDK) 17 or later
• Apache Tomcat Server 10 or later
• MySQL Database Server 8.0 or later
• IDE: NetBeans or IntelliJ IDEA (Maven-supported)
• Web Browser (Chrome / Edge / Firefox)


📦 DATABASE SETUP
---------------------------------------------------------------------
1. Open MySQL Workbench or phpMyAdmin.
2. Create a new database named `emsdb`.
3. Import the table structure:
   - Use your exported **CREATE TABLE** script from MySQL Workbench.
4. Import the sample data:
   - Run the provided **insert.sql** file to add default data.
5. Verify data:
   - One admin account will be available for login.

   Admin Credentials:
   Email: admin@ems.com
   Password: password


🧩 SERVER CONFIGURATION
---------------------------------------------------------------------
1. Locate the `persistence.xml` file under:
   `src/main/resources/META-INF/persistence.xml`
2. Confirm the following configuration:

   <properties>
       <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
       <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/emsdb"/>
       <property name="jakarta.persistence.jdbc.user" value="root"/>
       <property name="jakarta.persistence.jdbc.password" value="your_mysql_password"/>
       <property name="hibernate.hbm2ddl.auto" value="update"/>
       <property name="hibernate.show_sql" value="true"/>
   </properties>


🚀 RUNNING THE SYSTEM
---------------------------------------------------------------------
1. Open the project in your IDE.
2. Clean and build the project using Maven.
3. Run it on **Apache Tomcat Server 10+**.
4. Access the application in your browser:
   → http://localhost:8080/emsclothing


👨‍💼 ADMIN ACCESS
---------------------------------------------------------------------
Login: admin@ems.com
Password: password
Features:
• Add/Edit/Delete products and categories
• View all customer orders
• Manage stock levels


🛍️ CUSTOMER ACCESS
---------------------------------------------------------------------
• Register a new account
• Add items to cart
• Checkout (orders automatically marked as PAID)
• View and cancel previous orders
• Update personal profile (Name, Address, Phone)


📄 PROJECT SUMMARY
---------------------------------------------------------------------
This web application demonstrates:
• Java Servlet + JSP (MVC) architecture
• Hibernate JPA for database ORM
• Secure login and session management
• File upload (Multipart)
• Product and order management
• Admin and customer roles


✅ END OF FILE
=====================================================================
