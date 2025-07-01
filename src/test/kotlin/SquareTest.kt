package shapes

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class SquareTest {
    
    @Test
    fun testSquareCreation() {
        val square = Square(Point(0.0, 0.0), 5.0)
        assertEquals(0.0, square.getAttributes()[0].getX())
        assertEquals(5.0, square.getWidth())
        assertEquals(5.0, square.getHeight())
    }

    @Test
    fun testSquareArea() {
        val square = Square(Point(0.0, 0.0), 5.0)
        assertEquals(25.0, square.getArea())
    }

    @Test
    fun testSquareMove() {
        val square = Square(Point(0.0, 0.0), 5.0)
        square.move(3.0, 4.0)
        val attributes = square.getAttributes()
        assertEquals(3.0, attributes[0].getX())
        assertEquals(4.0, attributes[0].getY())
        assertEquals(8.0, attributes[1].getX())
        assertEquals(9.0, attributes[1].getY())
    }

    @Test
    fun testGetAttributes() {
        val square = Square(Point(2.0, 2.0), 3.0)
        val attributes = square.getAttributes()
        
        assertEquals(4, attributes.size)
        // Test upper-left point
        assertEquals(2.0, attributes[0].getX())
        assertEquals(2.0, attributes[0].getY())
        // Test lower-right point
        assertEquals(5.0, attributes[1].getX())
        assertEquals(5.0, attributes[1].getY())
    }

    @Test
    fun testInvalidSideLength() {
        assertFailsWith<IllegalArgumentException>("Should throw exception for negative side length") {
            Square(Point(0.0, 0.0), -1.0)
        }
    }

    @Test
    fun testZeroSideLength() {
        assertFailsWith<IllegalArgumentException>("Should throw exception for zero side length") {
            Square(Point(0.0, 0.0), 0.0)
        }
    }

    @Test
    fun testInfiniteSideLength() {
        assertFailsWith<IllegalArgumentException>("Should throw exception for infinite side length") {
            Square(Point(0.0, 0.0), Double.POSITIVE_INFINITY)
        }
    }

    @Test
    fun testNaNSideLength() {
        assertFailsWith<IllegalArgumentException>("Should throw exception for NaN side length") {
            Square(Point(0.0, 0.0), Double.NaN)
        }
    }

    @Test
    fun testMultipleMoves() {
        val square = Square(Point(0.0, 0.0), 5.0)
        square.move(2.0, 2.0)
        square.move(1.0, 1.0)
        val attributes = square.getAttributes()
        assertEquals(3.0, attributes[0].getX())
        assertEquals(3.0, attributes[0].getY())
        assertEquals(8.0, attributes[1].getX())
        assertEquals(8.0, attributes[1].getY())
    }

    @Test
    fun testDimensionMaintenance() {
        val square = Square(Point(0.0, 0.0), 5.0)
        val attributes = square.getAttributes()
        val width = attributes[1].getX() - attributes[0].getX()
        val height = attributes[1].getY() - attributes[0].getY()
        assertEquals(width, height)
    }

    @Test
    fun testVerySmallSideLength() {
        val square = Square(Point(0.0, 0.0), Double.MIN_VALUE)
        assertEquals(Double.MIN_VALUE * Double.MIN_VALUE, square.getArea())
    }

    }

