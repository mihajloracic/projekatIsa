insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'User', 'Userasd', null, 'USER', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'Admin', 'Admin', null, 'ADMIN, ROOT', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('expired', '$2a$10$PZ.A0IuNG958aHnKDzILyeD9k44EOi1Ny0VlAn.ygrGcgmVcg8PRK', 'Ana', 'Matkovic', null, 'USER', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('korisnik', '123', 'Petar', 'Petrovic', null, 'USER', 't');

insert into friendship(user_one_id, user_two_id, status, action_user_id) values (1, 3, 1, 3);