package shapes

import kotlin.test.Test
import kotlin.test.assertEquals

class PointTest {
    companion object {
        private const val INITIAL_X = 0.0
        private const val INITIAL_Y = 0.0
        private const val DELTA_X = 10.0
        private const val DELTA_Y = 5.0
    }

    // Initialization Tests
    @Test
    fun testInitialization_coordinatesMatchInitialValues() {
        val point = Point(3.0, 4.0)
        assertEquals(3.0, point.getX())
        assertEquals(4.0, point.getY())
    }

    // Movement Tests
    @Test
    fun testMovement_xCoordinateUpdatedCorrectly() {
        val point = Point(INITIAL_X, INITIAL_Y)
        point.move(DELTA_X, DELTA_Y)
        assertEquals(INITIAL_X + DELTA_X, point.getX())
    }

    @Test
    fun testMovement_yCoordinateUpdatedCorrectly() {
        val point = Point(INITIAL_X, INITIAL_Y)
        point.move(DELTA_X, DELTA_Y)
        assertEquals(INITIAL_Y + DELTA_Y, point.getY())
    }

    @Test
    fun testMovement_multipleMovesUpdateCumulatively() {
        val point = Point(INITIAL_X, INITIAL_Y)
        point.move(3.0, 4.0)
        point.move(1.0, 2.0)
        assertEquals(INITIAL_X + 4.0, point.getX())
        assertEquals(INITIAL_Y + 6.0, point.getY())
    }

    // Clone Tests
    @Test
    fun testClone_coordinatesMatchOriginal() {
        val original = Point(3.0, 4.0)
        val cloned = original.clone()
        assertEquals(original.getX(), cloned.getX())
        assertEquals(original.getY(), cloned.getY())
    }

    @Test
    fun testClone_originalModifiedCloneUnchanged() {
        val original = Point(3.0, 4.0)
        val cloned = original.clone()
        original.move(1.0, 1.0)
        assertEquals(3.0, cloned.getX())
        assertEquals(4.0, cloned.getY())
    }

    @Test
    fun testClone_cloneModifiedOriginalUnchanged() {
        val original = Point(3.0, 4.0)
        val cloned = original.clone()
        cloned.move(1.0, 1.0)
        assertEquals(3.0, original.getX())
        assertEquals(4.0, original.getY())
    }

    @Test
    fun testClone_zeroCoordinates() {
        val original = Point(0.0, 0.0)
        val cloned = original.clone()
        assertEquals(original.getX(), cloned.getX())
        assertEquals(original.getY(), cloned.getY())
    }

    @Test
    fun testClone_negativeCoordinates() {
        val original = Point(-3.0, -4.0)
        val cloned = original.clone()
        assertEquals(original.getX(), cloned.getX())
        assertEquals(original.getY(), cloned.getY())
    }

    @Test
    fun testClone_sequentialCloning() {
        val original = Point(1.0, 1.0)
        val firstClone = original.clone()
        val secondClone = firstClone.clone()
        assertEquals(original.getX(), secondClone.getX())
        assertEquals(original.getY(), secondClone.getY())
    }
}
