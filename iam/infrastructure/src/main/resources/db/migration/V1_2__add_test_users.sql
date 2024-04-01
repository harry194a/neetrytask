

INSERT INTO user (id, created_at, last_modified_at, email_address, password, firstname, lastname, phone, address, role)
VALUES (
           '2',
           '2024-01-04 00:00:00',
           '2024-01-04 00:00:00',
           'testuser1@gmail.com',
           '{bcrypt}$2a$10$HSpIG07Uohx3Kr7XvtqGP.8LRwKon7z1Cvcw2.emzmsydHJXwnnjO',
           'First',
           'Last',
           '1234567890',
           '123 Main St',
           'USER'
       );
INSERT INTO user (id, created_at, last_modified_at, email_address, password, firstname, lastname, phone, address, role)
VALUES (
           '1',
           '2024-01-04 00:00:00',
           '2024-01-04 00:00:00',
           'testAdmin@gmail.com',
           '{bcrypt}$2a$10$HSpIG07Uohx3Kr7XvtqGP.8LRwKon7z1Cvcw2.emzmsydHJXwnnjO',
           'Admin',
           'Super',
           '1234567890',
           '123 Main St',
           'SUPER_ADMIN'
       );
