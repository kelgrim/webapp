INSERT INTO TBL_USERS (username, description, email) VALUES
  ('Kelgrim', 'Developer of this Rest service', 'abc@gmail.com'),
  ('Dolphin', 'Just another testuser', 'xyz@email.com'),
  ('Barney', 'Stole captain marvels email address', 'cap@marvel.com');

INSERT INTO TBL_MESSAGES (SENDER_ID, RECIPIENT_ID, MESSAGE_TEXT)
VALUES 
	(1,3,'Kelgrim sends message to Barney'),
	(2,3,'Dolphin sends message to Barney'),
	(3,1,'Barney sends message to Kelgrim'),
	(3,1,'Barney sends another message to Kelgrim'),
	(3,1,'Barney sends yet another message to Kelgrim');





