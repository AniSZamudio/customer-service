CREATE TABLE customer
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name  VARCHAR(255),
    last_name   VARCHAR(255),
    email       VARCHAR(255),
    create_date DATE
);

CREATE TABLE customer_address
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_id BIGINT NOT NULL,
    street      VARCHAR(255),
    city        VARCHAR(255),
    postal_code INT,
    country     VARCHAR(255)
);

ALTER TABLE customer_address
    ADD CONSTRAINT fk_customer_address_customer
        FOREIGN KEY (customer_id) REFERENCES customer (id);

INSERT INTO customer (first_name, last_name, email, create_date)
VALUES ('Juan', 'Pérez', 'juan.perez@example.com', '2025-06-10'),
       ('María', 'Gómez', 'maria.gomez@example.com', '2025-06-12'),
       ('Carlos', 'López', 'carlos.lopez@example.com', '2025-06-15'),
       ('Ana', 'Martínez', 'ana.martinez@example.com', '2025-06-17'),
       ('Luis', 'Ramírez', 'luis.ramirez@example.com', '2025-06-18');

INSERT INTO customer_address (customer_id, street, city, postal_code, country)
VALUES (1, 'Calle 123', 'Bogotá', 110111, 'Colombia'),
       (2, 'Avenida 45', 'Medellín', 050001, 'Colombia'),
       (3, 'Carrera 9', 'Cali', 760001, 'Colombia'),
       (4, 'Calle 80', 'Barranquilla', 080001, 'Colombia'),
       (5, 'Avenida Siempre Viva', 'Cartagena', 130001, 'Colombia');