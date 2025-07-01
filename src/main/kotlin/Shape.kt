package shapes

abstract class Shape {
    abstract fun getArea(): Double
    abstract fun move(deltaX: Double, deltaY: Double)
    abstract fun getAttributes(): List<Any>
}
