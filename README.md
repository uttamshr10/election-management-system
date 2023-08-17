# Election Management System (EMS)
EMS is a comprehensive software solution developed using Java Swing. Its primary objective is to streamline the electoral process, ensuring transparency, efficiency and accuracy in managing elections. 
The EMS provides different forms for administrators, voters and winner declaration.

## Prerequisites
- Eclipse IDE for Java Developers
- Java Development Kit (JDK) - Version 19.0 or higher
- SQL Server Management Studio (SSMS) - Version 19.0 or higher
- JDBC Driver for SQL Server

## Installation

- Open Eclipse, Go to File > Import.
- Choose Git > Projects from Git and click Next.
- Select Clone URI and click Next.
- Paste below repository URL in URI Field and Click Next.
```
https://git@github.com/uttamshr10/election-management-system.git
```
- Continue following the wizard, selecting the desired branch and destination folder within the workspace.
- Select `Import using the New Project Wizard` and click Finish.
- This will clone EMS repo in your root directory (C drive).
- Again, Go to File > Import.
- Choose General > Existing Projects into Workspace and click Next.
- Select the root directory of your cloned repository.
- Make sure the project you want to import is selected.
- Click Finish to import the project.
- Now you can run `HomePage.java` file from `src` folder which will run the application.

## Configuration

1. **Database Setup:**

   - Open Microsoft SQL Server Management Studio (SSMS).
   - Select the correct Server name which is essential for establishing connection between Eclipse and SSMS.
   - Create a new database named `Election_db`.

2. **JDBC Configuration:**

   - Open the project in Eclipse IDE.
   - Locate the `Database.java` file in the `src` folder.
   - Replace with your SQL Server database detail.
   ```
   String server = "//your-sql-server-name\\SQLEXPRESS";
   ```
   - Run `Database.java` to establish connection between SSMS and Eclipse.

3. ** Table Setup: **

  - After configuration of Database and JDBC, run `CreateTable.java` which will create four tables required for our system.
  - For Admin authentication and authorization, locate the `InsertData.java` file.
  - Replace the username and password with your desire credential. Remember this will be the credential to access Administrator's Authenticated Form. You can create as many admin credentials as you want.
  - Run `InsertData.java` file.

## Usage

1. Compile and run the `HomePage.java` file.

2. The main interface will open, allowing you to access the admin, voter and winner functionalities.

3. Admins can manage candidates and users, while voters can cast their votes based on the available options.

4. The Winner Form will display the winner once all registered citizens have voted.

5. Duplicate votes are filtered out earlier by not letting the user vote twice.


# Authors
## Uttam Shrestha
[![LinkedIn](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/uttam-shrestha-b96032224/)
 
[![GitHub](https://img.shields.io/badge/GitHub-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/uttamshr10)


## Sumit Singh Thakuri
[![GitHub](https://img.shields.io/badge/GitHub-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)](https://github.com/sumitsinghthakuri)




# Contributing

Contributions are welcome! If you find any bugs or have any suggestions for improvements, please create an issue or a pull request.

# Contact

If you have any questions or feedback, feel free to contact me at utam.shrestha65@gmail.com.
