Task 2: Web App / Traditional MVC Servlets and JSPs Implementation
Created a login page that checks user credentials against a MySQL database and redirects users based on their roles (student, instructor, admin),
Tables:
CREATE TABLE `user` (
   `id` INT(11) AUTO_INCREMENT PRIMARY KEY,
   `first_name` varchar(20) DEFAULT NULL,
   `last_name` varchar(20) DEFAULT NULL,
   `username` varchar(250) DEFAULT NULL,
   `password` varchar(20) DEFAULT NULL,
   `role` varchar(20) DEFAULT NULL
  

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `grades` (
   `id` INT(11) AUTO_INCREMENT PRIMARY KEY,
   `student_id` INT(11),
   `subject` VARCHAR(50),
   `grade` VARCHAR(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `grades` (
   `id` INT(11) AUTO_INCREMENT PRIMARY KEY,
   `student_id` INT(11),
   `subject` VARCHAR(50) NOT NULL,
   `grade` VARCHAR(10) NOT NULL,
   FOREIGN KEY (`student_id`) REFERENCES `user`(`id`)
);

Based on role , user will be redirected to other pages , if instructor , he will be able to view and edit grades , and for student , he will be able to only view their grades 
  ![Screenshot 2024-03-03 040045](https://github.com/amanhamdan/part2-SGS/assets/101350478/f653a6c0-703a-41df-a83b-0f0868339f56)

EditGradeServlet:
This servlet handles the logic for editing grades. It retrieves form data (studentId, subject, newGrade) from the request parameters and redirects to the admin home page (adminHome.jsp). 

LoginServlet:
This servlet handles user login functionality. It retrieves the username and password from the request parameters, validates the user using the UserDao, and sets session attributes for the username and role. Depending on the user's role, it redirects to different pages (viewGrades for students and adminHome.jsp for admins).


UserServlet:
This servlet handles user registration. It retrieves user data from the request parameters, creates a User object, and registers the user using the UserDao. After registration, it forwards the request to the userdetails.jsp page.

ViewGradesServlet:
This servlet retrieves and displays grades for students. It retrieves the username from the session, retrieves the user ID using UserDao, retrieves grades for the user ID using UserDao, and forwards the grades to the studentGrades.jsp page for display.

UserDao:
This class handles database operations related to users and grades. It provides methods for getting grades by user ID, getting user ID by username, updating grades, validating users, getting user roles, and registering users.

