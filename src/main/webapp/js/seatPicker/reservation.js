function Reservation(eventId, username) {
    this.eventId = eventId;
    this.username = username;
    this.seats = new Map();

}

Reservation.prototype.addSeat = function(row, col) {
    this.seats.set(getSeatKey(row, col), [row, col]);
}

Reservation.prototype.removeSeat = function(row, col) {
    this.seats.delete(getSeatKey(row, col));
}

Reservation.prototype.getEventId = function() {
    return this.eventId;
}

Reservation.prototype.getUsername = function() {
    return this.username;
}

Reservation.prototype.getSeats = function() {
    return this.seats;
}

Reservation.prototype.getReservedSeatNum = function() {
    return this.seats.size;
}

function getSeatKey(i, j) {
    return i + ':' + j;
}




