package shapes


import kotlin.math.abs

class Triangle(
    private val pointA: Point,
    private val pointB: Point,
    private val pointC: Point
) : Shape() {

    init {
        validateDimensions()
    }

    override fun getArea(): Double {
        // Using the Shoelace formula (Gauss's area formula)
        val area = 0.5 * abs(
            (pointA.getX() * (pointB.getY() - pointC.getY())) +
            (pointB.getX() * (pointC.getY() - pointA.getY())) +
            (pointC.getX() * (pointA.getY() - pointB.getY()))
        )
        return area
    }

    override fun move(deltaX: Double, deltaY: Double) {
        pointA.move(deltaX, deltaY)
        pointB.move(deltaX, deltaY)
        pointC.move(deltaX, deltaY)
    }

    override fun getAttributes(): List<Point> = listOf(
        pointA.clone(),
        pointB.clone(),
        pointC.clone()
    )

    private fun validateDimensions() {
        // Check if the three points are collinear (form a straight line)
        val area = getArea()
        if (area == 0.0) {
            throw IllegalArgumentException("The three points cannot be collinear (form a straight line)")
        }
    }
}
