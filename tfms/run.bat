@echo off
REM Trade Finance Management System (TFMS) - Windows Run Script
REM This script helps you run the TFMS application in different modes

setlocal enabledelayedexpansion

REM Set colors (if supported)
set "INFO=[INFO]"
set "SUCCESS=[SUCCESS]"
set "WARNING=[WARNING]"
set "ERROR=[ERROR]"

REM Function to check prerequisites
:check_prerequisites
echo %INFO% Checking prerequisites...

REM Check Java
java -version >nul 2>&1
if errorlevel 1 (
    echo %ERROR% Java is not installed. Please install Java 17 or higher.
    exit /b 1
)

REM Check Maven
mvn -version >nul 2>&1
if errorlevel 1 (
    echo %ERROR% Maven is not installed. Please install Maven 3.6 or higher.
    exit /b 1
)

echo %SUCCESS% Prerequisites check passed!
goto :eof

REM Function to build the application
:build_app
echo %INFO% Building the application...
call mvn clean compile
if errorlevel 1 (
    echo %ERROR% Build failed!
    exit /b 1
)
echo %SUCCESS% Application built successfully!
goto :eof

REM Function to run with H2 database
:run_h2
echo %INFO% Starting TFMS with H2 database...
echo %INFO% Application will be available at: http://localhost:8080
echo %INFO% H2 Console will be available at: http://localhost:8080/h2-console
echo %INFO% H2 Connection details:
echo %INFO%   JDBC URL: jdbc:h2:mem:tfms
echo %INFO%   Username: sa
echo %INFO%   Password: (leave empty)
echo.
call mvn spring-boot:run
goto :eof

REM Function to run with MySQL database
:run_mysql
echo %INFO% Starting TFMS with MySQL database...

REM Check if MySQL configuration exists
if not exist "src\main\resources\application-mysql.properties" (
    echo %ERROR% MySQL configuration file not found!
    exit /b 1
)

echo %WARNING% Make sure MySQL is running and database 'tfms' exists!
echo %INFO% Application will be available at: http://localhost:8080
echo.

REM Run with MySQL profile
set SPRING_CONFIG_NAME=application-mysql
call mvn spring-boot:run
goto :eof

REM Function to package the application
:package_app
echo %INFO% Packaging the application...
call mvn clean package
if errorlevel 1 (
    echo %ERROR% Packaging failed!
    exit /b 1
)

if exist "target\tfms-0.0.1-SNAPSHOT.jar" (
    echo %SUCCESS% Application packaged successfully!
    echo %INFO% JAR file location: target\tfms-0.0.1-SNAPSHOT.jar
    echo %INFO% To run the JAR: java -jar target\tfms-0.0.1-SNAPSHOT.jar
) else (
    echo %ERROR% JAR file not found after packaging!
    exit /b 1
)
goto :eof

REM Function to run tests
:run_tests
echo %INFO% Running tests...
call mvn test
if errorlevel 1 (
    echo %ERROR% Tests failed!
    exit /b 1
)
echo %SUCCESS% Tests completed!
goto :eof

REM Function to clean the project
:clean_project
echo %INFO% Cleaning the project...
call mvn clean
if errorlevel 1 (
    echo %ERROR% Clean failed!
    exit /b 1
)
echo %SUCCESS% Project cleaned successfully!
goto :eof

REM Function to show help
:show_help
echo Trade Finance Management System (TFMS) - Windows Run Script
echo.
echo Usage: %~nx0 [COMMAND]
echo.
echo Commands:
echo   start, run       Start the application with H2 database (default)
echo   mysql           Start the application with MySQL database
echo   build           Build the application
echo   package         Package the application into JAR file
echo   test            Run tests
echo   clean           Clean the project
echo   check           Check prerequisites
echo   help, /?        Show this help message
echo.
echo Examples:
echo   %~nx0                # Start with H2 database
echo   %~nx0 start          # Start with H2 database
echo   %~nx0 mysql          # Start with MySQL database
echo   %~nx0 package        # Package into JAR file
echo.
echo For more information, see README.md
goto :eof

REM Main script logic
set "command=%~1"
if "%command%"=="" set "command=start"

if /i "%command%"=="start" goto start_h2
if /i "%command%"=="run" goto start_h2
if /i "%command%"=="mysql" goto start_mysql
if /i "%command%"=="build" goto build_only
if /i "%command%"=="package" goto package_only
if /i "%command%"=="test" goto test_only
if /i "%command%"=="clean" goto clean_only
if /i "%command%"=="check" goto check_only
if /i "%command%"=="help" goto show_help
if /i "%command%"=="/?" goto show_help

echo %ERROR% Unknown command: %command%
echo.
goto show_help

:start_h2
call :check_prerequisites
if errorlevel 1 exit /b 1
call :build_app
if errorlevel 1 exit /b 1
call :run_h2
goto :eof

:start_mysql
call :check_prerequisites
if errorlevel 1 exit /b 1
call :build_app
if errorlevel 1 exit /b 1
call :run_mysql
goto :eof

:build_only
call :check_prerequisites
if errorlevel 1 exit /b 1
call :build_app
goto :eof

:package_only
call :check_prerequisites
if errorlevel 1 exit /b 1
call :package_app
goto :eof

:test_only
call :check_prerequisites
if errorlevel 1 exit /b 1
call :run_tests
goto :eof

:clean_only
call :clean_project
goto :eof

:check_only
call :check_prerequisites
goto :eof