CREATE TABLE Bilett(
                       id INTEGER AUTO_INCREMENT NOT NULL,
                       film VARCHAR(255) NOT NULL,
                       antall INTEGER NOT NULL,
                       fornavn VARCHAR(255) NOT NULL,
                       etternavn VARCHAR(255) NOT NULL,
                       telefonnummer VARCHAR(25) NOT NULL,
                       epost VARCHAR(100),
                       PRIMARY KEY (id)

);