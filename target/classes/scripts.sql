-- Add 2 Insegnanti
INSERT INTO utenti (name, surname, age, datanascita, cf, type) VALUES ('Mario', 'Rossi', 35, '1988-01-15', 'MRORSS88A15H501S', 1);
INSERT INTO utenti (name, surname, age, datanascita, cf, type) VALUES ('Luca', 'Bianchi', 40, '1983-05-20', 'LCABNC83E20H501Z', 1);

-- Add 2 Studenti
INSERT INTO utenti (name, surname, age, datanascita, cf, type) VALUES ('Anna', 'Verdi', 20, '2003-10-10', 'ANNVRD03R10H501V', 0);
INSERT INTO utenti (name, surname, age, datanascita, cf, type) VALUES ('Elena', 'Neri', 22, '2001-04-05', 'ELNNRI01D05H501X', 0);


INSERT INTO corsi (courseName, creationDate, courseDuration, maxParticipantNumber, userid) VALUES ('Mathematics', NOW(), 6, 30, 1);
INSERT INTO corsi (courseName, creationDate, courseDuration, maxParticipantNumber, userid) VALUES ('Physics', NOW(), 6, 25, 1);
INSERT INTO corsi (courseName, creationDate, courseDuration, maxParticipantNumber, userid) VALUES ('Chemistry', NOW(), 6, 20, 2);
