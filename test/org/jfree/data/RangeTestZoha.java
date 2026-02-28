package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTest {

    private Range exampleRange;

    @BeforeEach
    void setUp() {
        // Initialize exampleRange before each test
        exampleRange = new Range(-1, 1);
    }

    @Test
    void testGetCentralValue() {
        // Check that the central value of (-1, 1) is 0
        assertEquals(
            0.0,                   // expected
            exampleRange.getCentralValue(), // actual
            0.0001,                // delta: allowable error for floating point
            "The central value of (-1,1) should be 0"
        );
    }
    // Constructor valid case
    @Test
    public void testConstructorValidRange() {
        Range r = new Range(1.0, 5.0);
        assertEquals(1.0, r.getLowerBound(), 0.0001);
        assertEquals(5.0, r.getUpperBound(), 0.0001);
    }

    // Constructor boundary case lower = upper
    @Test
    public void testConstructorEqualBounds() {
        Range r = new Range(3.0, 3.0);
        assertEquals(3.0, r.getLowerBound(), 0.0001);
        assertEquals(3.0, r.getUpperBound(), 0.0001);
    }

    // Constructor invalid case lower > upper
    @Test
    void testConstructorInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Range(5.0, 1.0);
        });
    }


    // getLowerBound normal case
    @Test
    public void testGetLowerBound() {
        Range r = new Range(2.0, 6.0);
        assertEquals(2.0, r.getLowerBound(), 0.0001);
    }

    // getUpperBound normal case
    @Test
    public void testGetUpperBound() {
        Range r = new Range(2.0, 6.0);
        assertEquals(6.0, r.getUpperBound(), 0.0001);
    }

    // getLength normal range
    @Test
    public void testGetLengthNormalRange() {
        Range r = new Range(2.0, 6.0);
        assertEquals(4.0, r.getLength(), 0.0001);
    }

    // getLength zero range
    @Test
    public void testGetLengthZero() {
        Range r = new Range(3.0, 3.0);
        assertEquals(0.0, r.getLength(), 0.0001);
    }

    // getLength negative values
    @Test
    public void testGetLengthNegativeRange() {
        Range r = new Range(-5.0, -1.0);
        assertEquals(4.0, r.getLength(), 0.0001);
    }
}


