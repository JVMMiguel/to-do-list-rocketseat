CREATE TABLE IF NOT EXISTS TB_USERS (
    id UUID NOT NULL,
    full_name VARCHAR(255),
    user_name VARCHAR(100),
    password VARCHAR(255),
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT pk_users PRIMARY KEY (id)
);

INSERT INTO public.tb_users(id, full_name, user_name, password, created_at)
VALUES ('a0dc701f-c7ce-40f5-afe8-c935e0fc7fcf', 'João Vittor', 'joaovittor', '$2a$12$CCDkFXHV7O9XavfmfzECLeOiaa3Bat/m9J5Z1CVF8pngGkhm7vY16', '2023-10-14T12:00:00');