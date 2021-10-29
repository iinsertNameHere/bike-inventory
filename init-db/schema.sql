create table bike(
    id SERIAL PRIMARY KEY,
    brand TEXT NOT NULL,
    color TEXT NOT NULL,
    number_of_gears INTEGER NOT NULL,
    created_date TIMESTAMP NOT NULL,
    created_by TEXT NOT NULL,
    last_modified_by TEXT NOT NULL,
    state TEXT NOT NULL);