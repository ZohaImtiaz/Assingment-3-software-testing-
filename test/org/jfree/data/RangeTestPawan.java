package org.jfree.data;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RangeTestPawan {

    private Range range1;
    private Range range2;

    @BeforeEach
    void setup() {
      range1 = new Range(2, 6);
      range2 = new Range(4, 8);
    }



  
  // equals() Tests

  @Test
  void testEqualsSameValues(){
    Range sameRange = new Range(2, 6);
    assertTrue(range1.equals(sameRange));
  }

  
  @Test
  void testEqualsDifferentLower() {
    Range different = new Range(3, 6);
    assertFalse(range1.equals(different));
  }

  
  @Test
  void testEqualsDifferentUpper() {
    Range different = new Range(2, 7);
    assertFalse(range1.equals(different));
  }

  
  @Test
  void testEqualsNull() {
    assertFalse(range1.equals(null));
  }


  @Test
  void testEqualsDifferentObjectType() {
    assertFalse(range1.equals("NotARange"));
  }


    
    
    
  //combine() Tests

    @Test
    void testCombineTwoValidRanges() {
        Range combined = Range.combine(range1, range2);
        assertEquals(2.0, combined.getLowerBound());
        assertEquals(8.0, combined.getUpperBound());
    }


    @Test
    void testCombineFirstNull() {
        Range combined = Range.combine(null, range2);
        assertEquals(range2, combined);
    }


    @Test
    void testCombineSecondNull() {
        Range combined = Range.combine(range1, null);
        assertEquals(range1, combined);
    }


    @Test
    void testCombineBothNull() {
        Range combined = Range.combine(null, null);
        assertNull(combined);
    }


    

    //toString() Test

    @Test
    void testToString() {
        assertEquals("Range[2.0,6.0]", range1.toString());
    }


    


    //expand() Tests
    
    @Test
    void testExpandWithPositiveMargins() {
        Range expanded = Range.expand(range1, 0.5, 0.5);
        assertEquals(0.0, expanded.getLowerBound());
        assertEquals(8.0, expanded.getUpperBound());
    }


    @Test
    void testExpandWithZeroMargins() {
        Range expanded = Range.expand(range1, 0.0, 0.0);
        assertEquals(2.0, expanded.getLowerBound());
        assertEquals(6.0, expanded.getUpperBound());
     }


    @Test
    void testExpandNegativeMargins() {
        Range expanded = Range.expand(range1, -0.25, -0.25);
        assertEquals(3.0, expanded.getLowerBound());
        assertEquals(5.0, expanded.getUpperBound());
    }


    @Test
    void testExpandNullRange() {
        assertThrows(IllegalArgumentException.class, () -> {
            Range.expand(null, 0.1, 0.1);
        });

    }
}
    

  

  
