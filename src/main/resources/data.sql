TRUNCATE TABLE users RESTART IDENTITY CASCADE;
TRUNCATE TABLE dish RESTART IDENTITY CASCADE;

        INSERT INTO dish (calories, carbohydrates, fat, name, protein) VALUES
        (500, 50, 20, 'Pasta', 30),
        (300, 40, 10, 'Salad', 25),
        (450, 55, 15, 'Pizza', 18),
        (600, 60, 25, 'Burger', 35),
        (200, 30, 5, 'Cake', 10);

        INSERT INTO users (age, email, full_name, goal, height, weight, sex) VALUES
        (25, 'ivanov@example.com', 'Ivan Ivanov', 0, 175, 80, 0),
        (30, 'petrova@example.com', 'Liza Petrova', 1, 165, 55, 1),
        (22, 'sidorov@example.com', 'Sidor Sidorov', 2 , 167, 75, 0),
        (28, 'smirnova@example.com', 'Olga Smirnova', 1, 170, 65, 1),
        (35, 'volkov@example.com', 'Sergey Volkov', 0, 185, 90, 0);