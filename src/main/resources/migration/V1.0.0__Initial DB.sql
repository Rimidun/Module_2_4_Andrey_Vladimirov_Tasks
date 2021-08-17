CREATE TABLE IF NOT EXISTS events(
  id BIGINT NOT NULL AUTO_INCREMENT,
  time TIMESTAMP,
  user_id BIGINT NOT NULL,
  file_id BIGINT NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS files(
  id BIGINT NOT NULL AUTO_INCREMENT,
  name varchar(50),
  type varchar(10),
  size FLOAT,
  status varchar(50),
  created TIMESTAMP,
  last_modified TIMESTAMP,
  user_id BIGINT,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users(
  id BIGINT NOT NULL AUTO_INCREMENT,
  first_name varchar(50),
  last_name varchar(50),
  PRIMARY KEY (id)
);