-- Jeux de données de démo, idempotent
INSERT INTO patients (id, last_name, first_name, birth_date, gender, address, phone)
SELECT 1, 'TestNone', 'Test', DATE '1966-12-31', 'FEMALE', '1 Brookside St', '100-222-3333'
WHERE NOT EXISTS (SELECT 1 FROM patients WHERE id = 1);

INSERT INTO patients (id, last_name, first_name, birth_date, gender, address, phone)
SELECT 2, 'TestBorderline', 'Test', DATE '1945-06-24', 'MALE', '2 High St', '200-333-4444'
WHERE NOT EXISTS (SELECT 1 FROM patients WHERE id = 2);

INSERT INTO patients (id, last_name, first_name, birth_date, gender, address, phone)
SELECT 3, 'TestInDanger', 'Test', DATE '2004-06-18', 'MALE', '3 Club Road', '300-444-5555'
WHERE NOT EXISTS (SELECT 1 FROM patients WHERE id = 3);

INSERT INTO patients (id, last_name, first_name, birth_date, gender, address, phone)
SELECT 4, 'TestEarlyOnset', 'Test', DATE '2002-06-28', 'FEMALE', '4 Valley Dr', '400-555-6666'
WHERE NOT EXISTS (SELECT 1 FROM patients WHERE id = 4);