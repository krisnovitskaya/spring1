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
CREATE TABLE products_customers (product_id bigint, customer_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (customer_id) REFERENCES customers (id));
INSERT INTO products_customers (product_id, customer_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 5),
(7, 3),
(8, 2),
(9, 3),
(10, 1),
(1, 2),
(2, 5),
(5, 2),
(6, 2),
(7, 1),
(10, 2),
(10, 2);



COMMIT;