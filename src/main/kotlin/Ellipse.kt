package shapes

open class Ellipse(
    private val center: Point = Point(0.0, 0.0),
    private val radiusX: Double,
    private val radiusY: Double
) : Shape() {

    init {
        validateDimensions()
    }


    override fun getArea(): Double = Math.PI * radiusX * radiusY

    override fun move(deltaX: Double, deltaY: Double) {
        center.move(deltaX, deltaY)
    }

    override fun getAttributes(): List<Any> = listOf(
        center.clone(),
        radiusX,
        radiusY
    )


    private fun validateDimensions() {
        if (radiusX <= 0.0 || radiusY <= 0.0 || !radiusX.isFinite() || !radiusY.isFinite()) {
            throw IllegalArgumentException("Radius must be a finite number greater than zero.")
        }
    }

}
