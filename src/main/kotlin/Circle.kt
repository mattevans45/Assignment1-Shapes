package shapes

class Circle(center: Point, radiusX: Double, radiusY:Double) : Ellipse(center, radiusX, radiusY) {
    init {
        require(radiusX == radiusY) {
            "Both radii must be equal in a circle."
        }
    }
}
