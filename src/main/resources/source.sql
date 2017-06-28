INSERT INTO `project`.`app_user`
(`id`, `ROLE`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `SSO_ID`, `STATE`)
VALUES ('1', 'ADMIN', 'sergiobaluh@gmail.com', 'Sergiy', 'Balukh',
        '$2a$10$dlFMHNBmXO/Wj8Mrd51c3OiPKG0b2EkLNJ/gyg2gB7T2iUzzo69EO',
        'roysez', 'Active');

--         For testing login page
--         Username = roysez
--         Password = qwerty



CREATE DEFINER=`root`@`localhost` PROCEDURE `X`()
BEGIN
  DECLARE i INT DEFAULT 401;
  WHILE i < 2500 DO
  INSERT INTO project.app_user (`ROLE`, `EMAIL`, `FIRST_NAME`, `LAST_NAME`, `PASSWORD`, `SSO_ID`, `STATE`)
        VALUES('USER',i,i,i,i,i,'ACTIVE');
  SET i = i + 1;
  END WHILE;
END


CALL X(); -- RANDOM USERS