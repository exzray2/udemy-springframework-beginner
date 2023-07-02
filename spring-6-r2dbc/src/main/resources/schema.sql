CREATE TABLE IF NOT EXISTS beer
(
    id                  integer NOT NULL PRIMARY KEY AUTO_INCREMENT,
    beer_name           varchar(255),
    beer_style          varchar(255),
    upc                 varchar(255),
    quantity_on_hand    integer,
    price               decimal,
    created_on          timestamp,
    updated_on          timestamp
);