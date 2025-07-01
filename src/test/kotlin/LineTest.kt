package shapes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.math.sqrt

class LineTest {

    @Test
    fun testLineLength() {
        val line = Line(Point(0.0, 0.0), Point(3.0, 4.0))
        assertEquals(5.0, line.getLength())
    }

    @Test
    fun testLineArea() {
        val line = Line(Point(0.0, 0.0), Point(3.0, 4.0))
        assertEquals(0.0, line.getArea())
    }

    @Test
    fun testLineGetSlope() {
        val line = Line(Point(0.0, 0.0), Point(4.0, 2.0))
        assertEquals(0.5, line.getSlope())
    }
    @Test
    fun testLineMove() {
        val line = Line(Point(1.0, 1.0), Point(4.0, 5.0))
        line.move(2.0, 3.0)

        val attributes = line.getAttributes()
        assertEquals(3.0, attributes[0].getX())
        assertEquals(4.0, attributes[0].getY())
        assertEquals(6.0, attributes[1].getX())
        assertEquals(8.0, attributes[1].getY())
    }

    @Test
    fun testGetAttributes() {
        val pointA = Point(1.0, 2.0)
        val pointB = Point(4.0, 6.0)
        val line = Line(pointA, pointB)

        val attributes = line.getAttributes()
        assertEquals(2, attributes.size)
        assertEquals(1.0, attributes[0].getX())
        assertEquals(2.0, attributes[0].getY())
        assertEquals(4.0, attributes[1].getX())
        assertEquals(6.0, attributes[1].getY())
    }

    @Test
    fun testZeroLengthLine() {
        assertFailsWith<IllegalArgumentException> {
            Line(Point(1.0, 1.0), Point(1.0, 1.0))
        }
    }

    @Test
    fun testPositiveSlope() {
        val line = Line(Point(0.0, 0.0), Point(2.0, 2.0))
        assertEquals(1.0, line.getSlope())
    }

    @Test
    fun testNegativeSlope() {
        val line = Line(Point(0.0, 2.0), Point(2.0, 0.0))
        assertEquals(-1.0, line.getSlope())
    }

    @Test
    fun testVerticalLineSlope() {
        val line = Line(Point(1.0, 0.0), Point(1.0, 2.0))
        assertFailsWith<IllegalStateException>("Slope is undefined (vertical line)") {
            line.getSlope()
        }
    }

    @Test
    fun testHorizontalLineSlope() {
        val line = Line(Point(0.0, 1.0), Point(2.0, 1.0))
        assertEquals(0.0, line.getSlope())
    }

    @Test
    fun testLineWithDecimalPoints() {
        val line = Line(Point(0.5, 1.5), Point(2.5, 4.5))
        assertEquals(1.5, line.getSlope())  // Corrected expected value
        assertEquals(sqrt(13.0), line.getLength(), 0.000001)
    }
}
