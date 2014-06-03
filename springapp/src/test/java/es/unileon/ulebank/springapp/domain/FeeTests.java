package es.unileon.ulebank.springapp.domain;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FeeTests {

    private Fee fee;

    @Before
    public void setUp() throws Exception {
        fee = new Fee();
    }

    @Test
    public void testSetAndGetDescription() {
        String testDescription = "aDescription";
        assertNull(fee.getDescription());
        fee.setDescription(testDescription);
        assertEquals(testDescription, fee.getDescription());
    }

    @Test
    public void testSetAndGetPrice() {
        double testPrice = 100.00;
        assertEquals(0, 0, 0);    
        fee.setFeePercentage(testPrice);
        assertEquals(testPrice, fee.getFeePercentage(), 0);
    }

}