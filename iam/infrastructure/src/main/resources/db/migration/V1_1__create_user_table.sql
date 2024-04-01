CREATE TABLE user (
                      id bigint PRIMARY KEY,
                      created_at TIMESTAMP,
                      last_modified_at TIMESTAMP,
                      email_address VARCHAR(255) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      firstname VARCHAR(255) NOT NULL,
                      lastname VARCHAR(255) NOT NULL,
                      phone VARCHAR(255) NOT NULL,
                      address VARCHAR(255) NOT NULL,
                      role ENUM('SUPER_ADMIN', 'ADMIN', 'USER') NOT NULL
);

ALTER TABLE user
    ADD CONSTRAINT uidx_user_email_address UNIQUE (email_address);