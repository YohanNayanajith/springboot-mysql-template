CREATE TABLE course (
    course_id INTEGER AUTO_INCREMENT NOT NULL, -- Unique identifier for each course, auto-incremented
    title VARCHAR(80) NOT NULL, -- Course title, maximum 80 characters, cannot be NULL
    description VARCHAR(500) NOT NULL, -- Course description, maximum 500 characters, cannot be NULL
    link VARCHAR(255) NOT NULL, -- Link to the course, maximum 255 characters, cannot be NULL
    CONSTRAINT pk_course_course_id PRIMARY KEY (course_id) -- Primary key constraint on "course_id"
);