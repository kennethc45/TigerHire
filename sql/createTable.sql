CREATE TABLE company (
    headquarters varchar(50),
    name varchar(50),
    purpose varchar(200),
    company_type varchar(50),
    id SERIAL PRIMARY KEY
);

CREATE TABLE jobs (
    salary varchar(50),
    location varchar(20),
    remote varchar(20),
    hours varchar(100),
    name varchar(50) NOT NULL,
    c_id int4 REFERENCES company(id) ON DELETE CASCADE,
    id SERIAL PRIMARY KEY,
    description varchar(5000),
    q1 varchar(1000),
    q2 varchar(1000),
    q3 varchar(1000)
);

CREATE TABLE applicants (
    id SERIAL PRIMARY KEY,
    username varchar(20) NOT NULL,
    password varchar(200) NOT NULL,
    j_id int4 REFERENCES jobs(id) ON DELETE CASCADE
);

CREATE TABLE recruiters (
    id SERIAL PRIMARY KEY,
    username varchar(20) NOT NULL,
    password varchar(200) NOT NULL,
    c_id int4 REFERENCES company(id) ON DELETE CASCADE
);

CREATE TABLE r_profile (
    description varchar(2000),
    current_position varchar(200),
    name varchar(100),
    location varchar(100),
    email varchar(100),
    pronouns varchar(100),
    r_id int4 REFERENCES recruiters(id) ON DELETE CASCADE
);

CREATE TABLE a_profile (
    description varchar(2000),
    education varchar(50),
    name varchar(50),
    university varchar(30),
    email varchar(20),
    pronouns varchar(50),
    a_id int4 REFERENCES applicants(id) ON DELETE CASCADE
);

CREATE TABLE inbox (
    a_id int4 REFERENCES applicants(id) ON DELETE CASCADE,
    j_id int4 REFERENCES jobs(id) ON DELETE CASCADE
);

CREATE TABLE appl (
    a_id int4 REFERENCES applicants(id) ON DELETE CASCADE,
    j_id int4 REFERENCES jobs(id) ON DELETE CASCADE,
    id SERIAL PRIMARY KEY,
    answer1 varchar(5000),
    answer2 varchar(5000),
    answer3 varchar(5000),
    experience varchar(5000)
);
