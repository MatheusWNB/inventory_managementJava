CREATE TABLE inventories(
    id_inventory BIGINT GENERATED ALWAYS AS IDENTITY,
    owner TEXT NOT NULL,
    name_inventory TEXT NOT NULL,
    password_inventory TEXT,
    total_items BIGINT NOT NULL
);

CREATE TABLE users(
    id_users BIGINT GENERATED ALWAYS AS IDENTITY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);