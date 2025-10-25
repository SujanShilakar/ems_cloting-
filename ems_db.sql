-- Insert sample data for EMS Clothing Store
USE emsdb;

-- Admin account
INSERT INTO user (fullName, email, password, role, address, phone)
VALUES ('Admin User', 'admin@ems.com', 'password', 'ADMIN', 'Adelaide, SA', '0400000000');

-- Sample customer accounts
INSERT INTO user (fullName, email, password, role, address, phone)
VALUES
('John Smith', 'john@example.com', '12345', 'CUSTOMER', 'Melbourne, VIC', '0412345678'),
('Emily Davis', 'emily@example.com', '12345', 'CUSTOMER', 'Sydney, NSW', '0498765432');

-- Categories
INSERT INTO category (name) VALUES 
('Men'), ('Women'), ('Kids');

-- Products
INSERT INTO product (name, price, imageUrl, stock, category_id) VALUES
('Men T-Shirt', 25.00, 'uploads/men_tshirt.jpg', 20, 1),
('Men Jeans', 55.00, 'uploads/men_jeans.jpg', 10, 1),
('Women Dress', 45.00, 'uploads/women_dress.jpg', 15, 2),
('Women Top', 35.00, 'uploads/women_top.jpg', 12, 2),
('Kids Hoodie', 30.00, 'uploads/kids_hoodie.jpg', 8, 3),
('Kids Shorts', 20.00, 'uploads/kids_shorts.jpg', 10, 3);

-- Example order (Paid)
INSERT INTO orders (user_id, total, status, created_at)
VALUES (2, 80.00, 'PAID', NOW());

-- Example order items
INSERT INTO order_item (order_id, product_id, qty, price)
VALUES 
(1, 1, 2, 25.00),
(1, 5, 1, 30.00);

-- Confirmation
SELECT '✅ Sample data inserted successfully!' AS message;
