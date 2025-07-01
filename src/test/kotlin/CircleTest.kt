package shapes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CircleTest {

    // Area calculation tests
    @Test
    fun testCircleArea() {
        val circle = Circle(Point(0.0, 0.0), 5.0, 5.0)
        assertEquals(Math.PI * 25.0, circle.getArea(), 0.001)
    }

    @Test
    fun testCircleAreaWithDecimalRadius() {
        val circle = Circle(Point(0.0, 0.0), 2.5, 2.5)
        assertEquals(Math.PI * 6.25, circle.getArea(), 0.001)
    }

    @Test
    fun testVerySmallRadius() {
        val circle = Circle(Point(0.0, 0.0), Double.MIN_VALUE, Double.MIN_VALUE)
        assertEquals(Math.PI * Double.MIN_VALUE * Double.MIN_VALUE, circle.getArea(), 0.001)
    }

    // Movement tests
    @Test
    fun testCircleMove() {
        val circle = Circle(Point(0.0, 0.0), 5.0, 5.0)
        circle.move(3.0, 4.0)
        val attributes = circle.getAttributes()
        assertEquals(3.0, (attributes[0] as Point).getX())
        assertEquals(4.0, (attributes[0] as Point).getY())
    }

    @Test
    fun testCircleMoveWithNegativeDeltas() {
        val circle = Circle(Point(3.0, 3.0), 5.0, 5.0)
        circle.move(-2.0, -1.0)
        val attributes = circle.getAttributes()
        assertEquals(1.0, (attributes[0] as Point).getX())
        assertEquals(2.0, (attributes[0] as Point).getY())
    }

    @Test
    fun testMultipleMoves() {
        val circle = Circle(Point(0.0, 0.0), 5.0, 5.0)
        circle.move(2.0, 2.0)
        circle.move(1.0, 1.0)
        val attributes = circle.getAttributes()
        assertEquals(3.0, (attributes[0] as Point).getX())
        assertEquals(3.0, (attributes[0] as Point).getY())
    }

    // Attribute access tests
    @Test
    fun testGetAttributes() {
        val circle = Circle(Point(2.0, 2.0), 3.0, 3.0)
        val attributes = circle.getAttributes()

        assertEquals(3, attributes.size)
        // Test center point
        assertEquals(2.0, (attributes[0] as Point).getX())
        assertEquals(2.0, (attributes[0] as Point).getY())
        // Test radius
        assertEquals(3.0, attributes[1] as Double)
    }

    // Validation tests - specific scenarios
    @Test
    fun testConstructorWithZeroRadius() {
        assertFailsWith<IllegalArgumentException> {
            Circle(Point(0.0, 0.0), 0.0, 0.0)
        }
    }

    @Test
    fun testConstructorWithNegativeRadius() {
        assertFailsWith<IllegalArgumentException> {
            Circle(Point(0.0, 0.0), -5.0, -5.0)
        }
    }

    @Test
    fun testConstructorWithPositiveInfiniteRadius() {
        assertFailsWith<IllegalArgumentException> {
            Circle(Point(0.0, 0.0), Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY)
        }
    }

    @Test
    fun testConstructorWithNegativeInfiniteRadius() {
        assertFailsWith<IllegalArgumentException> {
            Circle(Point(0.0, 0.0), Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY)
        }
    }

    @Test
    fun testConstructorWithNaNRadius() {
        assertFailsWith<IllegalArgumentException> {
            Circle(Point(0.0, 0.0), Double.NaN, Double.NaN)
        }
    }

    @Test
    fun testConstructorWithUnequalRadii() {
        assertFailsWith<IllegalArgumentException> {
            Circle(Point(0.0, 0.0), 5.0, 3.0)
        }
    }

    @Test
    fun testConstructorWithSlightlyUnequalRadii() {
        assertFailsWith<IllegalArgumentException> {
            Circle(Point(0.0, 0.0), 3.14, 3.15)
        }
    }

    // Positive test case
    @Test
    fun testValidCircleConstructor() {
        val center = Point(1.0, 2.0)
        val circle = Circle(center, 4.5, 4.5)
        assertEquals(4.5, circle.getAttributes()[1] as Double)
    }
}