DROP TRIGGER IF EXISTS checkCoauthorExistence ON coauthors;
DROP FUNCTION IF EXISTS insertCoauthor;

DROP TRIGGER IF EXISTS checkSourceExistence ON sources;
DROP FUNCTION IF EXISTS insertSource;

DROP TABLE IF EXISTS coauthors;
DROP TABLE IF EXISTS sources;
DROP TABLE IF EXISTS comments;
DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR,
    password VARCHAR
);

CREATE TABLE articles (
    id SERIAL PRIMARY KEY,
    author INT NOT NULL,
    title VARCHAR,
    text TEXT,
    publication DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (author) REFERENCES users(id)
);

CREATE TABLE comments (
    id SERIAL PRIMARY KEY,
    article INT NOT NULL,
    author INT NOT NULL,
    text TEXT,
    FOREIGN KEY (article) REFERENCES articles(id) ON DELETE CASCADE,
FOREIGN KEY (author) REFERENCES users(id)
);

CREATE TABLE sources (
    id SERIAL PRIMARY KEY,
    article INT NOT NULL,
    link VARCHAR,
    FOREIGN KEY (article) REFERENCES articles(id) ON DELETE CASCADE
);

CREATE TABLE coauthors (
    id SERIAL PRIMARY KEY,
    article INT NOT NULL,
    author INT NOT NULL,
    FOREIGN KEY (article) REFERENCES articles(id) ON DELETE CASCADE,
    FOREIGN KEY (author) REFERENCES users(id) ON DELETE CASCADE
);

CREATE FUNCTION insertCoauthor() RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF EXISTS (SELECT FROM coauthors c WHERE c.article=NEW.article AND c.author=NEW.author) THEN
        RETURN NULL;
    ELSE
        RETURN NEW;
    END IF;
END;
$$;

CREATE TRIGGER checkCoauthorExistence
BEFORE INSERT ON coauthors
FOR EACH ROW
EXECUTE FUNCTION insertCoauthor();
END;

CREATE FUNCTION insertSource() RETURNS TRIGGER
    LANGUAGE plpgsql
AS $$
BEGIN
    IF EXISTS (SELECT FROM sources c WHERE c.article=NEW.article AND c.link=NEW.link) THEN
        RETURN NULL;
    ELSE
        RETURN NEW;
    END IF;
END;
$$;

CREATE TRIGGER checkSourceExistence
    BEFORE INSERT ON sources
    FOR EACH ROW
EXECUTE FUNCTION insertSource();
END;

INSERT INTO users(username, password) values
-- 1
('ya_daria', '7132a1d8f757bbf1dae79e409bfa3ec1648ce79165ef4f6c4654be217359cf1b'),
-- 2
('iliusha', '7132a1d8f757bbf1dae79e409bfa3ec1648ce79165ef4f6c4654be217359cf1b'),
-- 3
('semen300', '7132a1d8f757bbf1dae79e409bfa3ec1648ce79165ef4f6c4654be217359cf1b');

