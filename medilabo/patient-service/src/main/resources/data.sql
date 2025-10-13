-- Prepopulate the patient database with the four test cases provided in the
-- sprint specification.  Using SQL insert statements ensures that the
-- application starts with a known state when using the default H2 database.
INSERT INTO patients (id, last_name, first_name, birth_date, gender, address, phone) VALUES
  (1, 'TestNone', 'Test', '1966-12-31', 'FEMALE', '1 Brookside St', '100-222-3333'),
  (2, 'TestBorderline', 'Test', '1945-06-24', 'MALE', '2 High St', '200-333-4444'),
  (3, 'TestInDanger', 'Test', '2004-06-18', 'MALE', '3 Club Road', '300-444-5555'),
  (4, 'TestEarlyOnset', 'Test', '2002-06-28', 'FEMALE', '4 Valley Dr', '400-555-6666');