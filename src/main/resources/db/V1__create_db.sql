CREATE TABLE user
(
    id              SERIAL          NOT NULL,
    email           VARCHAR(255)    NOT NULL,
    password        VARCHAR(255)    NOT NULL,
    first_name      VARCHAR(255)    NOT NULL,
    last_name       VARCHAR(255)    NOT NULL,
    account_type    VARCHAR(255)    NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE grade_book (
    id              SERIAL          NOT NULL,
    date            DATETIME        NOT NULL,
    mark            VARCHAR(255)    NOT NULL,
    student_login   VARCHAR(255)    NOT NULL REFERENCES user(email) ON UPDATE CASCADE ON DELETE CASCADE,
    subject         VARCHAR(255)    NOT NULL,
    teacher_login   VARCHAR(255)    NOT NULL REFERENCES user(email) ON UPDATE CASCADE ON DELETE CASCADE,
    CONSTRAINT grade_book_pk PRIMARY KEY (id)
);