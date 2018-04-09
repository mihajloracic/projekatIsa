insert into users(username, password, firstname, lastname, email,last_password_reset, authorities, enabled) values('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'User', 'Userasd', 'alexandranevermind68@gmail.com', null, 'USER', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'Admin', 'Admin', null, 'ADMIN, ROOT', 't');
insert into users(username, password, firstname, lastname, email, last_password_reset, authorities, enabled) values('expired', '$2a$10$PZ.A0IuNG958aHnKDzILyeD9k44EOi1Ny0VlAn.ygrGcgmVcg8PRK', 'Ana', 'Matkovic', 'alalvelas@gmail.com', null, 'USER', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('korisnik', '123', 'Petar', 'Petrovic', null, 'USER', 't');

insert into friendship(user_one_id, user_two_id, status, action_user_id) values (1, 3, 1, 3);

insert into venue(name, address, type, description) values ('Arena', 'Mihajla Pupina 3', 'CINEMA', 'super bioskop');
insert into venue(name, address, type, description) values ('Extra bioskop', 'Pap Pavla 34', 'CINEMA', 'odlican bioskop');
insert into venue(name, address, type, description) values ('SNP', 'Zmaj Jovina 1', 'THEATRE', 'super pozoriste');

insert into show(name, type, length, grade) values ('Dark knight', 'FILM', 130, 9.2);
insert into show(name, type, length, grade) values ('Saints of Newark', 'FILM', 130, 9.5);

insert into hall (name, venue_id, number_of_rows, number_of_cols) values ('Sala 1', 1, 10, 12);
insert into hall (name, venue_id, number_of_rows, number_of_cols) values ('Sala 1', 2, 9, 10);

insert into event (type, date, time, venue_id, show_id, hall_id) values ('PROJECTION', '2018-02-14', '20:00:00', 1, 1, 1);
insert into event (type, date, time, venue_id, show_id, hall_id) values ('PROJECTION', '2018-04-14', '21:00:00', 1, 2, 1);
insert into event (type, date, time, venue_id, show_id, hall_id) values ('PROJECTION', '2018-04-15', '18:00:00', 1, 2, 1);

insert into reservation (event_id, reservation_owner_id) values (1, 1);

insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 3);
insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 4);
insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 5);
insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 6);
