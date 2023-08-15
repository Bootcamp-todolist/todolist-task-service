CREATE TABLE  IF NOT EXISTS task
(
    id           VARCHAR(36) NOT NULL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    description     VARCHAR(255) NOT NULL,
    priority         VARCHAR(36),
    status         VARCHAR(36),
    due_date timestamp,
    deleted         boolean default false,
    created_by   VARCHAR(255) NOT NULL,
    created_time timestamp    NOT NULL,
    updated_by   VARCHAR(255),
    updated_time timestamp
);

