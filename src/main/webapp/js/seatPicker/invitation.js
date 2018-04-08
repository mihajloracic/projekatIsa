function Invitation(username) {
    this.username = username;
    this.friends = [];
}

Invitation.prototype.addFriendInv = function(username) {
    this.friends.push(username);
}

Invitation.prototype.removeFriendInv = function(username) {
    const index = this.friends.indexOf(username);

    if (index !== -1) {
        this.friends.splice(index, 1);
    }
}

Invitation.prototype.getInvitedFriends = function() {
    return this.friends;
}


Invitation.prototype.containsFriend = function(username) {
   return this.friends.indexOf(username) !== -1;
}

Invitation.prototype.getInvitedNum = function() {
    return this.friends.length;
}
