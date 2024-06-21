-- Удаление схемы, если она уже существует
DROP SCHEMA IF EXISTS webnotes;

-- Создание новой схемы
CREATE SCHEMA IF NOT EXISTS webnotes;

-- Использование новой схемы
USE webnotes;

-- Создание таблицы 'category'
CREATE TABLE IF NOT EXISTS `category` (
                                          `id` int NOT NULL AUTO_INCREMENT,
                                          `name` varchar(45) NOT NULL,
                                          `userId` int NOT NULL,
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Создание таблицы 'note'
CREATE TABLE IF NOT EXISTS `note` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `content` varchar(9999) DEFAULT NULL,
                                      `title` varchar(100) DEFAULT NULL,
                                      `lastUpdate` datetime NOT NULL,
                                      `userId` int NOT NULL,
                                      `categoryId` int DEFAULT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Создание таблицы 'user'
CREATE TABLE IF NOT EXISTS `user` (
                                      `id` int NOT NULL AUTO_INCREMENT,
                                      `login` varchar(30) NOT NULL,
                                      `password` varchar(100) NOT NULL,
                                      `role` varchar(45) NOT NULL,
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Установка внешних ключей
ALTER TABLE `category` ADD CONSTRAINT `fk_category_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE;
ALTER TABLE `note` ADD CONSTRAINT `fk_note_user` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE CASCADE;
ALTER TABLE `note` ADD CONSTRAINT `fk_note_category` FOREIGN KEY (`categoryId`) REFERENCES `category` (`id`) ON DELETE SET NULL;
