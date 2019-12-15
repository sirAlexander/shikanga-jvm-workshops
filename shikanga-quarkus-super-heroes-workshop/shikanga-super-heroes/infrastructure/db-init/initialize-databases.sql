CREATE ROLE shikanga WITH LOGIN PASSWORD 'shikanga';

CREATE ROLE shaka WITH LOGIN PASSWORD 'shaka' NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE NOREPLICATION;
CREATE DATABASE shikanga_heroes;
GRANT ALL PRIVILEGES ON DATABASE shikanga_heroes TO shikanga;
GRANT ALL PRIVILEGES ON DATABASE shikanga_heroes TO shaka;