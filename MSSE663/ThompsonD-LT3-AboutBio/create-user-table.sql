/**
 * Author:  david
 * Created: Mar 5, 2017
 */

CREATE TABLE `user` (
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50) NOT NULL,
    emailaddress VARCHAR(100) NOT NULL,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(300) NOT NULL
);