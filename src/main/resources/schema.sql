drop table if exists project_by_user;

drop table if exists users;

drop table if exists projects;

create table if not exists users (
   id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
   name VARCHAR(255),
   email VARCHAR(255)
);

create table if not exists projects (
  id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(255)
);

create table if not exists project_by_user (
     project_id BIGINT,
     user_id BIGINT,
     PRIMARY KEY (project_id, user_id),
     FOREIGN KEY (project_id) REFERENCES projects (id),
     FOREIGN KEY (user_id) REFERENCES users (id)
);
