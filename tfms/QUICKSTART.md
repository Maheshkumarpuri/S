# TFMS Quick Start Guide

Get the Trade Finance Management System up and running in minutes!

## 🚀 Quick Start (5 minutes)

### Prerequisites
- Java 17 or higher
- Maven 3.6 or higher

### Option 1: Using Run Scripts (Recommended)

#### Linux/Mac:
```bash
./run.sh
```

#### Windows:
```cmd
run.bat
```

### Option 2: Manual Setup

1. **Build and Run**:
```bash
mvn spring-boot:run
```

2. **Access the Application**:
   - Main App: http://localhost:8080
   - H2 Database Console: http://localhost:8080/h2-console

3. **H2 Database Connection**:
   - JDBC URL: `jdbc:h2:mem:tfms`
   - Username: `sa`
   - Password: (leave empty)

## 🎯 What You'll See

### Dashboard (http://localhost:8080)
- Overview cards showing counts of LCs, guarantees, documents, and risk assessments
- Quick action buttons for common tasks
- Recent transactions and compliance alerts

### Sample Data Included
- 2 Letters of Credit (ABC Corp, Global Trade Inc)
- 2 Bank Guarantees (Construction Co, Tech Solutions)
- 2 Trade Documents (Invoice, Bill of Lading)
- 2 Risk Assessments
- 2 Compliance records

## 🧪 Try These Features

### 1. Letters of Credit
- Go to http://localhost:8080/lc
- Click "Create New LC" 
- Fill out the form and submit
- View, edit, or close existing LCs

### 2. Browse Sample Data
- Check the dashboard for overview
- Browse different modules from the navigation
- View detailed information for each item

### 3. Database Exploration
- Access H2 Console: http://localhost:8080/h2-console
- Run SQL queries to explore the data:
  ```sql
  SELECT * FROM letter_of_credit;
  SELECT * FROM bank_guarantee;
  SELECT * FROM trade_document;
  ```

## 🛠️ Advanced Options

### Run with MySQL
1. Set up MySQL database:
   ```sql
   CREATE DATABASE tfms;
   CREATE USER 'tfms'@'localhost' IDENTIFIED BY 'tfms_password';
   GRANT ALL PRIVILEGES ON tfms.* TO 'tfms'@'localhost';
   ```

2. Run with MySQL:
   ```bash
   ./run.sh mysql    # Linux/Mac
   run.bat mysql     # Windows
   ```

### Package for Deployment
```bash
./run.sh package    # Linux/Mac
run.bat package     # Windows
```

Then run:
```bash
java -jar target/tfms-0.0.1-SNAPSHOT.jar
```

## 🆘 Troubleshooting

### Port 8080 in use?
Change port in `src/main/resources/application.properties`:
```properties
server.port=8081
```

### Build issues?
```bash
./run.sh clean build    # Linux/Mac
run.bat clean           # Windows
run.bat build           # Windows
```

### Can't access the app?
- Check if it's running: look for "Started TfmsApplication" in the logs
- Try: http://127.0.0.1:8080 instead of localhost
- Check firewall settings

## 📚 Next Steps

1. **Read the full README.md** for detailed documentation
2. **Explore the codebase** in your IDE
3. **Customize the application** for your needs
4. **Add authentication** for production use
5. **Set up MySQL** for persistent data

## 🎉 You're Ready!

The TFMS application is now running with:
- ✅ Modern Spring MVC architecture
- ✅ Beautiful Bootstrap UI
- ✅ Sample trade finance data
- ✅ Interactive forms and tables
- ✅ Database console access

Happy coding! 🚀