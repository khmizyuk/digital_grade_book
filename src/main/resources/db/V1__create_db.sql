CREATE TABLE user
(
    id          SERIAL       NOT NULL,
    email       VARCHAR(255) NOT NULL,
    password    VARCHAR(255) NOT NULL,
    firstName   VARCHAR(255) NOT NULL,
    lastName    VARCHAR(255) NOT NULL,
    accountType VARCHAR(255) NOT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
);

CREATE TABLE grade_book (
    id          SERIAL          NOT NULL,
    date        DATETIME        NOT NULL,
    mark        VARCHAR(255)    NOT NULL,
    student_id  BIGINT          NOT NULL    REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE,
    subject     VARCHAR(255)    NOT NULL    REFERENCES user(id) ON UPDATE CASCADE ON DELETE CASCADE,
    teacher_id  BIGINT UNSIGNED          NOT NULL,
    CONSTRAINT grade_book_pk PRIMARY KEY (id)
);