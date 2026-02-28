package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.jfree.data.Range;
import org.junit.Test;

public class RangeTestPalak {

    private static void assertRangeEquals(double lower, double upper, Range r) {
        assertNotNull(r);
        assertEquals(lower, r.getLowerBound(), 1e-9);
        assertEquals(upper, r.getUpperBound(), 1e-9);
    }

    @Test
    void expandToInclude_nullRange_returnsPointRange() {
        Range result = Range.expandToInclude(null, 3.0);
        assertRangeEquals(3.0, 3.0, result);
        assertTrue(result.contains(3.0));
    }

    @Test
    void expandToInclude_valueInsideRange_noChange() {
        Range base = new Range(1.0, 5.0);
        Range result = Range.expandToInclude(base, 3.0);
        assertRangeEquals(1.0, 5.0, result);
        assertTrue(result.contains(3.0));
    }

    @Test
    void expandToInclude_valueBelowLower_expandsLower() {
        Range base = new Range(1.0, 5.0);
        Range result = Range.expandToInclude(base, -2.0);
        assertRangeEquals(-2.0, 5.0, result);
        assertTrue(result.contains(-2.0));
        assertTrue(result.contains(1.0));
        assertTrue(result.contains(5.0));
    }

    @Test
    void expandToInclude_valueAboveUpper_expandsUpper() {
        Range base = new Range(1.0, 5.0);
        Range result = Range.expandToInclude(base, 10.0);
        assertRangeEquals(1.0, 10.0, result);
        assertTrue(result.contains(10.0));
        assertTrue(result.contains(1.0));
        assertTrue(result.contains(5.0));
    }

    @Test
    void expandToInclude_valueEqualsLower_noExpansion() {
        Range base = new Range(1.0, 5.0);
        Range result = Range.expandToInclude(base, 1.0);
        assertRangeEquals(1.0, 5.0, result);
    }

    @Test
    void expandToInclude_valueEqualsUpper_noExpansion() {
        Range base = new Range(1.0, 5.0);
        Range result = Range.expandToInclude(base, 5.0);
        assertRangeEquals(1.0, 5.0, result);
    }
}