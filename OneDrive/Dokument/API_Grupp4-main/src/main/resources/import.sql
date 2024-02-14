INSERT INTO Recipes (name, chef, imgUrl)
VALUES 
    ('Spaghetti Carbonara', 'Chef 1', 'https://example.com/recipe_1.jpg'),
    ('Chicken Curry', 'Chef 2', 'https://example.com/recipe_2.jpg'),
    ('Chocolate Cake', 'Chef 3', 'https://example.com/recipe_3.jpg'),
    ('Caesar Salad', 'Chef 4', 'https://example.com/recipe_4.jpg'),
    ('Beef Stroganoff', 'Chef 5', 'https://example.com/recipe_5.jpg');
    -- Lägg till resten av recepten här...

-- Lägg till mockdata för ingredienser
INSERT INTO ingredients (recipe_id, ingredient)
VALUES 
    (1, 'Spaghetti'), (1, 'Bacon'), (1, 'Eggs'), (1, 'Parmesan Cheese'), (1, 'Heavy Cream'),
    (2, 'Chicken'), (2, 'Curry Sauce'), (2, 'Coconut Milk'), (2, 'Rice'), (2, 'Bell Pepper');
    -- Lägg till resten av ingredienserna här...

-- Lägg till mockdata för steg
INSERT INTO steps (recipe_id, step)
VALUES 
    (1, 'Cook spaghetti according to package instructions.'),
    (1, 'Fry bacon until crispy, then chop it into small pieces.'),
    (1, 'In a bowl, whisk together eggs, grated Parmesan cheese, and heavy cream.'),
    (1, 'Combine cooked spaghetti with bacon and egg mixture, tossing until well coated.'),
    (1, 'Serve immediately with additional grated Parmesan cheese on top.');

insert into users (uuid, firstName, lastName, userName, password) values ('a7444bcc-a82e-4dda-abd2-201e5f6c0b45', 'Domenico', 'Monnoyer', 'dmonnoyer0', 'gE2b02');
insert into users (uuid, firstName, lastName, userName, password) values ('3578266d-b985-4203-af8d-5fddf99636d1', 'Iorgo', 'Gorbell', 'igorbell1', 'iX5$Ac');
insert into users (uuid, firstName, lastName, userName, password) values ('5bf08c60-3fc3-481c-817c-2f497065a68a', 'Clare', 'Dorling', 'cdorling2', 'yH8qG&}`*Q,=');
insert into users (uuid, firstName, lastName, userName, password) values ('41a77c32-f181-4e24-96a9-e62e5e85dde3', 'Madelina', 'Wandrich', 'mwandrich3', 'kY6sivwgKt)');
insert into users (uuid, firstName, lastName, userName, password) values ('23ce97be-9293-493f-9a59-db87294db419', 'Mommy', 'Carslaw', 'mcarslaw4', 'bG5!h#?');
insert into users (uuid, firstName, lastName, userName, password) values ('ebe2b957-9677-4329-b72d-38f5ae8cd53f', 'Thoma', 'Boggas', 'tboggas5', 'pG8%20S');
insert into users (uuid, firstName, lastName, userName, password) values ('b24f6e09-ce19-4d07-a730-eeba3925e2dc', 'Borden', 'Slewcock', 'bslewcock6', 'hT0e~o">CC');
insert into users (uuid, firstName, lastName, userName, password) values ('39d1062d-2465-47ef-88e1-3e1cf435d692', 'Lura', 'Piaggia', 'lpiaggia7', 'xW4nio');
insert into users (uuid, firstName, lastName, userName, password) values ('8b0cf34b-49cd-463a-9427-5681a82e8432', 'Carlie', 'Easun', 'ceasun8', 'uC4#yZ0m!');
insert into users (uuid, firstName, lastName, userName, password) values ('de7ce658-162a-4b88-ab3f-fac5c5ed9bd0', 'Pate', 'Penreth', 'ppenreth9', 'mO6OzP~');


insert into keys ( owner, key) values ('a7444bcc-a82e-4dda-abd2-201e5f6c0b45', '$2a$10$4/XS2fsbXXXDtk8wBE0Ms.mDDL1IdctVGDUeT6PCbDCgM6AGBB8V.');
insert into keys ( owner, key) values ('a7444bcc-a82e-4dda-abd2-201e5f6c0b45', '$2a$10$jxFM852rIJ0LOQfwPwX6tuhKCyCCZqyJC2v1smIKEfDPtpxCuN5u6');