
-- =========================================
--  LIBRARY MANAGEMENT SYSTEM V1
-- =========================================

CREATE DATABASE IF NOT EXISTS library_management_system_v1;
USE library_management_system_v1;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS book_issues;
DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS members;
DROP TABLE IF EXISTS membership_categories;
DROP TABLE IF EXISTS book_categories;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS users;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE authors (
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    author_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE book_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(100) NOT NULL UNIQUE,
    short_code VARCHAR(10) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE membership_categories (
    category_id INT AUTO_INCREMENT PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL UNIQUE,
    short_code VARCHAR(10) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE members (
    member_id VARCHAR(20) PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(15) NOT NULL,
    address VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    status ENUM('Active', 'Inactive') DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE books (
    book_id VARCHAR(50) PRIMARY KEY,
    book_title VARCHAR(100) NOT NULL,
    isbn VARCHAR(50) UNIQUE,
    total_copies INT NOT NULL,
    description TEXT,
    available_copies INT NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') DEFAULT 'ACTIVE',
    author_id INT NOT NULL,
    category_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

CREATE TABLE book_issues (
    issue_id INT AUTO_INCREMENT PRIMARY KEY,
    member_id VARCHAR(20) NOT NULL,
    book_id VARCHAR(50) NOT NULL,
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    status ENUM('Issued', 'Returned') NOT NULL DEFAULT 'Issued',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
) ENGINE = InnoDB;

-- Members -> Membership Categories
ALTER TABLE members
ADD CONSTRAINT fk_members_membership_category
FOREIGN KEY (category_id)
REFERENCES membership_categories(category_id)
ON UPDATE CASCADE
ON DELETE RESTRICT;

-- Books -> Authors
ALTER TABLE books
ADD CONSTRAINT fk_books_author
FOREIGN KEY (author_id)
REFERENCES authors(author_id)
ON UPDATE CASCADE
ON DELETE RESTRICT;

-- Books -> Book Categories
ALTER TABLE books
ADD CONSTRAINT fk_books_category
FOREIGN KEY (category_id)
REFERENCES book_categories(category_id)
ON UPDATE CASCADE
ON DELETE RESTRICT;

-- Book Issues -> Members
ALTER TABLE book_issues
ADD CONSTRAINT fk_book_issues_member
FOREIGN KEY (member_id)
REFERENCES members(member_id)
ON UPDATE CASCADE
ON DELETE RESTRICT;

-- Book Issues -> Books
ALTER TABLE book_issues
ADD CONSTRAINT fk_book_issues_book
FOREIGN KEY (book_id)
REFERENCES books(book_id)
ON UPDATE CASCADE
ON DELETE RESTRICT;

INSERT INTO users (username, password)
VALUES
('admin', 'admin123'),
('librarian', 'library123'),
('staff01', 'staff123');

INSERT INTO authors (author_id, author_name)
VALUES
(1, 'Paulo Coelho'),
(2, 'Robert C. Martin'),
(3, 'Stephen Hawking'),
(4, 'Robert T. Kiyosaki');

INSERT INTO book_categories
(category_id, category_name, short_code)
VALUES
(1, 'Fiction', 'FIC'),
(2, 'Non-Fiction', 'NON'),
(3, 'Science', 'SCI'),
(4, 'Technology', 'TEC'),
(5, 'Education', 'EDU'),
(6, 'History', 'HIS'),
(7, 'Biography', 'BIO'),
(8, 'Business', 'BUS'),
(9, 'Self-Help', 'SEL'),
(10, 'Literature', 'LIT'),
(11, 'Reference', 'REF'),
(12, 'Children''s Books', 'CHI');

INSERT INTO membership_categories
(category_id, category_name, short_code)
VALUES
(1, 'Student', 'STU'),
(2, 'Regular', 'REG'),
(3, 'Premium', 'PRE');

INSERT INTO members
(member_id, full_name, email, phone, address, category_id, status)
VALUES
('STU001', 'Nimal Perera', 'nimal@example.com',
 '0771234567', 'Colombo, Sri Lanka', 1, 'Active'),
('STU002', 'Kamal Silva', 'kamal@example.com',
 '0712345678', 'Gampaha, Sri Lanka', 1, 'Active'),
('REG001', 'Amara Fernando', 'amara@example.com',
 '0761234567', 'Negombo, Sri Lanka', 2, 'Active'),
('PRE001', 'Saman Jayasuriya', 'saman@example.com',
 '0751234567', 'Kurunegala, Sri Lanka', 3, 'Active');

INSERT INTO books
(book_id, book_title, isbn, total_copies, description,
 available_copies, status, author_id, category_id)
VALUES
('FIC001', 'The Alchemist', '9780061122415', 5,
 'A novel about following your dreams.', 5, 'ACTIVE', 1, 1),
('TEC001', 'Clean Code', '9780132350884', 4,
 'A guide to writing clean and maintainable code.', 4, 'ACTIVE', 2, 4),
('SCI001', 'A Brief History of Time', '9780553380163', 3,
 'An introduction to cosmology and the universe.', 3, 'ACTIVE', 3, 3),
('EDU001', 'Learning Java', '9780000000001', 6,
 'A beginner-friendly programming book.', 6, 'ACTIVE', 2, 5),
('BUS001', 'Rich Dad Poor Dad', '9781612680194', 2,
 'A book about financial education.', 2, 'ACTIVE', 4, 8);

INSERT INTO book_issues
(member_id, book_id, issue_date, due_date, status)
VALUES
('STU001', 'FIC001', '2026-10-20', '2026-11-03', 'Issued'),
('STU002', 'TEC001', '2026-10-15', '2026-10-29', 'Issued'),
('REG001', 'SCI001', '2026-10-01', '2026-10-15', 'Returned'),
('PRE001', 'BUS001', '2026-10-22', '2026-11-05', 'Issued');
