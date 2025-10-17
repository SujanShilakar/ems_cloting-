# EMS Clothing - Base Java Web App

Stack: Maven WAR, Servlets/JSP, JPA (Hibernate), MySQL, Jakarta EE 10.

## Configure DB
Edit `src/main/resources/META-INF/persistence.xml` and set your MySQL password/user.
Create DB:
```sql
CREATE DATABASE ems_clothing CHARACTER SET utf8mb4;
```

## Run
- Deploy on Tomcat 10+ from NetBeans.
- Visit `http://localhost:8080/ems_clothing/` then click **View Products**.

## Push
```
git init
git add .
git commit -m "Base project"
git branch -M main
git remote add origin https://github.com/<org>/ems_cloting-.git
git push -u origin main
```
