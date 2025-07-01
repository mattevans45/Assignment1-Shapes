package shapes

class Square(upperLeft: Point, sideLength: Double) :
    Rectangle(upperLeft, Point(upperLeft.getX() + sideLength, upperLeft.getY() + sideLength)) {

    init {
        require(sideLength > 0.0 && sideLength.isFinite()) {
            "Side length must be a finite number greater than zero"
        }
    }

}
