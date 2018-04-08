function Cell(x, y, w, row, col) {
    this.row = row + 1;
    this.col = col + 1;
    this.x = x;
    this.y = y;
    this.w = w;
    this.r = w * 0.7;
    this.centerX = x + w / 2;
    this.centerY =  y + w / 2;
    this.reserved = false;
    this.clicked = false;
}

Cell.prototype.isReserved = function() {
    return this.reserved;
}

Cell.prototype.isClicked = function() {
    return this.clicked;
}

Cell.prototype.show = function(){
    stroke(0);
    noFill();
    ellipse(this.centerX, this.centerY, this.r);
    if (this.reserved) {
        stroke(0);
        fill(127);
        ellipse(this.centerX, this.centerY, this.r);
    }
    if (this.clicked) {
        stroke(0);
        fill(100, 200, 120);
        ellipse(this.centerX, this.centerY, this.r);
    }
}

Cell.prototype.book = function(){
    if (!this.clicked && !this.reserved) {
        this.clicked = true;
    } else {
        this.clicked = false;
    }
    return this.clicked;
}



Cell.prototype.containsPoint = function(x, y){

    return (x > this.x && x < this.x + this.w && y > this.y && y < this.y + this.w);

    // var distSquared = Math.sqrt(this.centerX - x) + Math.sqrt(this.centerY - y);
    // var rSquared = Math.sqrt(this.r);

    // return (distSquared <= rSquared);
}