INSERT INTO articles(author, title, text, publication) values
-- 1
(1, 'Why people in Sweden do not smoke', 'Morton, a 20-year-old in Stockholm, enjoys using snus, a smokeless tobacco product popular in Sweden. Snus has contributed to Swedenâs low smoking rates, with only 5.6% of Swedes smoking in 2022. While smoking has declined, snus usage, particularly among women, has increased. Nicotine pouches, a tobacco-free alternative, have also gained popularity. Despite claims that snus helps reduce smoking-related cancer rates, health experts express concerns about nicotine addiction. The tobacco industry views alternative products like snus as a shift away from cigarettes, emphasizing reduced risk. The global market for nicotine pouches is expected to grow significantly. While some credit snus for Swedenâs success, others attribute it to anti-smoking measures implemented in the 1990s. Difficult words: pouch (a small bag), credit (to acknowledge).', '2024-01-04'),
-- 2
(1, 'California and Alabama emergencies', 'US president Biden approved an emergency declaration for California, as it struggles to cope with a series of deadly storms. Since December 26, 2022, a series of storms killed at least 19 people and brought floods, power outages, mudslides, evacuations, and road closures. Some areas had floods three times in ten days. The presidentâs action makes federal funding available to affected people in Merced, Sacramento, and Santa Cruz counties. Biden also approved an emergency declaration for Alabama on Sunday. At least five tornadoes hit central Alabama on Thursday. At least nine people died in tornadoes; they destroyed homes and stopped power to tens of thousands in the southeast United States. Difficult words: outage (a time when a power supply or other service isnât available), federal funding (money that a government uses for a specific purpose), county (a large area in a state).', '2024-01-05'),
-- 3
(2, 'Hippos in Russia', 'Hippopotamuses can be described as highly aggressive, unpredictable, and among the most dangerous animals in the world. However, ten-year-old Zlat, Jana, and Ida perform tricks as part of a touring circus show in Russia. Their handler insists that the show is totally safe for spectators who are at times only a few feet away. The big animals roll over, jump over, and even play a real-life version of Hungry Hippos as part of the performance. People reported that the hippo trio is fully trained without any physical force, but whether they want to perform is another question. Difficult words: unpredictable (changeable in mood), handler (a person who trains an animal), insist (say that something is true), spectator (a person watching a performance).', '2024-01-09'),
-- 4
(3, 'Drones help reforest Brazil', 'Rio de Janeiro, Brazil, is utilizing drones in collaboration with startup Morfo for an innovative reforestation initiative. Launched as a partnership between the city hall and Morfo, the project aims to accelerate reforestation in challenging terrains. The initiative involves drones dispersing seeds of local native species in areas identified through soil and native species analysis. AI-powered computers determine specific targets and seed quantities. The drones are highly efficient and capable of dispersing 180 seed capsules per minute, making the process 100 times faster than traditional methods involving human hands. This high-tech approach not only speeds up reforestation but also eliminates the need for nurturing seedlings for months and transporting them to planting sites. Difficult words: initiative (a new plan or process to achieve a particular goal), determine (to find out, establish), eliminate (to get rid of).', '2024-01-10'),
-- 5
(2, 'Tesla is no longer the biggest electric carmaker', 'Chinaâs BYD has become the worldâs largest seller of electric vehicles, surpassing Tesla. Despite being relatively unknown, BYDâs strategic planning and support from the Chinese government played a crucial role. The Chinese governmentâs substantial subsidies, tax exemptions, and a carrot-and-stick approach propelled BYDâs rise. The companyâs focus on cheaper electric vehicles and vertical integration, making 75% of its parts in-house, contributes to its success. BYDâs founder, Wang Chuanfu, with a background in chemistry, prioritizes cost-effective battery technology. The companyâs global expansion aims to make BYD a major player in the electric vehicle market worldwide, challenging regulatory uncertainties and building brand awareness. Difficult words: carrot-and-stick (a combination of reward and punishment used as motivation), propel (to move forward), vertical integration (a strategy where a company controls multiple stages of the production process).', '2024-01-10');


INSERT INTO comments(article, author, text) values
-- 1
(1, 2, 'Ive never been to Sweden'),
-- 2
(1, 3, 'I like smoking'),
-- 3
(5, 3, 'Tesla sucks!'),
-- 4
(3, 1, 'Hippos so nice'),
-- 5
(4, 1, 'Cool, i love forest');

INSERT INTO coauthors(article, author) values
-- 1
(1, 2),
-- 2
(3, 1),
-- 3
(5, 3),
-- 4
(4, 2),
-- 5
(4, 1);

INSERT INTO sources(article, link) values
-- 1
(1, 'wikipedia.org'),
-- 2
(1, 'lichess.org'),
-- 3
(2, 'emergenciesinusa.org'),
-- 4
(5, 'teslo.com'),
-- 5
(5, 'zarulem.ru'),
-- 6
(4, 'brazilian.drones.br')
;
