
IF Not Exist create DATABASE pizza_shop;
USE pizza_shop;


CREATE TABLE user (
    userId int PRIMARY KEY auto_increment,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(100),
    createdTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE admin (
    adminId int PRIMARY KEY auto_increment,
    firstName VARCHAR(50),
    lastName VARCHAR(50),
    email VARCHAR(50),
    password VARCHAR(100),
    createdTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



CREATE TABLE pizza (
    Id int PRIMARY KEY auto_increment,
    p_name VARCHAR(50),
    details VARCHAR(1024),
    price FLOAT,
    category VARCHAR(50),
    image VARCHAR(100),
    createdTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



INSERT into pizza (p_name, details, price,category image) VALUE
('MARGHERITA', 'A hugely popular margherita, with a deliciously tangy single cheese topping', 99, 'veg','margherita.jpeg'),
('CORNCHEESE', 'A hugely popular, with a deliciously tangy single cheese topping', 89, 'veg','corncheesepizza.jpeg'),
('MUSHROOM', 'A hugely popular margherita, with a deliciously tangy single cheese topping', 159,'veg', 'mushroompizza.jpeg'),
('BBQVEGPI', 'A hugely popular BBQVEGPIZZA, with a deliciously tangy ',280, 'veg','BBQvegipizza.jpeg'),
('CHEESEPIZA', 'A pizza, with a deliciously tangy cheese topping', 99, 'veg','cheesepiza.jpeg'),
('CHOCOLATEPIZZA', 'A  popular chocolate, with a deliciously tangy single chocolate topping', 79, 'veg','chocolatepizza.jpeg'),
('DOUBLECHEESEPIZA', 'A pizza, with double cheese topping', 199, 'veg','doublecheesepizza.jpeg'),
('CHEESEPIZA', 'A pizza, with a deliciously tangy cheese topping', 99, 'veg','cheesepizza.jpeg'),
('PERIPERI', 'A pizza, with a deliciously tangy cheese topping', 150, 'veg','periperipizza.jpeg'),
('ROASTVEGPIZZA', 'A pizza, with a deliciously tangy cheese topping', 230, 'veg','roastvegpizza.jpeg'),
('VEGGIEDELUXPIZZA', 'A pizza, with a deliciously tangy cheese topping', 280, 'veg','veggiedeluxpizza.jpeg'),
('VEGMEXICUM', 'A pizza, with a deliciously tangy cheese topping', 199, 'veg','vegmexicumpiza.jpeg'),
('VEGPIZA', 'A pizza, with a deliciously tangy cheese topping', 159, 'veg','vegpiza.jpeg');

('BBQchiken', 'A hugely popular BBQVEGPIZZA, with a deliciously tangy ',280, 'non_veg','BBQvegipizza.jpeg'),
('CHIKENFEAST', 'A hugely popular BBQVEGPIZZA, with a deliciously tangy ',250, 'non_veg','chikenfiestpizza.jpeg'),
('CHIKENPESTO', 'A hugely popular BBQVEGPIZZA, with a deliciously tangy ',200,'non_veg','chikenpestopizza.jpeg'),
('CHIKENPIZZA', 'A hugely popular BBQVEGPIZZA, with a deliciously tangy ',199, 'non_veg','chikenpizza.jpeg'),
('CHILLIGARLICCHIKEN', 'A hugely popular BBQVEGPIZZA, with a deliciously tangy ',380, 'non_veg','chilligarlicchikenpizza.jpeg'),
('GREEKPIZZA', 'A pizza, with a deliciously tangy cheese topping', 199, 'non_veg','greekpizza.jpeg'),
('MEATLOVERPIZZA', 'A pizza, with a deliciously tangy cheese topping', 239, 'non_veg','meatloverpizza.jpeg'),
('PEPPERONIPIZZA', 'A pizza, with a deliciously tangy cheese topping', 349, 'non_veg','pepperonipizza.jpeg'),
('SPANISHPIZZA', 'A pizza, with a deliciously tangy cheese topping',279, 'non_veg','spanishpizza.jpeg'),
('TANDOORICHIKEN', 'A pizza, with a deliciously tangy cheese topping', 290, 'non_veg','tandoorichikenpizza.jpeg'),
('GREEKPIZZA', 'A pizza, with a deliciously tangy cheese topping', 199, 'non_veg','greekpizza.jpeg');




CREATE TABLE cart (
    id int PRIMARY KEY auto_increment,
    userId INTEGER,
    p_Id INTEGER,
    quantity INTEGER,
    price FLOAT,
    amount FLOAT,   
    createdTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);




CREATE TABLE orderDetails (
    id int PRIMARY KEY auto_increment,
    orderId INTEGER,
    p_Id INTEGER,
    quantity INTEGER,
    amount FLOAT,
    createdTimestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
