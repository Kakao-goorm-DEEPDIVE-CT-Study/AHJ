
-- -----------------------------------------------------
-- Table `myboard`.`board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myboard`.`board` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `board_name` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myboard`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myboard`.`post` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `board_id` BIGINT(32) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(4) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `content` TEXT NULL,
  `posted_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myboard`.`reply`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `myboard`.`reply` (
  `id` BIGINT(32) NOT NULL AUTO_INCREMENT,
  `post_id` BIGINT(32) NOT NULL,
  `user_name` VARCHAR(50) NOT NULL,
  `password` VARCHAR(4) NOT NULL,
  `status` VARCHAR(50) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `content` TEXT NOT NULL,
  `replyed_at` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;
