CREATE TABLE Kanjis( id TEXT PRIMARY KEY,
                     description TEXT,
                     popularity INT NOT NULL);

CREATE TABLE Meanings( id TEXT PRIMARY KEY,
                       description TEXT NOT NULL,
                       kanjiId TEXT NOT NULL,
                       FOREIGN KEY (kanjiId) REFERENCES Kanjis(id) ON DELETE CASCADE);

CREATE TABLE Readings( id TEXT PRIMARY KEY,
                      hiraganaReading TEXT NOT NULL,
                      romanjiReading TEXT NOT NULL,
                      readingType INT NOT NULL,
                      kanjiId TEXT NOT NULL,
                      FOREIGN KEY (kanjiId) REFERENCES Kanjis(id) ON DELETE CASCADE);