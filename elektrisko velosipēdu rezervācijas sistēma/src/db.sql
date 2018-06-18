--
-- File generated with SQLiteStudio v3.1.1 on pirmd. jûn. 18 17:56:04 2018
--
-- Text encoding used: System
--
PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Darbinieki
DROP TABLE IF EXISTS Darbinieki;
CREATE TABLE Darbinieki (Id INT PRIMARY KEY UNIQUE, Vards STRING NOT NULL, Uzvards STRING NOT NULL);

-- Table: Grafiks
DROP TABLE IF EXISTS Grafiks;
CREATE TABLE Grafiks (id INT PRIMARY KEY NOT NULL UNIQUE, Velosipeds INT REFERENCES Velosipedi (Id) NOT NULL, Darbinieks INT REFERENCES Darbinieki (Id) NOT NULL, Datums DATE NOT NULL, "No" TIME NOT NULL, Lidz TIME NOT NULL);

-- Table: Velosipedi
DROP TABLE IF EXISTS Velosipedi;
CREATE TABLE Velosipedi (Id INT PRIMARY KEY UNIQUE NOT NULL, Razotajs STRING, Krasa STRING, Gads TEXT);

COMMIT TRANSACTION;
PRAGMA foreign_keys = on;
