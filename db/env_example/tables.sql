CREATE TABLE inventories(
    id_inventory BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    owner TEXT REFERENCES users(username),
    name_inventory TEXT NOT NULL,
    password_inventory TEXT,
    total_items BIGINT NOT NULL
);

CREATE TABLE users(
    id_users BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username TEXT UNIQUE NOT NULL,
    password TEXT NOT NULL
);

CREATE TABLE items(
    id_inventory BIGINT REFERENCES inventories(id_inventory),
    item TEXT NOT NULL,
    amount BIGINT NOT NULL,
    unit_price NUMERIC(10,2),
    total_price NUMERIC(10,2)
);