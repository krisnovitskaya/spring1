BEGIN;



DROP TABLE IF EXISTS customers CASCADE;
CREATE TABLE customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Ann'),
('Bob'),
('Robert'),
('Mary'),
('Dan');

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('jeans', 180),
('t-Shirt', 50),
('shoes', 200),
('skirt', 150),
('tie', 30),
('shorts', 95),
('undershirt', 70),
('blouse', 117 ),
('jacket', 220),
('cap', 88);


DROP TABLE IF EXISTS products_customers CASCADE;
CREATE TABLE products_customers (id bigserial PRIMARY KEY, product_id bigint, customer_id bigint, actual_price int, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (customer_id) REFERENCES customers (id));
INSERT INTO products_customers (product_id, customer_id, actual_price) VALUES
(1, 1, 100),
(2, 2, 30),
(3, 3, 200),
(4, 4, 150),
(5, 5, 25),
(6, 5, 90),
(7, 3, 50),
(8, 2, 20),
(9, 3, 220),
(10, 1, 88),
(1, 2, 14),
(2, 5, 25),
(5, 2, 45),
(6, 2, 45),
(7, 1, 48),
(10, 2, 48),
(10, 2, 48);



COMMIT;