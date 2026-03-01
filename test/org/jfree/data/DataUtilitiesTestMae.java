package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class DataUtilitiesTestMae {

    // =====================================================
    // Helper Mock for Values2D
    // =====================================================

    class MockValues2D implements Values2D {

        private Number[][] data;

        public MockValues2D(Number[][] data) {
            this.data = data;
        }

        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            if (data.length == 0) {
                return 0;   // Branch now explicitly covered
            }
            return data[0].length;
        }

        public Number getValue(int row, int column) {
            return data[row][column];
        }
    }

    // =====================================================
    // Mock Coverage Tests
    // =====================================================

    // Covers getColumnCount() when data is empty
    @Test
    void testMockGetColumnCountEmpty() {
        Number[][] data = {};
        MockValues2D values = new MockValues2D(data);
        assertEquals(0, values.getColumnCount());
    }

    // Covers getColumnCount() when data is not empty
    @Test
    void testMockGetColumnCountNonEmpty() {
        Number[][] data = {{1.0, 2.0}};
        MockValues2D values = new MockValues2D(data);
        assertEquals(2, values.getColumnCount());
    }

    // =====================================================
    // calculateColumnTotal()
    // =====================================================

    @Test
    void testCalculateColumnTotalNormal() {
        Number[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        MockValues2D values = new MockValues2D(data);
        assertEquals(4.0, DataUtilities.calculateColumnTotal(values, 0), 0.0001);
    }

    @Test
    void testCalculateColumnTotalWithNullValue() {
        Number[][] data = {{1.0, null}, {3.0, 4.0}};
        MockValues2D values = new MockValues2D(data);
        assertEquals(4.0, DataUtilities.calculateColumnTotal(values, 1), 0.0001);
    }

    @Test
    void testCalculateColumnTotalEmptyData() {
        Number[][] data = {};
        MockValues2D values = new MockValues2D(data);
        assertEquals(0.0, DataUtilities.calculateColumnTotal(values, 0));
    }

    // =====================================================
    // calculateRowTotal()
    // =====================================================

    @Test
    void testCalculateRowTotalNormal() {
        Number[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        MockValues2D values = new MockValues2D(data);
        assertEquals(3.0, DataUtilities.calculateRowTotal(values, 0), 0.0001);
    }

    @Test
    void testCalculateRowTotalWithNullValue() {
        Number[][] data = {{1.0, null}};
        MockValues2D values = new MockValues2D(data);
        assertEquals(1.0, DataUtilities.calculateRowTotal(values, 0), 0.0001);
    }

    @Test
    void testCalculateRowTotalEmptyRow() {
        Number[][] data = {{}};
        MockValues2D values = new MockValues2D(data);
        assertEquals(0.0, DataUtilities.calculateRowTotal(values, 0));
    }

    // =====================================================
    // createNumberArray()
    // =====================================================

    @Test
    void testCreateNumberArrayNormal() {
        double[] data = {1.0, 2.0, 3.0};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals(3, result.length);
        assertEquals(2.0, result[1]);
    }

    // Fixed assertThrows format (single-line lambda for coverage)
    @Test
    void testCreateNumberArrayNull() {
        assertThrows(IllegalArgumentException.class,
                () -> DataUtilities.createNumberArray(null));
    }

    @Test
    void testCreateNumberArrayEmpty() {
        double[] data = {};
        Number[] result = DataUtilities.createNumberArray(data);
        assertEquals(0, result.length);
    }

    // =====================================================
    // createNumberArray2D()
    // =====================================================

    @Test
    void testCreateNumberArray2DNormal() {
        double[][] data = {{1.0, 2.0}, {3.0, 4.0}};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(2, result.length);
        assertEquals(4.0, result[1][1]);
    }

    @Test
    void testCreateNumberArray2DNull() {
        assertThrows(IllegalArgumentException.class,
                () -> DataUtilities.createNumberArray2D(null));
    }

    @Test
    void testCreateNumberArray2DEmpty() {
        double[][] data = {};
        Number[][] result = DataUtilities.createNumberArray2D(data);
        assertEquals(0, result.length);
    }

    // =====================================================
    // getCumulativePercentages()
    // =====================================================

    @Test
    void testGetCumulativePercentagesNormal() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 5.0);
        data.addValue("B", 9.0);
        data.addValue("C", 2.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(5.0 / 16.0, result.getValue(0).doubleValue(), 0.0001);
        assertEquals((5.0 + 9.0) / 16.0, result.getValue(1).doubleValue(), 0.0001);
        assertEquals(1.0, result.getValue(2).doubleValue(), 0.0001);
    }

    @Test
    void testGetCumulativePercentagesNull() {
        assertThrows(IllegalArgumentException.class,
                () -> DataUtilities.getCumulativePercentages(null));
    }

    @Test
    void testGetCumulativePercentagesWithNullValue() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 5.0);
        data.addValue("B", null);
        data.addValue("C", 5.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertEquals(0.5, result.getValue(0).doubleValue(), 0.0001);
    }

    @Test
    void testGetCumulativePercentagesTotalZero() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 0.0);
        data.addValue("B", 0.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertTrue(Double.isNaN(result.getValue(0).doubleValue()));
    }

    @Test
    void testGetCumulativePercentagesEmpty() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        KeyedValues result = DataUtilities.getCumulativePercentages(data);
        assertEquals(0, result.getItemCount());
    }

    @Test
    void testGetCumulativePercentagesZeroTotalWithNonNullValues() {
        DefaultKeyedValues data = new DefaultKeyedValues();
        data.addValue("A", 0.0);
        data.addValue("B", 0.0);
        data.addValue("C", 0.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(data);

        assertTrue(Double.isNaN(result.getValue(0).doubleValue()));
        assertTrue(Double.isNaN(result.getValue(1).doubleValue()));
        assertTrue(Double.isNaN(result.getValue(2).doubleValue()));
    }
    
 // NEW: covers case where data has rows but zero columns
    @Test
    void testMockGetColumnCountZeroColumns() {
        Number[][] data = {{}};
        MockValues2D values = new MockValues2D(data);
        assertEquals(0, values.getColumnCount());
    }
    
}
