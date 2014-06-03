package es.unileon.ulebank.springapp.repository;

import java.util.List;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import es.unileon.ulebank.fees.LinearFee;;


public class JPAFeeDaoTests {

    private ApplicationContext context;
    private FeeDao feeDao;

    @Before
    public void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:test-context.xml");
        feeDao = (FeeDao) context.getBean("feeDao");
    }

    @Test
    public void testGetProductList() {
        List<LinearFee> fees = feeDao.getFeeList();
        assertEquals(fees.size(), 3, 0);	   
    }

    @Test
    public void testSaveProduct() {
    	List<LinearFee> fees = feeDao.getFeeList();

        LinearFee p = fees.get(0);
        Double commission = p.getFee();
        p.setFee(10.50);
        feeDao.saveFee(p);

        List<LinearFee> updatedFees = feeDao.getFeeList();
        LinearFee p2 = updatedFees.get(0);
        assertEquals(p2.getFee(), 200.12, 0);

        p2.setFee(commission);
        feeDao.saveFee(p2);
    }
}