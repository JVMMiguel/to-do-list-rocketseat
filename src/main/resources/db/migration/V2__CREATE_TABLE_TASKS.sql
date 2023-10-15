CREATE TABLE IF NOT EXISTS TB_TASKS (
    id UUID NOT NULL,
    title VARCHAR(255),
    description VARCHAR(100),
    priority VARCHAR(255),
    created_by UUID NOT NULL,
    start_at TIMESTAMP NOT NULL,
    end_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL,

    CONSTRAINT pk_tasks PRIMARY KEY (id)
);

ALTER TABLE tb_tasks ADD CONSTRAINT fk_tasks_users FOREIGN KEY (created_by) REFERENCES tb_users(id);

INSERT INTO public.tb_tasks(id, title, description, priority, created_by, start_at, end_at, created_at)
VALUES ('c9eeacb5-4886-4b12-9b2b-4cd7d6c3593b', 'Task title', 'Task description', 'HIGH', 'a0dc701f-c7ce-40f5-afe8-c935e0fc7fcf', '2023-10-21T12:00:00', '2023-10-22T12:00:00', '2023-10-14T23:59:00');