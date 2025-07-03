package shapes

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class TriangleTest {

    // Area calculation tests
    @Test
    fun testTriangleArea() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(3.0, 0.0),
            Point(0.0, 4.0)
        )
        assertEquals(6.0, triangle.getArea())
    }

    @Test
    fun testTriangleAreaEquilateral() {
        // Equilateral triangle with side length 2
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(2.0, 0.0),
            Point(1.0, Math.sqrt(3.0))
        )
        assertEquals(Math.sqrt(3.0), triangle.getArea(), 0.001)
    }

    @Test
    fun testTriangleAreaWithNegativeCoordinates() {
        val triangle = Triangle(
            Point(-2.0, -1.0),
            Point(1.0, -1.0),
            Point(-2.0, 2.0)
        )
        assertEquals(4.5, triangle.getArea(), 0.001)
    }

    @Test
    fun testTriangleAreaIsosceles() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(4.0, 0.0),
            Point(2.0, 3.0)
        )
        assertEquals(6.0, triangle.getArea())
    }

    @Test
    fun testTriangleAreaVerySmall() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(0.001, 0.0),
            Point(0.0, 0.001)
        )
        assertEquals(0.0000005, triangle.getArea(), 0.0000001)
    }

    @Test
    fun testTriangleAreaOrderIndependence() {
        // Same triangle with vertices in different order should have same area
        val area1 = Triangle(Point(0.0, 0.0), Point(3.0, 0.0), Point(0.0, 4.0)).getArea()
        val area2 = Triangle(Point(3.0, 0.0), Point(0.0, 4.0), Point(0.0, 0.0)).getArea()
        val area3 = Triangle(Point(0.0, 4.0), Point(0.0, 0.0), Point(3.0, 0.0)).getArea()

        assertEquals(area1, area2, 0.001)
        assertEquals(area1, area3, 0.001)
    }

    // Movement tests
    @Test
    fun testTriangleMove() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(3.0, 0.0),
            Point(0.0, 4.0)
        )
        triangle.move(2.0, 2.0)
        val attributes = triangle.getAttributes()
        assertEquals(2.0, attributes[0].getX())
        assertEquals(2.0, attributes[0].getY())
        assertEquals(5.0, attributes[1].getX())
        assertEquals(2.0, attributes[1].getY())
        assertEquals(2.0, attributes[2].getX())
        assertEquals(6.0, attributes[2].getY())
    }

    @Test
    fun testTriangleMoveWithNegativeDeltas() {
        val triangle = Triangle(
            Point(5.0, 5.0),
            Point(8.0, 5.0),
            Point(5.0, 9.0)
        )
        triangle.move(-3.0, -2.0)
        val attributes = triangle.getAttributes()
        assertEquals(2.0, attributes[0].getX())
        assertEquals(3.0, attributes[0].getY())
        assertEquals(5.0, attributes[1].getX())
        assertEquals(3.0, attributes[1].getY())
        assertEquals(2.0, attributes[2].getX())
        assertEquals(7.0, attributes[2].getY())
    }

    @Test
    fun testMultipleMoves() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(3.0, 0.0),
            Point(0.0, 4.0)
        )
        triangle.move(1.0, 1.0)
        triangle.move(2.0, 3.0)
        val attributes = triangle.getAttributes()
        assertEquals(3.0, attributes[0].getX())
        assertEquals(4.0, attributes[0].getY())
        assertEquals(6.0, attributes[1].getX())
        assertEquals(4.0, attributes[1].getY())
        assertEquals(3.0, attributes[2].getX())
        assertEquals(8.0, attributes[2].getY())
    }

    @Test
    fun testMovePreservesArea() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(3.0, 0.0),
            Point(0.0, 4.0)
        )
        val originalArea = triangle.getArea()
        triangle.move(10.0, -5.0)
        assertEquals(originalArea, triangle.getArea(), 0.001)
    }

    // Attribute access tests
    @Test
    fun testGetAttributes() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(3.0, 0.0),
            Point(0.0, 4.0)
        )
        val attributes = triangle.getAttributes()
        assertEquals(3, attributes.size)
        assertEquals(0.0, attributes[0].getX())
        assertEquals(0.0, attributes[0].getY())
        assertEquals(3.0, attributes[1].getX())
        assertEquals(0.0, attributes[1].getY())
        assertEquals(0.0, attributes[2].getX())
        assertEquals(4.0, attributes[2].getY())
    }

    @Test
    fun testGetAttributesReturnsClones() {
        val triangle = Triangle(
            Point(1.0, 1.0),
            Point(4.0, 1.0),
            Point(1.0, 5.0)
        )
        val attributes = triangle.getAttributes()

        // Modify the returned points
        attributes[0].move(10.0, 10.0)

        // Original triangle should be unchanged
        val newAttributes = triangle.getAttributes()
        assertEquals(1.0, newAttributes[0].getX())
        assertEquals(1.0, newAttributes[0].getY())
    }

    // Validation tests - collinear points
    @Test
    fun testCollinearPointsHorizontal() {
        assertFailsWith<IllegalArgumentException> {
            Triangle(
                Point(0.0, 0.0),
                Point(1.0, 0.0),
                Point(2.0, 0.0)
            )
        }
    }

    @Test
    fun testCollinearPointsVertical() {
        assertFailsWith<IllegalArgumentException> {
            Triangle(
                Point(0.0, 0.0),
                Point(0.0, 1.0),
                Point(0.0, 2.0)
            )
        }
    }

    @Test
    fun testCollinearPointsDiagonal() {
        assertFailsWith<IllegalArgumentException> {
            Triangle(
                Point(0.0, 0.0),
                Point(1.0, 1.0),
                Point(2.0, 2.0)
            )
        }
    }

    @Test
    fun testCollinearPointsWithNegativeSlope() {
        assertFailsWith<IllegalArgumentException> {
            Triangle(
                Point(0.0, 2.0),
                Point(1.0, 1.0),
                Point(2.0, 0.0)
            )
        }
    }

    @Test
    fun testIdenticalPoints() {
        assertFailsWith<IllegalArgumentException> {
            Triangle(
                Point(1.0, 1.0),
                Point(1.0, 1.0),
                Point(1.0, 1.0)
            )
        }
    }

    @Test
    fun testTwoIdenticalPoints() {
        assertFailsWith<IllegalArgumentException> {
            Triangle(
                Point(0.0, 0.0),
                Point(1.0, 1.0),
                Point(0.0, 0.0)
            )
        }
    }

    // Edge cases and special triangles
    @Test
    fun testTriangleWithDecimalCoordinates() {
        val triangle = Triangle(
            Point(0.5, 0.5),
            Point(2.7, 0.5),
            Point(0.5, 3.1)
        )
        assertEquals(2.86, triangle.getArea(), 0.001)
    }

    @Test
    fun testTriangleAtOrigin() {
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(1.0, 0.0),
            Point(0.0, 1.0)
        )
        assertEquals(0.5, triangle.getArea())
    }

    @Test
    fun testTriangleInDifferentQuadrants() {
        val triangle = Triangle(
            Point(-1.0, -1.0),
            Point(1.0, -1.0),
            Point(0.0, 1.0)
        )
        assertEquals(2.0, triangle.getArea())
    }

    // Additional validation edge cases
    @Test
    fun testNearlyCollinearPoints() {
        // Points that are very close to being collinear but not quite
        val triangle = Triangle(
            Point(0.0, 0.0),
            Point(1.0, 0.0),
            Point(0.5, 0.0001)
        )
        // Should not throw an exception and should have a very small area
        assertEquals(0.00005, triangle.getArea(), 0.00001)
    }

    @Test
    fun testLargeCoordinates() {
        val triangle = Triangle(
            Point(1000.0, 1000.0),
            Point(1003.0, 1000.0),
            Point(1000.0, 1004.0)
        )
        assertEquals(6.0, triangle.getArea())
    }
}
