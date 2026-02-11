CREATE USER admin WITH PASSWORD 'yourpassword';
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public to admin;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO admin;

CREATE USER admin_inventories WITH PASSWORD 'yourpassword';
GRANT ALL PRIVILEGES ON inventories TO admin_inventories;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO admin_inventories;

GRANT ALL PRIVILEGES ON items TO admin_inventories;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO admin_inventories;

CREATE USER admin_inventories WITH PASSWORD 'yourpassword';
GRANT ALL PRIVILEGES ON users TO admin_users;
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO admin_users;