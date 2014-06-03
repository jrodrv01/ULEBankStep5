package es.unileon.ulebank.springapp.service;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.springapp.repository.InMemoryFeeDao;
import es.unileon.ulebank.springapp.repository.FeeDao;

public class SimpleChangeFeeManagerTests {

    private SimpleChangeFeeManager feeManager;
    
    private ArrayList<LinearFee> products;
    
    private static int PRODUCT_COUNT = 2;
    
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String CHAIR_DESCRIPTION = "Chair";
    
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10); 
    
    @Before
    public void setUp() throws Exception {
        feeManager = new SimpleChangeFeeManager();
        products = new ArrayList<LinearFee>();
        
        // stub up a list of products
        LinearFee product = new LinearFee();
        product.setDescription("Chair");
        product.setFee(CHAIR_PRICE);
        products.add(product);
        
        product = new LinearFee();
        product.setDescription("Table");
        product.setFee(TABLE_PRICE);
        products.add(product);
        
        FeeDao feeDao = new InMemoryFeeDao(products);
        feeManager.setFeeDao(feeDao);
        //productManager.setProducts(products);
        feeManager.setShareFees(products);

    }

    @Test
    public void testGetProductsWithNoProducts() {
        feeManager = new SimpleChangeFeeManager();
        feeManager.setFeeDao(new InMemoryFeeDao(null));
        assertNull(feeManager.getShareFees());
    }

    /**
     * 
     */
    @Test
    public void testGetProducts() {
        /*List<Fee> products = SimpleChangeFeeManager.getProducts();
        assertNotNull(products);        
        assertEquals(PRODUCT_COUNT, SimpleChangeFeeManager.getProducts().size());*/
    
        LinearFee product = products.get(0);
        assertEquals(CHAIR_DESCRIPTION, product.getDescription());
       // assertEquals(CHAIR_PRICE, product.getFee());
        
        product = products.get(1);
        assertEquals(TABLE_DESCRIPTION, product.getDescription());
       // assertEquals(TABLE_PRICE, product.getFee());      
    }
    @Test
    public void testIncreasePriceWithEmptyListOfProducts() {
        try {
            feeManager = new SimpleChangeFeeManager();
            feeManager.setFeeDao(new InMemoryFeeDao(new ArrayList<LinearFee>()));
            //productManager.setProducts(new ArrayList<Product>());
            feeManager.decreaseFee(0, 0);
            }
        catch(Exception ex) {
        	fail("Products list is empty.");
        }           
    }
    
    @Test
    public void testIncreasePriceWithPositivePercentage() {
        feeManager.decreaseFee(0, 0);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;
        
        ArrayList<LinearFee> products = (ArrayList<LinearFee>) feeManager.getShareFees();      
        LinearFee product = products.get(0);
        assertEquals(expectedChairPriceWithIncrease, product.getFee(), 0);
        
        product = products.get(1);      
        assertEquals(expectedTablePriceWithIncrease, product.getFee(), 0);       
    }
}

