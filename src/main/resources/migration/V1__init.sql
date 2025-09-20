CREATE TYPE task_status AS ENUM ('TODO', 'IN_PROGRESS', 'DONE');

CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(255)      NOT NULL,
    role        VARCHAR(20)       NOT NULL,
    created_at  TIMESTAMP         NOT NULL DEFAULT now()
);

CREATE TABLE tasks (
    id          BIGSERIAL PRIMARY KEY,
    title       VARCHAR(200)      NOT NULL,
    description TEXT,
    status      task_status       NOT NULL DEFAULT 'TODO',
    assignee_id BIGINT            NOT NULL REFERENCES users(id),
    created_at  TIMESTAMP         NOT NULL DEFAULT now()
);

CREATE TABLE comments (
    id          BIGSERIAL PRIMARY KEY,
    task_id     BIGINT            NOT NULL REFERENCES tasks(id) ON DELETE CASCADE,
    author_id   BIGINT            NOT NULL REFERENCES users(id),
    text        TEXT              NOT NULL,
    created_at  TIMESTAMP         NOT NULL DEFAULT now()
);