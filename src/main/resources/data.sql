insert into users(username, password, firstname, lastname, email,last_password_reset, authorities, enabled) values('user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'User', 'Userasd', 'alexandranevermind68@gmail.com', null, 'USER', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'Admin', 'Admin', null, 'ADMIN, ROOT', 't');
insert into users(username, password, firstname, lastname, email, last_password_reset, authorities, enabled) values('expired', '$2a$10$PZ.A0IuNG958aHnKDzILyeD9k44EOi1Ny0VlAn.ygrGcgmVcg8PRK', 'Ana', 'Matkovic', 'alalvelas@gmail.com', null, 'USER', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('korisnik', '123', 'Petar', 'Petrovic', null, 'USER', 't');
insert into users(username, password, firstname, lastname, last_password_reset, authorities, enabled) values('stevan', 'stevan', 'Petar', 'Petrovic', null, 'VENUE_ADMIN', 't');


insert into friendship(user_one_id, user_two_id, status, action_user_id) values (1, 3, 1, 3);

insert into venue(name, address, type, description,lat,lng) values ('Arena', 'Mihajla Pupina 3', 'CINEMA', 'super bioskop',45.253761,19.845319);
insert into venue(name, address, type, description,lat,lng) values ('Extra bioskop', 'Pap Pavla 34', 'CINEMA', 'odlican bioskop',45.2550129,19.843056100000013);
insert into venue(name, address, type, description,lat,lng) values ('SNP', 'Zmaj Jovina 1', 'THEATRE', 'super pozoriste',45.2532761,19.8455319);

insert into show(name, type, length, grade) values ('Dark knight', 'FILM', 130, 9.2);
insert into show(name, type, length, grade) values ('Saints of Newark', 'FILM', 130, 9.5);

insert into hall (name, venue_id, number_of_rows, number_of_cols) values ('Sala 1', 1, 50, 60);
insert into hall (name, venue_id, number_of_rows, number_of_cols) values ('Sala 2', 1, 10, 12);
insert into hall (name, venue_id, number_of_rows, number_of_cols) values ('Sala 3', 1, 10, 120);
insert into hall (name, venue_id, number_of_rows, number_of_cols) values ('Sala 1', 2, 9, 10);

insert into event (type, date, time, venue_id, show_id, hall_id, price) values ('PROJECTION', '2019-02-14', '20:00:00', 1, 1, 1, 350);
insert into event (type, date, time, venue_id, show_id, hall_id, price) values ('PROJECTION', '2016-02-14', '20:00:00', 1, 1, 1, 350);
insert into event (type, date, time, venue_id, show_id, hall_id, price) values ('PROJECTION', '2018-04-14', '21:00:00', 1, 2, 1, 300);
insert into event (type, date, time, venue_id, show_id, hall_id, price) values ('PROJECTION', '2018-04-15', '18:00:00', 1, 2, 1, 250);

insert into reservation (event_id, reservation_owner_id) values (1, 1);

insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 3);
insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 4);
insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 5);
insert into reservation_seat (reservation_id, event_id, row, col) values (1, 1, 3, 6);

insert into discount_event (new_price, event_id) values (150,1);
insert into discount_event (new_price, event_id) values (150,2);
