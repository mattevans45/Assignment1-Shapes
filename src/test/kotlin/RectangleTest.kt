
package shapes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RectangleTest {

    @Test
    fun testRectangleArea() {
        val rectangle = Rectangle(Point(0.0, 0.0), Point(4.0, 3.0))
        assertEquals(12.0, rectangle.getArea())
    }

    @Test
    fun testRectangleMove() {
        val rectangle = Rectangle(Point(0.0, 0.0), Point(4.0, 3.0))
        rectangle.move(2.0, 2.0)
        val attributes = rectangle.getAttributes()
        assertEquals(2.0, attributes[0].getX())
        assertEquals(2.0, attributes[0].getY())
        assertEquals(6.0, attributes[1].getX())
        assertEquals(5.0, attributes[1].getY())
    }

    @Test
    fun testGetAttributes() {
        val rectangle = Rectangle(Point(2.0, 5.0), Point(5.0, 2.0))
        val attributes = rectangle.getAttributes()

        assertEquals(4, attributes.size)

        // upperLeft
        assertEquals(2.0, attributes[0].getX())
        assertEquals(5.0, attributes[0].getY())

        // lowerRight
        assertEquals(5.0, attributes[1].getX())
        assertEquals(2.0, attributes[1].getY())

        // upperRight
        assertEquals(5.0, attributes[2].getX())
        assertEquals(5.0, attributes[2].getY())

        // lowerLeft
        assertEquals(2.0, attributes[3].getX())
        assertEquals(2.0, attributes[3].getY())
    }

    @Test
    fun testRectangleDimensions() {
        val rectangle = Rectangle(Point(0.0, 0.0), Point(4.0, 3.0))
        assertEquals(4.0, rectangle.getWidth())
        assertEquals(3.0, rectangle.getHeight())
    }

    @Test
    fun testInvalidDimensions() {
        assertFailsWith<IllegalArgumentException> {
            Rectangle(Point(0.0, 0.0), Point(0.0, 3.0))
        }
        
        assertFailsWith<IllegalArgumentException> {
            Rectangle(Point(0.0, 0.0), Point(4.0, 0.0))
        }
    }
}