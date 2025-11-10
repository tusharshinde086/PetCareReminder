# PetCareReminder
Java Servlet + JDBC CRUD + Popup Reminder
    database    code      CREATE DATABASE petcare_db;
USE petcare_db;


CREATE TABLE users (
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  password VARCHAR(100)
);

CREATE TABLE pet_types (
  type_id INT AUTO_INCREMENT PRIMARY KEY,
  type_name VARCHAR(50) UNIQUE
);


CREATE TABLE activity_types (
  activity_type_id INT AUTO_INCREMENT PRIMARY KEY,
  activity_name VARCHAR(50) UNIQUE
);

-- pets
CREATE TABLE pets (
  pet_id INT AUTO_INCREMENT PRIMARY KEY,
  owner_id INT DEFAULT NULL,
  pet_name VARCHAR(100),
  type VARCHAR(50),
  breed VARCHAR(100),
  age INT,
  gender VARCHAR(10),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- activities
CREATE TABLE activities (
  activity_id INT AUTO_INCREMENT PRIMARY KEY,
  pet_id INT,
  activity_name VARCHAR(100),
  scheduled_date DATE,
  scheduled_time TIME,
  frequency VARCHAR(20), -- Daily / Weekly / Monthly / Once
  status VARCHAR(20) DEFAULT 'Due', -- Due / Done / Overdue
  notes VARCHAR(255),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (pet_id) REFERENCES pets(pet_id) ON DELETE CASCADE
);

-- sample pet types & activity types
INSERT INTO pet_types(type_name) VALUES ('Dog'),('Cat'),('Bird'),('Rabbit');
INSERT INTO activity_types(activity_name) VALUES ('Feeding'),('Walking'),('Vaccination'),('Bath'),('Grooming');

-- sample pets
INSERT INTO pets(pet_name, type, breed, age, gender) VALUES
('Bruno','Dog','Labrador',3,'Male'),
('Kitty','Cat','Persian',2,'Female'),
('Coco','Bird','Parrot',1,'Male');

-- sample activities
INSERT INTO activities(pet_id, activity_name, scheduled_date, scheduled_time, frequency, status, notes) VALUES
(1,'Feeding','2025-11-10','08:00:00','Daily','Due','Chicken & rice'),
(1,'Walk','2025-11-10','18:30:00','Daily','Due','Park'),
(2,'Vaccination','2025-11-15','10:00:00','Once','Due','Vet clinic'),
(3,'Singing Practice','2025-11-11','09:00:00','Daily','Due','Reward with seeds');
