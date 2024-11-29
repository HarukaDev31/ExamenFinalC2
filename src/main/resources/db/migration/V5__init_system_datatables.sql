
/*CREATE TABLE document_types (
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
  INSERT INTO positions (name, area_id)
VALUES
    -- Human Resources
    ('Recruiter', 1),
    ('HR Generalist', 1),
    ('Training Specialist', 1),

    -- Finance
    ('Financial Analyst', 2),
    ('Accountant', 2),
    ('Auditor', 2),

    -- Marketing
    ('Marketing Manager', 3),
    ('SEO Specialist', 3),
    ('Brand Strategist', 3),

    -- Sales
    ('Sales Executive', 4),
    ('Account Manager', 4),
    ('Business Development Manager', 4),

    -- Operations
    ('Operations Manager', 5),
    ('Logistics Coordinator', 5),
    ('Quality Assurance Specialist', 5),

    -- Customer Service
    ('Customer Support Representative', 6),
    ('Call Center Supervisor', 6),
    ('Customer Success Manager', 6),

    -- Research and Development
    ('R&D Scientist', 7),
    ('Product Developer', 7),
    ('Research Analyst', 7),

    -- IT/Technology
    ('Software Engineer', 8),
    ('Software Engineer Mid',8),
    ('Software Engineer Senior',8),
    ('Tech Lead',8)
    ('System Administrator', 8),
    ('Cybersecurity Specialist', 8),

    -- Legal
    ('Legal Advisor', 9),
    ('Compliance Officer', 9),
    ('Paralegal', 9),

    -- Supply Chain
    ('Supply Chain Manager', 10),
    ('Inventory Analyst', 10),
    ('Procurement Specialist', 10);

   select * from salaries s ;
  select * from employees e;
 select * from positions p ;
select * from settings s ;
select
        s1_0.id,
        s1_0.created_at,
        s1_0.key,
        s1_0.name,
        s1_0.updated_at,
        s1_0.value
    from
        settings s1_0


        INSERT INTO fundamentos2.dbo.settings
(name, value, created_at, updated_at, key_val)
VALUES('UIT', 5140, getdate(), null, 'uit'),
('AFP',0.10,getdate(),null,'afp'),
('ONP',0.12,getdate(),null,'onp'),
('SEGURO',0.9,getdate(),null,'seguro')}
 */