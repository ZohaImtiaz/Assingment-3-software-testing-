package org.jfree.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DataUtilitiesTest {

    private Values2D value;

    @BeforeEach
    void setUp() throws Exception {
        // Create mock object representing a 3x4 2D table
        value = mock(Values2D.class);

        // Define behavior for the test
        when(value.getColumnCount()).thenReturn(4);
        when(value.getRowCount()).thenReturn(3);
        when(value.getValue(0, 2)).thenReturn(5);
        when(value.getValue(1, 2)).thenReturn(7);
        when(value.getValue(2, 2)).thenReturn(1);
    }

    @Test
    void testCalculateColumnTotal() {
        // Call the method under test
        double total = DataUtilities.calculateColumnTotal(value, 2);

        // Verify the result is correct
        assertEquals(13.0, total, 0.0001);

        // Verify that getValue() was called exactly 3 times
        verify(value, times(3)).getValue(anyInt(), eq(2));
    }
}
