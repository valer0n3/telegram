INSERT INTO category (name_, description)
VALUES ('руки', 'все мышцы рук'),
       ('ноги', 'все мышцы ног'),
       ('торс и шея', 'все мышцы груди и шеи'),
       ('спина', 'все мышцы спины');

INSERT INTO muscle (category_id, name_, description)
VALUES (1, 'бицепс', 'тренировка бицепса'),
       (1, 'трицепс', 'тренировка трицепса'),
       (1, 'предплечье', 'тренировка предплечья'),
       (1, 'дельты', 'тренировка дельт'),
       (2, 'квадрицепс бедра', 'тренировка квадрицепса'),
       (2, 'бицепс бедра', 'тренировка бицепса бедра'),
       (2, 'икроножные', 'тренировка икроножных мышц'),
       (2, 'ягодицы', 'тренировка ягодичных мышц'),
       (3, 'шея', 'тренировка мышц шеи'),
       (3, 'грудь', 'тренировка мышц груди'),
       (3, 'пресс', 'тренировка пресса'),
       (3, 'косые мышцы живота', 'тренировка косых мышц живота'),
       (4, 'трапеции', 'тренировка трапеций'),
       (4, 'широчайшая', 'тренировка широчайшей мышцы спины');


INSERT INTO training_program (name_, description)
VALUES ('тренировка бицепса 1', 'Подъемы штанги на бицеп 5 подхоров по 10 раз'),
       ('тренировка трицепса 1', 'Разгибание рук со штангой 5 подходов по 10 раз'),
       ('тренировка пресса 1', 'скручивания торса 3 подхода по 20 раз'),
       ('тренировка бицепса 2', 'подъемы на турнике обратным хватом 5 подходов по 10 раз'),
       ('комплексная тренировка ног', 'присяды с утяжелением 5 подходом по 10 раз');

INSERT INTO muscle_training_program (muscle_id, training_program_id)
VALUES ((SELECT muscle_id FROM muscle WHERE name_ = 'бицепс'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'тренировка бицепса 1')),
       ((SELECT muscle_id FROM muscle WHERE name_ = 'бицепс'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'тренировка бицепса 2')),
       ((SELECT muscle_id FROM muscle WHERE name_ = 'трицепс'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'тренировка трицепса 1')),
       ((SELECT muscle_id FROM muscle WHERE name_ = 'пресс'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'тренировка пресса 1')),
       ((SELECT muscle_id FROM muscle WHERE name_ = 'квадрицепс бедра'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'комплексная тренировка ног')),
       ((SELECT muscle_id FROM muscle WHERE name_ = 'бицепс бедра'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'комплексная тренировка ног')),
       ((SELECT muscle_id FROM muscle WHERE name_ = 'икроножные'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'комплексная тренировка ног')),
       ((SELECT muscle_id FROM muscle WHERE name_ = 'ягодицы'),
        (SELECT training_program_id FROM training_program WHERE name_ = 'комплексная тренировка ног'));



