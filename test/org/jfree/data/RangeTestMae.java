package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RangeTestMae {

    // getCentralValue() Tests

    @Test
    void testGetCentralValueTypicalRange() {
        Range r = new Range(2.0, 10.0);
        assertEquals(6.0, r.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValueNegativeRange() {
        Range r = new Range(-10.0, -2.0);
        assertEquals(-6.0, r.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValueMixedRange() {
        Range r = new Range(-4.0, 6.0);
        assertEquals(1.0, r.getCentralValue(), 0.0001);
    }

    @Test
    void testGetCentralValueSingleValue() {
        Range r = new Range(5.0, 5.0);
        assertEquals(5.0, r.getCentralValue(), 0.0001);
    }

    // contains(double) Tests
    @Test
    void testContainsInside() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.contains(10.0));
    }

    @Test
    void testContainsLowerBoundary() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.contains(5.0));
    }

    @Test
    void testContainsUpperBoundary() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.contains(15.0));
    }

    @Test
    void testContainsBelowRange() {
        Range r = new Range(5.0, 15.0);
        assertFalse(r.contains(4.0));
    }

    @Test
    void testContainsAboveRange() {
        Range r = new Range(5.0, 15.0);
        assertFalse(r.contains(16.0));
    }

    // intersects(double, double)

    @Test
    void testIntersectsFullyInside() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.intersects(7.0, 10.0));
    }

    @Test
    void testIntersectsLeftOverlap() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.intersects(0.0, 7.0));
    }

    @Test
    void testIntersectsRightOverlap() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.intersects(10.0, 20.0));
    }

    @Test
    void testIntersectsExactMatch() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.intersects(5.0, 15.0));
    }

    @Test
    void testIntersectsTouchingBoundary() {
        Range r = new Range(5.0, 15.0);
        assertTrue(r.intersects(15.0, 20.0));
    }

    @Test
    void testIntersectsCompletelyLeft() {
        Range r = new Range(5.0, 15.0);
        assertFalse(r.intersects(0.0, 4.0));
    }

    @Test
    void testIntersectsCompletelyRight() {
        Range r = new Range(5.0, 15.0);
        assertFalse(r.intersects(16.0, 20.0));
    }

    // constrain(double)
    @Test
    void testConstrainInside() {
        Range r = new Range(5.0, 15.0);
        assertEquals(10.0, r.constrain(10.0), 0.0001);
    }

    @Test
    void testConstrainLowerBoundary() {
        Range r = new Range(5.0, 15.0);
        assertEquals(5.0, r.constrain(5.0), 0.0001);
    }

    @Test
    void testConstrainUpperBoundary() {
        Range r = new Range(5.0, 15.0);
        assertEquals(15.0, r.constrain(15.0), 0.0001);
    }

    @Test
    void testConstrainBelowRange() {
        Range r = new Range(5.0, 15.0);
        assertEquals(5.0, r.constrain(2.0), 0.0001);
    }

    @Test
    void testConstrainAboveRange() {
        Range r = new Range(5.0, 15.0);
        assertEquals(15.0, r.constrain(20.0), 0.0001);
    }
}


