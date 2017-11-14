

/* Populate USER_PROFILE Table */
INSERT INTO `fxs-platform`.USER_PROFILE(fxs_type)
VALUES ('USER');
  
INSERT INTO `fxs-platform`.USER_PROFILE(fxs_type)
VALUES ('ADMIN');
  
INSERT INTO `fxs-platform`.USER_PROFILE(fxs_type)
VALUES ('LAWYER');
  
  
/* Populate one Admin User which will further create other users for the application using GUI */
INSERT INTO `fxs-platform`.APP_USER(fxs_username, fxs_password)
VALUES ('user','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm');
 
INSERT INTO `fxs-platform`.APP_USER(fxs_username, fxs_password)
VALUES ('admin','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm');
 
INSERT INTO `fxs-platform`.APP_USER(fxs_username, fxs_password)
VALUES ('laywer','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm');
 
/* Populate JOIN Table */
INSERT INTO `fxs-platform`.APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.fxs_id, profile.fxs_id FROM `fxs-platform`.app_user user, `fxs-platform`.user_profile profile
  where user.fxs_username='user' and profile.fxs_type='USER';
  
/* Populate JOIN Table */
INSERT INTO `fxs-platform`.APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.fxs_id, profile.fxs_id FROM `fxs-platform`.app_user user, `fxs-platform`.user_profile profile
  where user.fxs_username='admin' and profile.fxs_type='ADMIN';
  
/* Populate JOIN Table */
INSERT INTO `fxs-platform`.APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.fxs_id, profile.fxs_id FROM `fxs-platform`.app_user user, `fxs-platform`.user_profile profile
  where user.fxs_username='laywer' and profile.fxs_type='LAWYER';
  
create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null);