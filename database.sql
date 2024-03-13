create database employee_managament;

use employee_managament;

CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    designation VARCHAR(100) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
); 

select * from employee_management.employees;