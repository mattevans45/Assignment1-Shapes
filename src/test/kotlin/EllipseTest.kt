package shapes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFailsWith

class EllipseTest {
    
    @Test
    fun testEllipseArea() {
        val ellipse = Ellipse(Point(0.0, 0.0), 3.0, 4.0)
        assertEquals(Math.PI * 12.0, ellipse.getArea())
    }

    @Test
    fun testEllipseMove() {
        val ellipse = Ellipse(Point(1.0, 1.0), 3.0, 4.0)
        ellipse.move(2.0, 3.0)

        val attributes = ellipse.getAttributes()
        assertEquals(3.0, (attributes[0] as Point).getX()) // center
        assertEquals(4.0, (attributes[0] as Point).getY())
        assertEquals(3.0, attributes[1] as Double) // radiusX
        assertEquals(4.0, attributes[2] as Double) // radiusY
    }


    @Test
    fun testGetAttributes() {
        val center = Point(2.0, 2.0)
        val ellipse = Ellipse(center, 3.0, 4.0)

        val attributes = ellipse.getAttributes()
        assertEquals(3, attributes.size)

        // Test center point
        assertEquals(2.0, (attributes[0] as Point).getX())
        assertEquals(2.0, (attributes[0] as Point).getY())

        // Test radiusX
        assertEquals(3.0, attributes[1] as Double)

        // Test radiusY
        assertEquals(4.0, attributes[2] as Double)
    }

    @Test
    fun testDefaultCenter() {
        val ellipse = Ellipse(radiusX = 3.0, radiusY = 4.0)
        val attributes = ellipse.getAttributes()
        
        assertEquals(0.0, (attributes[0] as Point).getX())
        assertEquals(0.0, (attributes[0] as Point).getY())
    }

    @Test
    fun testInvalidRadiusX() {
        assertFailsWith<IllegalArgumentException>("Should throw exception for negative radiusX") {
            Ellipse(Point(0.0, 0.0), -1.0, 4.0)
        }
        
        assertFailsWith<IllegalArgumentException>("Should throw exception for zero radiusX") {
            Ellipse(Point(0.0, 0.0), 0.0, 4.0)
        }
    }

    @Test
    fun testInvalidRadiusY() {
        assertFailsWith<IllegalArgumentException>("Should throw exception for negative radiusY") {
            Ellipse(Point(0.0, 0.0), 3.0, -1.0)
        }
        
        assertFailsWith<IllegalArgumentException>("Should throw exception for zero radiusY") {
            Ellipse(Point(0.0, 0.0), 3.0, 0.0)
        }
    }

    @Test
    fun testEllipseWithEqualRadii() {
        val ellipse = Ellipse(Point(0.0, 0.0), 5.0, 5.0)
        assertEquals(Math.PI * 25.0, ellipse.getArea())
    }

    @Test
    fun testEllipseWithDecimalRadii() {
        val ellipse = Ellipse(Point(0.0, 0.0), 2.5, 3.5)
        assertEquals(Math.PI * 8.75, ellipse.getArea())
    }
    @Test
    fun testInfiniteRadii() {
        assertFailsWith<IllegalArgumentException> {
            Ellipse(Point(0.0, 0.0), Double.POSITIVE_INFINITY, 4.0)
        }

        assertFailsWith<IllegalArgumentException> {
            Ellipse(Point(0.0, 0.0), 3.0, Double.POSITIVE_INFINITY)
        }
    }

    @Test
    fun testNaNRadii() {
        assertFailsWith<IllegalArgumentException> {
            Ellipse(Point(0.0, 0.0), Double.NaN, 4.0)
        }

        assertFailsWith<IllegalArgumentException> {
            Ellipse(Point(0.0, 0.0), 3.0, Double.NaN)
        }
    }

    @Test
    fun testEllipseInheritance() {
        val ellipse = Ellipse(Point(0.0, 0.0), 3.0, 4.0)
        assertTrue(ellipse is Shape)
    }

    @Test
    fun testMultipleMoves() {
        val ellipse = Ellipse(Point(0.0, 0.0), 3.0, 4.0)
        ellipse.move(1.0, 2.0)
        ellipse.move(2.0, 1.0)

        val attributes = ellipse.getAttributes()
        assertEquals(3.0, (attributes[0] as Point).getX())
        assertEquals(3.0, (attributes[0] as Point).getY())
        assertEquals(3.0, attributes[1] as Double) // radiusX unchanged
        assertEquals(4.0, attributes[2] as Double) // radiusY unchanged
    }
}