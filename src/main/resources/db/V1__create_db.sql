CREATE TABLE user (
    id          SERIAL          NOT NULL,
    email       VARCHAR(255)    NOT NULL,
    password    VARCHAR(255)    NOT NULL,
    firstName   VARCHAR(255)    NOT NULL,
    lastName    VARCHAR(255)    NOT NULL,
    accountType VARCHAR(255)    NOT NULL,
    PRIMARY KEY (id)
}

CREATE TABLE grade_book (
    id          SERIAL          NOT NULL,
    date        DATETIME        NOT NULL,
    mark        VARCHAR(255)   NOT NULL,
    student_id  SERIAL          NOT NULL,
    subject     VARCHAR(255)   NOT NULL,
    teacher_id  SERIAL          NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (student_id) REFERENCES user(id),
    FOREIGN KEY (teacher_id) REFERENCES user(id)
)