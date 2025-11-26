CREATE TABLE Tenant(
   
    tenant_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    moving_date DATE NOT NULL,
    deposit DECIMAL(10,2) NOT NULL,
    house_number VARCHAR(20) NOT NULL
);
