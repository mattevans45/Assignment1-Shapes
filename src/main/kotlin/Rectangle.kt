package shapes
import kotlin.math.abs

open class Rectangle (private val upperLeft: Point, private val lowerRight: Point) : Shape() {

    init {
        validateDimensions()
    }

    open fun getWidth(): Double = abs(lowerRight.getX() - upperLeft.getX())

    open fun getHeight(): Double = abs(lowerRight.getY() - upperLeft.getY())

    override fun getArea(): Double = getWidth() * getHeight()

    override fun move(deltaX: Double, deltaY: Double) {
        upperLeft.move(deltaX, deltaY)
        lowerRight.move(deltaX, deltaY)
    }

    override fun getAttributes(): List<Point> = listOf(
        upperLeft.clone(),
        lowerRight.clone(),
        Point(lowerRight.getX(), upperLeft.getY()),  // upperRight
        Point(upperLeft.getX(), lowerRight.getY())   // lowerLeft
    )

    private fun validateDimensions() {
        if (getWidth() <= 0.0 || getHeight() <= 0.0) {
            throw IllegalArgumentException("Width and height must be greater than zero.")
        }
    }
}
