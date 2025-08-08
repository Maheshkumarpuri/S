#!/bin/bash

# Trade Finance Management System (TFMS) - Run Script
# This script helps you run the TFMS application in different modes

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Function to print colored output
print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to check prerequisites
check_prerequisites() {
    print_info "Checking prerequisites..."
    
    # Check Java
    if ! command -v java &> /dev/null; then
        print_error "Java is not installed. Please install Java 17 or higher."
        exit 1
    fi
    
    # Check Java version
    JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | cut -d'.' -f1)
    if [ "$JAVA_VERSION" -lt 17 ]; then
        print_error "Java 17 or higher is required. Current version: $JAVA_VERSION"
        exit 1
    fi
    
    # Check Maven
    if ! command -v mvn &> /dev/null; then
        print_error "Maven is not installed. Please install Maven 3.6 or higher."
        exit 1
    fi
    
    print_success "Prerequisites check passed!"
}

# Function to build the application
build_app() {
    print_info "Building the application..."
    mvn clean compile
    print_success "Application built successfully!"
}

# Function to run with H2 database
run_h2() {
    print_info "Starting TFMS with H2 database..."
    print_info "Application will be available at: http://localhost:8080"
    print_info "H2 Console will be available at: http://localhost:8080/h2-console"
    print_info "H2 Connection details:"
    print_info "  JDBC URL: jdbc:h2:mem:tfms"
    print_info "  Username: sa"
    print_info "  Password: (leave empty)"
    echo ""
    mvn spring-boot:run
}

# Function to run with MySQL database
run_mysql() {
    print_info "Starting TFMS with MySQL database..."
    
    # Check if MySQL configuration exists
    if [ ! -f "src/main/resources/application-mysql.properties" ]; then
        print_error "MySQL configuration file not found!"
        exit 1
    fi
    
    print_warning "Make sure MySQL is running and database 'tfms' exists!"
    print_info "Application will be available at: http://localhost:8080"
    echo ""
    
    # Run with MySQL profile
    SPRING_CONFIG_NAME=application-mysql mvn spring-boot:run
}

# Function to package the application
package_app() {
    print_info "Packaging the application..."
    mvn clean package
    
    if [ -f "target/tfms-0.0.1-SNAPSHOT.jar" ]; then
        print_success "Application packaged successfully!"
        print_info "JAR file location: target/tfms-0.0.1-SNAPSHOT.jar"
        print_info "To run the JAR: java -jar target/tfms-0.0.1-SNAPSHOT.jar"
    else
        print_error "Packaging failed!"
        exit 1
    fi
}

# Function to run tests
run_tests() {
    print_info "Running tests..."
    mvn test
    print_success "Tests completed!"
}

# Function to clean the project
clean_project() {
    print_info "Cleaning the project..."
    mvn clean
    print_success "Project cleaned successfully!"
}

# Function to show help
show_help() {
    echo "Trade Finance Management System (TFMS) - Run Script"
    echo ""
    echo "Usage: $0 [COMMAND]"
    echo ""
    echo "Commands:"
    echo "  start, run       Start the application with H2 database (default)"
    echo "  mysql           Start the application with MySQL database"
    echo "  build           Build the application"
    echo "  package         Package the application into JAR file"
    echo "  test            Run tests"
    echo "  clean           Clean the project"
    echo "  check           Check prerequisites"
    echo "  help, -h        Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0                # Start with H2 database"
    echo "  $0 start          # Start with H2 database"
    echo "  $0 mysql          # Start with MySQL database"
    echo "  $0 package        # Package into JAR file"
    echo ""
    echo "For more information, see README.md"
}

# Main script logic
case "${1:-start}" in
    "start"|"run"|"")
        check_prerequisites
        build_app
        run_h2
        ;;
    "mysql")
        check_prerequisites
        build_app
        run_mysql
        ;;
    "build")
        check_prerequisites
        build_app
        ;;
    "package")
        check_prerequisites
        package_app
        ;;
    "test")
        check_prerequisites
        run_tests
        ;;
    "clean")
        clean_project
        ;;
    "check")
        check_prerequisites
        ;;
    "help"|"-h"|"--help")
        show_help
        ;;
    *)
        print_error "Unknown command: $1"
        echo ""
        show_help
        exit 1
        ;;
esac