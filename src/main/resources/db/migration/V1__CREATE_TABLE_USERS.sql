CREATE TABLE IF NOT EXISTS TB_USERS (
    id UUID NOT NULL,
    full_name VARCHAR(255),
    user_name VARCHAR(100),
    password VARCHAR(255),
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id)
);