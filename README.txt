=== InternSync Backend - Setup Instructions ===

STEP 1 - Stop your OLD Spring Boot project in STS
  - In the Boot Dashboard or Project Explorer, right-click the old project → Stop

STEP 2 - Import THIS project into STS
  - File → Import → Maven → Existing Maven Projects
  - Browse to the internsync-backend folder
  - Click Finish
  - Wait for Maven to download dependencies (takes 1-2 minutes)

STEP 3 - Set your MySQL password
  - Open: src/main/resources/application.properties
  - Change line: spring.datasource.password=your_mysql_password
  - Replace "your_mysql_password" with your actual MySQL root password

STEP 4 - Make sure the MySQL database exists
  - Run the internsync_setup.sql file in MySQL Workbench first
  - (from the internsync-mysql.zip you downloaded)

STEP 5 - Run the project
  - Right-click InternsyncApplication.java → Run As → Spring Boot App
  - You should see: "Started InternsyncApplication on port 8080"

STEP 6 - Test it works
  - Open browser: http://localhost:8080/api/internships
  - You should see: []   (empty JSON array, NOT a 404 error)

=== API Endpoints ===
POST   /api/auth/register
POST   /api/auth/login
GET    /api/internships
POST   /api/internships
DELETE /api/internships/{id}
GET    /api/applications
POST   /api/applications
PATCH  /api/applications/{id}/status
GET    /api/tasks
POST   /api/tasks
PATCH  /api/tasks/{id}/status
PATCH  /api/tasks/{id}/report
GET    /api/feedbacks
POST   /api/feedbacks
GET    /api/users
GET    /api/users/students
