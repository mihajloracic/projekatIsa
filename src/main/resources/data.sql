insert into users(username, password, last_password_reset, authorities, enabled) values('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', null, 'USER', 't');
insert into users(username, password, last_password_reset, authorities, enabled) values('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', null, 'ADMIN, ROOT', 't');
insert into users(username, password, last_password_reset, authorities, enabled) values('expired', '$2a$10$PZ.A0IuNG958aHnKDzILyeD9k44EOi1Ny0VlAn.ygrGcgmVcg8PRK', null, 'USER', 'f');