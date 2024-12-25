CREATE TABLE IF NOT EXISTS customer (
    CUSTOMER_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255),
    DATE_OF_BIRTH DATE,
    EMAIL VARCHAR(255),
    CREATED_AT TIMESTAMP,
    UPDATED_AT TIMESTAMP,
    PHONE VARCHAR(255) );

CREATE TABLE IF NOT EXISTS address (
    addressId BIGINT AUTO_INCREMENT PRIMARY KEY,
    addressType VARCHAR(50),
    customerId VARCHAR(255),
    houseNo VARCHAR(255),
    street VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    postalCode VARCHAR(255),
    country VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS CONTACT_DETAILS (
    CONTACT_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    CUSTOMER_ID BIGINT,
    email VARCHAR(255),
    phone VARCHAR(255),
    CONTACT_TYPE VARCHAR(50)
);