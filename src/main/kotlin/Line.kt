package shapes

import kotlin.math.sqrt

class Line(private val pointA: Point, private val pointB: Point) : Shape() {

    init {
        validateDimensions()
    }

    override fun getArea(): Double = 0.0

    override fun move(deltaX: Double, deltaY: Double) {
        pointA.move(deltaX, deltaY)
        pointB.move(deltaX, deltaY)
    }

    override fun getAttributes(): List<Point> = listOf(pointA.clone(), pointB.clone())

    fun getLength(): Double {
        val dx = pointA.getX() - pointB.getX()
        val dy = pointA.getY() - pointB.getY()
        return sqrt(dx * dx + dy * dy)
    }

    fun getSlope(): Double {
        val dx = pointB.getX() - pointA.getX()
        val dy = pointB.getY() - pointA.getY()
        if (dx == 0.0) throw IllegalStateException("Slope is undefined (vertical line)")
        return dy / dx
    }

    private fun validateDimensions() {
        if (getLength() == 0.0) throw IllegalArgumentException("Line cannot have zero length")
    }
} 
