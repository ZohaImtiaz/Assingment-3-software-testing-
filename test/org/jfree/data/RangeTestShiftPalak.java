package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import java.security.InvalidParameterException;
import org.junit.jupiter.api.Test;

public class RangeTestShiftPalak {

    private static void assertRangeEquals(double lower, double upper, Range r) {
        assertNotNull(r, "Returned range should not be null");
        assertEquals(lower, r.getLowerBound(), 1e-9, "Lower bound mismatch");
        assertEquals(upper, r.getUpperBound(), 1e-9, "Upper bound mismatch");
    }

    // ---------- Null base behavior ----------

    @Test
    void shift_nullBase_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Range.shift(null, 1.0));
    }

    @Test
    void shift_nullBaseWithFlag_throwsInvalidParameterException() {
        assertThrows(InvalidParameterException.class, () -> Range.shift(null, 1.0, true));
    }

    // ---------- Overload equivalence ----------

    @Test
    void shift_twoArg_isEquivalentTo_threeArg_withAllowZeroCrossingFalse() {
        Range base = new Range(1.0, 3.0);
        Range r1 = Range.shift(base, 2.0);
        Range r2 = Range.shift(base, 2.0, false);

        assertRangeEquals(r2.getLowerBound(), r2.getUpperBound(), r1);
    }

    // ---------- No crossing cases (normal shift) ----------

    @Test
    void shift_allowZeroCrossingFalse_noCrossing_shiftsNormally() {
        Range base = new Range(1.0, 5.0);
        Range result = Range.shift(base, 2.0, false);
        assertRangeEquals(3.0, 7.0, result);
    }

    @Test
    void shift_allowZeroCrossingTrue_noCrossing_shiftsNormally() {
        Range base = new Range(-10.0, -2.0);
        Range result = Range.shift(base, -3.0, true);
        assertRangeEquals(-13.0, -5.0, result);
    }

    @Test
    void shift_deltaZero_returnsSameRange_evenWhenNoCrossingAllowed() {
        Range base = new Range(-4.0, 6.0);
        Range result = Range.shift(base, 0.0, false);
        assertRangeEquals(-4.0, 6.0, result);
    }
}
