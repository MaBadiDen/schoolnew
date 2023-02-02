-- liquibase formatted sql

-- changeset truemabadi:1
CREATE INDEX name_index ON student (name);

-- changeset truemabadi:2
CREATE INDEX color_name_index ON faculty (color, name);