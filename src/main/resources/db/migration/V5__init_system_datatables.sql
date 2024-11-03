CREATE TABLE document_types (
                                id INT PRIMARY KEY IDENTITY,
                                name VARCHAR(255) NOT NULL,
                                created_at DATETIME DEFAULT GETDATE()
);
GO

-- Create roles table before users since it's referenced by users
CREATE TABLE roles (
                       id INT PRIMARY KEY IDENTITY,
                       name VARCHAR(255) NOT NULL,
                       created_at DATETIME DEFAULT GETDATE()
);
GO

-- Create areas table before positions since it's referenced by positions
CREATE TABLE areas (
                       id INT PRIMARY KEY IDENTITY,
                       name VARCHAR(255) NOT NULL,
                       created_at DATETIME DEFAULT GETDATE()
);
GO

-- Create employees table after document_types
CREATE TABLE employees (
                           id INT PRIMARY KEY IDENTITY,
                           first_name VARCHAR(255) NOT NULL,
                           last_name VARCHAR(255) NOT NULL,
                           gender BIT NOT NULL,
                           is_active BIT NOT NULL,
                           hire_date DATE NOT NULL,
                           termination_date DATE,
                           phone VARCHAR(255),
                           address TEXT,
                           document_number VARCHAR(255),
                           document_type_id INT NOT NULL,
                           created_at DATETIME DEFAULT GETDATE(),
                           FOREIGN KEY (document_type_id) REFERENCES document_types(id)
);
GO

-- Create users table after roles
CREATE TABLE users (
                       id INT PRIMARY KEY IDENTITY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       role_id INT NOT NULL,
                       created_at DATETIME DEFAULT GETDATE(),
                       FOREIGN KEY (role_id) REFERENCES roles(id)
);
GO

-- Create positions table after areas
CREATE TABLE positions (
                           id INT PRIMARY KEY IDENTITY,
                           name VARCHAR(255) NOT NULL,
                           area_id INT NOT NULL,
                           created_at DATETIME DEFAULT GETDATE(),
                           updated_at DATETIME,
                           FOREIGN KEY (area_id) REFERENCES areas(id)
);
GO

-- Create salaries table after employees and positions
CREATE TABLE salaries (
                          id INT PRIMARY KEY IDENTITY,
                          employee_id INT NOT NULL,
                          amount DECIMAL(10, 2) NOT NULL,
                          position_id INT NOT NULL,
                          created_at DATETIME DEFAULT GETDATE(),
                          updated_at DATETIME,
                          FOREIGN KEY (employee_id) REFERENCES employees(id),
                          FOREIGN KEY (position_id) REFERENCES positions(id)
);
GO

-- Create settings table
CREATE TABLE settings (
                          id INT PRIMARY KEY IDENTITY,
                          name VARCHAR(255) NOT NULL,
                          value FLOAT(10) NOT NULL,
                          created_at DATETIME DEFAULT GETDATE(),
                          updated_at DATETIME
);
GO

-- Create schedules table after areas
CREATE TABLE schedules (
                           id INT PRIMARY KEY IDENTITY,
                           area_id INT NOT NULL,
                           day_id INT NOT NULL,
                           start_time DATETIME NOT NULL,
                           end_time DATETIME NOT NULL,
                           created_at DATETIME DEFAULT GETDATE(),
                           FOREIGN KEY (area_id) REFERENCES areas(id)
);
GO

-- Create attendance_records table after employees
CREATE TABLE attendance_records (
                                    id INT PRIMARY KEY IDENTITY,
                                    employee_id INT NOT NULL,
                                    day_id INT NOT NULL,
                                    created_at DATETIME DEFAULT GETDATE(),
                                    FOREIGN KEY (employee_id) REFERENCES employees(id)
);
GO

-- Create monthly_hours table after employees
CREATE TABLE monthly_hours (
                               id INT PRIMARY KEY IDENTITY,
                               employee_id INT NOT NULL,
                               expected_hours INT NOT NULL,
                               worked_hours INT NOT NULL,
                               created_at DATETIME DEFAULT GETDATE(),
                               FOREIGN KEY (employee_id) REFERENCES employees(id)
);
GO

-- Create bank_accounts table after employees
CREATE TABLE bank_accounts (
                               id INT PRIMARY KEY IDENTITY,
                               employee_id INT NOT NULL,
                               account_number VARCHAR(255) NOT NULL,
                               interbank_code VARCHAR(255),
                               is_active BIT NOT NULL,
                               created_at DATETIME DEFAULT GETDATE(),
                               FOREIGN KEY (employee_id) REFERENCES employees(id)
);
GO