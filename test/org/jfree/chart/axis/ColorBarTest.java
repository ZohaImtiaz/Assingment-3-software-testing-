package org.jfree.chart.axis;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.ContourPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.ui.ColorPalette;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class ColorBarTest {

    private ColorBar colorBar;

    @BeforeEach
    void setUp() {
        colorBar = new ColorBar("Test ColorBar");
    }

    @Test
    void testConstructorNotNull() {
        assertNotNull(colorBar, "ColorBar object should not be null");
    }

    @Test
    void testSetGetAxis() {
        ValueAxis axis = mock(ValueAxis.class);
        colorBar.setAxis(axis);
        assertEquals(axis, colorBar.getAxis(), "Axis should be set and retrieved correctly");
    }

    @Test
    void testSetGetColorPalette() {
        ColorPalette palette = mock(ColorPalette.class);
        colorBar.setColorPalette(palette);
        assertEquals(palette, colorBar.getColorPalette(), "Palette should be set and retrieved correctly");
    }

    @Test
    void testSetMaximumMinimumValues() {
        colorBar.setMaximumValue(100.0);
        colorBar.setMinimumValue(10.0);
        // Use getPaint to indirectly test? Otherwise just ensure no exception
        assertDoesNotThrow(() -> colorBar.getPaint(50.0));
    }

    @Test
    void testAutoAdjustRange() {
        assertDoesNotThrow(() -> colorBar.autoAdjustRange());
    }

    @Test
    void testConfigure() {
        ContourPlot plot = mock(ContourPlot.class);
        assertDoesNotThrow(() -> colorBar.configure(plot));
    }

    @Test
    void testDraw() {
        Graphics2D g2 = mock(Graphics2D.class);
        Rectangle2D plotArea = new Rectangle2D.Double(0,0,100,50);
        Rectangle2D dataArea = new Rectangle2D.Double(0,0,100,50);
        Rectangle2D reservedArea = new Rectangle2D.Double(0,0,100,50);
        assertDoesNotThrow(() -> colorBar.draw(g2, plotArea, dataArea, reservedArea, RectangleEdge.BOTTOM));
    }

    @Test
    void testDrawColorBar() {
        Graphics2D g2 = mock(Graphics2D.class);
        Rectangle2D area = new Rectangle2D.Double(0,0,100,50);
        assertDoesNotThrow(() -> colorBar.drawColorBar(g2, area, RectangleEdge.BOTTOM));
    }

    @Test
    void testReserveSpace() {
        Graphics2D g2 = mock(Graphics2D.class);
        Plot plot = mock(Plot.class);
        Rectangle2D plotArea = new Rectangle2D.Double(0,0,100,50);
        Rectangle2D dataArea = new Rectangle2D.Double(0,0,100,50);
        AxisSpace space = new AxisSpace();
        assertDoesNotThrow(() -> colorBar.reserveSpace(g2, plot, plotArea, dataArea, RectangleEdge.BOTTOM, space));
    }

    @Test
    void testEqualsAndHashCode() {
        ColorBar bar2 = new ColorBar("Test ColorBar");
        assertTrue(colorBar.equals(bar2), "ColorBars with same label should be equal");
        assertEquals(colorBar.hashCode(), bar2.hashCode(), "HashCodes should match for equal objects");
    }

    @Test
    void testClone() throws CloneNotSupportedException {
        ColorBar cloned = (ColorBar) colorBar.clone();
        assertNotNull(cloned, "Cloned ColorBar should not be null");
        assertTrue(cloned.equals(colorBar), "Cloned ColorBar should be equal to original");
    }
}
