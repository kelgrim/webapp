INSERT INTO TBL_EMPLOYEES (first_name, last_name, email) VALUES
  ('Lokesh Local', 'Gupta', 'abc@gmail.com'),
  ('Deja Local', 'Vu', 'xyz@email.com'),
  ('Caption Local', 'America', 'cap@marvel.com');

INSERT INTO TBL_MESSAGES (SENDER_ID, RECIPIENT_ID, MESSAGE_TEXT)
VALUES (1,3,'Lokesh sends message to Caption');

INSERT INTO TBL_MESSAGES (SENDER_ID, RECIPIENT_ID, MESSAGE_TEXT)
VALUES (2,3,'Deja sends message to Caption');

INSERT INTO TBL_MESSAGES (SENDER_ID, RECIPIENT_ID, MESSAGE_TEXT)
VALUES (3,1,'Caption sends message to Lokeshs');



