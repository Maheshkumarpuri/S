# Trade Finance Management System (TFMS)

A comprehensive web application designed for banks and financial institutions to manage trade finance operations. The system provides functionalities for handling letters of credit, guarantees, and trade documentation while ensuring compliance with international trade regulations.

## 🏗️ Architecture

Built using **Spring MVC architecture** with the following technology stack:

- **Backend**: Spring Boot 3.3.2, Spring MVC, Spring Data JPA
- **Frontend**: Thymeleaf, Bootstrap 5.3.2, JavaScript
- **Database**: H2 (development), MySQL (production)
- **Build Tool**: Maven
- **Java Version**: 17

## 🚀 Features

### Core Modules

1. **Letter of Credit Management**
   - Issue, amend, and close letters of credit
   - Track LC lifecycle and status
   - Automated compliance checks

2. **Bank Guarantee Management**
   - Request and issue bank guarantees
   - Monitor guarantee validity periods
   - Status tracking and notifications

3. **Trade Documentation**
   - Upload and manage trade documents
   - Document categorization and search
   - File storage and retrieval

4. **Risk Assessment**
   - Automated risk scoring algorithms
   - Transaction risk analysis
   - Risk factor evaluation

5. **Compliance and Regulatory Reporting**
   - Compliance status monitoring
   - Regulatory report generation
   - Non-compliance alerts

### Key Features

- **Dashboard**: Real-time overview of all trade finance activities
- **Modern UI**: Responsive design with Bootstrap 5
- **Data Tables**: Sortable and searchable data presentation
- **Form Validation**: Client-side and server-side validation
- **File Upload**: Drag-and-drop file upload functionality
- **Status Tracking**: Visual status indicators and timeline views

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- MySQL 8.0 (for production) - optional for development

## 🛠️ Installation & Setup

### 1. Clone the Repository

```bash
git clone <repository-url>
cd tfms
```

### 2. Database Setup

#### For Development (H2 Database)
The application comes pre-configured with H2 in-memory database. No additional setup required.

#### For Production (MySQL)
1. Create a MySQL database:
```sql
CREATE DATABASE tfms;
CREATE USER 'tfms'@'localhost' IDENTIFIED BY 'tfms_password';
GRANT ALL PRIVILEGES ON tfms.* TO 'tfms'@'localhost';
FLUSH PRIVILEGES;
```

2. Update application properties:
```bash
# Copy MySQL configuration
cp src/main/resources/application-mysql.properties src/main/resources/application.properties
```

### 3. Build the Application

```bash
mvn clean compile
```

### 4. Run the Application

```bash
mvn spring-boot:run
```

The application will be available at: http://localhost:8080

## 🖥️ Usage

### Accessing the Application

1. **Dashboard**: http://localhost:8080
   - Overview of all trade finance activities
   - Quick action buttons
   - Recent transactions and alerts

2. **Letters of Credit**: http://localhost:8080/lc
   - Create, view, edit, and close LCs
   - Search and filter functionality

3. **H2 Console** (Development): http://localhost:8080/h2-console
   - JDBC URL: `jdbc:h2:mem:tfms`
   - Username: `sa`
   - Password: (leave empty)

### Sample Data

The application includes sample data for testing:
- 2 Letters of Credit
- 2 Bank Guarantees  
- 2 Trade Documents
- 2 Risk Assessments
- 2 Compliance records

## 📁 Project Structure

```
tfms/
├── src/main/java/com/example/tfms/
│   ├── controller/          # MVC Controllers
│   ├── service/            # Business Logic Services
│   ├── repository/         # Data Access Layer
│   ├── model/entity/       # JPA Entities
│   ├── exception/          # Custom Exceptions
│   └── TfmsApplication.java # Main Application Class
├── src/main/resources/
│   ├── templates/          # Thymeleaf Templates
│   ├── static/            # CSS, JS, Images
│   ├── schema.sql         # Database Schema
│   └── application.properties # Configuration
└── pom.xml                # Maven Dependencies
```

## 🔧 Configuration

### Application Properties

```properties
# Server Configuration
server.port=8080

# Database Configuration (H2 - Development)
spring.datasource.url=jdbc:h2:mem:tfms
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# File Storage
tfms.storage.location=uploads
```

### MySQL Configuration

For production, use the MySQL configuration in `application-mysql.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tfms
spring.datasource.username=tfms
spring.datasource.password=tfms_password
```

## 🚀 Deployment

### Local Deployment

1. **Package the application**:
```bash
mvn clean package
```

2. **Run the JAR file**:
```bash
java -jar target/tfms-0.0.1-SNAPSHOT.jar
```

### Production Deployment

1. **Set up MySQL database** (see Database Setup section)

2. **Update configuration**:
```bash
# Use production profile
java -jar target/tfms-0.0.1-SNAPSHOT.jar --spring.profiles.active=mysql
```

3. **Environment Variables** (optional):
```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/tfms
export SPRING_DATASOURCE_USERNAME=tfms
export SPRING_DATASOURCE_PASSWORD=your_secure_password
```

## 🧪 Testing

### Run Tests
```bash
mvn test
```

### Manual Testing
1. Access the application at http://localhost:8080
2. Navigate through different modules
3. Test CRUD operations for Letters of Credit
4. Verify form validations and error handling

## 📊 Database Schema

The application uses the following main tables:

- `letter_of_credit` - Stores LC information
- `bank_guarantee` - Bank guarantee records  
- `trade_document` - Document metadata
- `risk_assessment` - Risk analysis data
- `compliance` - Compliance status records

## 🎨 UI Components

### Key UI Features

- **Responsive Design**: Works on desktop, tablet, and mobile
- **Bootstrap 5**: Modern, clean interface
- **Interactive Tables**: Sorting, searching, pagination
- **Form Validation**: Real-time validation feedback
- **Status Badges**: Visual status indicators
- **Modal Dialogs**: Confirmation dialogs for critical actions

### Custom Styling

The application includes custom CSS for:
- Dashboard cards with gradients
- Status-specific badge colors
- Timeline components
- File upload areas
- Enhanced form controls

## 🔒 Security Considerations

For production deployment, consider implementing:

- Authentication and authorization
- HTTPS/SSL encryption
- Input validation and sanitization
- SQL injection protection (already handled by JPA)
- Cross-Site Request Forgery (CSRF) protection
- Session management
- Audit logging

## 📈 Future Enhancements

Potential improvements for the system:

- User authentication and role-based access control
- Email notifications and alerts
- Document workflow and approval processes
- Integration with external banking systems
- Advanced reporting and analytics
- Mobile application
- Multi-language support
- API endpoints for third-party integrations

## 🐛 Troubleshooting

### Common Issues

1. **Port Already in Use**:
   - Change port in `application.properties`: `server.port=8081`

2. **Database Connection Issues**:
   - Verify MySQL is running
   - Check database credentials
   - Ensure database exists

3. **File Upload Issues**:
   - Check `tfms.storage.location` directory permissions
   - Verify disk space availability

4. **Build Issues**:
   - Ensure Java 17 is installed
   - Update Maven to latest version
   - Clear Maven cache: `mvn clean`

## 📞 Support

For technical support or questions:

1. Check the troubleshooting section above
2. Review application logs for error details
3. Verify configuration settings
4. Test with sample data provided

## 📄 License

This project is developed for educational and demonstration purposes. 

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- Bootstrap team for the UI components
- Thymeleaf team for the template engine
- All contributors to the open-source libraries used

---

**Version**: 1.0.0  
**Last Updated**: 2024  
**Compatibility**: Spring Boot 3.3.2, Java 17+