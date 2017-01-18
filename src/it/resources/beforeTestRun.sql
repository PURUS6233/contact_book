INSERT INTO contactbook.contacts
    (id, name)
SELECT 1, 'TestCase'
WHERE
    NOT EXISTS (
        SELECT id FROM contactbook.contacts WHERE id = 1
  	);