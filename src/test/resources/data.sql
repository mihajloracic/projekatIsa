insert into users(username, password, last_password_reset, authorities, enabled) values('usert', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', null, 'USER', 't');
insert into users(username, password, last_password_reset, authorities, enabled) values('admint', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', null, 'ADMIN, ROOT', 't');
insert into users(username, password, last_password_reset, authorities, enabled) values('expiredt', '$2a$10$PZ.A0IuNG958aHnKDzILyeD9k44EOi1Ny0VlAn.ygrGcgmVcg8PRK', null, 'USER', 't');

insert into friendship(user_one_id, user_two_id, status, action_user_id) values (1, 2, 1, 1);
insert into friendship(user_one_id, user_two_id, status, action_user_id) values (4, 1, 1, 4);