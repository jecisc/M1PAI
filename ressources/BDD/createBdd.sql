/* We can't use this with alwaysdata.*/
CREATE DATABASE "M1PAI"
  WITH OWNER = postgres
  ENCODING = 'UTF8'
  TABLESPACE = pg_default
  LC_COLLATE = 'French_France.1252'
  LC_CTYPE = 'French_France.1252'
  CONNECTION LIMIT = -1;



SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = OFF;
SET check_function_bodies = FALSE;
SET client_min_messages = WARNING;
SET escape_string_warning = OFF;

SET default_tablespace = '';

SET default_with_oids = FALSE;

-- User

CREATE SEQUENCE user_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE APPLIUSER (
  idUser      INTEGER PRIMARY KEY   NOT NULL DEFAULT nextval('user_id_seq' :: REGCLASS),
  email       CHARACTER VARYING(64) NOT NULL,
  firstName   CHARACTER VARYING(64) NOT NULL,
  lastName    CHARACTER VARYING(64) NOT NULL,
  pseudo      CHARACTER VARYING(64) NOT NULL,
  passwd      CHARACTER VARYING(64) NOT NULL,
  inscription DATE                  NOT NULL,
  isActive    BOOLEAN               NOT NULL,
  avatar      CHARACTER VARYING
);

ALTER SEQUENCE user_id_seq OWNED BY APPLIUSER.idUser;

-- CATEGORY

CREATE SEQUENCE category_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE CATEGORY (
  idCategory  INTEGER PRIMARY KEY   NOT NULL DEFAULT nextval('category_id_seq' :: REGCLASS),
  designation CHARACTER VARYING(64) NOT NULL
);

ALTER SEQUENCE category_id_seq OWNED BY CATEGORY.idCategory;

-- Ressources

CREATE SEQUENCE ressource_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE RESSOURCE (
  idRessource INTEGER PRIMARY KEY   NOT NULL DEFAULT nextval('ressource_id_seq' :: REGCLASS),
  name        CHARACTER VARYING(64) NOT NULL,
  icon        CHARACTER VARYING(64) NOT NULL,
  category INTEGER REFERENCES CATEGORY (idCategory)
);

ALTER SEQUENCE ressource_id_seq OWNED BY RESSOURCE.idRessource;

-- Event

CREATE SEQUENCE event_id_seq
START WITH 1
INCREMENT BY 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE EVENT (
  idEvent      INTEGER PRIMARY KEY   NOT NULL DEFAULT nextval('event_id_seq' :: REGCLASS),
  name         CHARACTER VARYING(64) NOT NULL,
  emailCreator CHARACTER VARYING(64) NOT NULL,
  dateBegin    DATE                  NOT NULL,
  dateEnd      DATE,
  hourBegin    TIME                  NOT NULL,
  hourEnd      TIME,
  description  CHARACTER VARYING(64) NOT NULL,
  place        CHARACTER VARYING(64) NOT NULL
);

ALTER SEQUENCE event_id_seq OWNED BY EVENT.idEvent;

-- areFriend

CREATE TABLE AREFRIEND (
  friend1      INTEGER REFERENCES APPLIUSER (idUser),
  friend2      INTEGER REFERENCES APPLIUSER (idUser),
  PRIMARY KEY (friend1, friend2)
);


CREATE TABLE ASKFRIEND (
  asker INTEGER REFERENCES APPLIUSER(idUser),
  friend INTEGER REFERENCES APPLIUSER(idUser),
  PRIMARY KEY (friend,asker)
);
-- participate

CREATE TABLE PARTICIPATE (
  appliuser INTEGER REFERENCES APPLIUSER (idUser),
  event     INTEGER REFERENCES EVENT (idEvent),
  accepted  BOOLEAN NOT NULL,
  PRIMARY KEY (appliuser, event)
);
-- needed

CREATE TABLE NEEDED (
  event         INTEGER REFERENCES EVENT (idEvent),
  ressource     INTEGER REFERENCES RESSOURCE (idRessource),
  needed        INTEGER NOT NULL,
  isFacultative BOOLEAN NOT NULL,
  PRIMARY KEY (event, ressource)
);
-- Provided

CREATE TABLE PROVIDED (
  appliuser INTEGER REFERENCES APPLIUSER (idUser),
  ressource INTEGER REFERENCES RESSOURCE (idRessource),
  provided  INTEGER NOT NULL,
  PRIMARY KEY (appliuser, ressource)
);