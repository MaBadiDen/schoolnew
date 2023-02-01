ALTER TABLE student
    ADD CONSTRAINT age_check CHECK (age > 16);

ALTER TABLE student
    ADD CONSTRAINT name_check UNIQUE (name);

ALTER TABLE student
    ALTER name SET NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT name_color_check UNIQUE (name, color);

ALTER TABLE student
    ALTER age SET DEFAULT 20;

