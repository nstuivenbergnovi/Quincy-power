insert into users (username, password, enabled)
values

('user', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 1),    -- password: password
('admin', '$2a$10$wPHxwfsfTnOJAdgYcerBt.utdAvC24B/DWfuXfzKBSDHO0etB1ica', 1)    -- password: password
ON DUPLICATE KEY UPDATE username=username;


insert into authorities (username, authority)
values
('user', 'ROLE_USER'),
('admin', 'ROLE_USER'),
('admin', 'ROLE_ADMIN')
ON DUPLICATE KEY UPDATE username=username;

insert into customer (id, country, email, first_name, last_name, phone_number)
values
(1, 'Leverworst', 'Anoniempje@hotmail.com', 'Pieter', 'Post', '123456789')

ON DUPLICATE KEY UPDATE id=id;

insert into product (id, name, price)
values
(2, 'Bruine bollen', 0.99),
(3, 'Roze koeken', 1.99)

ON DUPLICATE KEY UPDATE id=id;





