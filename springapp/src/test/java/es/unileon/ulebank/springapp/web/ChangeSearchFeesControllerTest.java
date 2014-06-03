package es.unileon.ulebank.springapp.web;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import es.unileon.ulebank.springapp.web.ChangeSearchFeesController;
import java.util.ArrayList;
import java.util.Map;


import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.springapp.repository.InMemoryFeeDao;
import es.unileon.ulebank.springapp.service.SimpleChangeFeeManager;


public class ChangeSearchFeesControllerTest {

	

	    @Test
	    public void testHandleRequestView() throws Exception{
	    	  SimpleChangeFeeManager spm = new SimpleChangeFeeManager();
	          spm.setFeeDao(new InMemoryFeeDao(new ArrayList<LinearFee>()));
	          ChangeSearchFeesController controller = new ChangeSearchFeesController();
	        //  controller.setChangeFeeManager(spm);
	          //controller.setProductManager(new SimpleProductManager());
	    	
	       
	        ModelAndView modelAndView = controller.handleRequest(null, null);		
	        assertEquals("hello.jsp", modelAndView.getViewName());
	        
	        assertNotNull(modelAndView.getModel());
	        Map modelMap = (Map) modelAndView.getModel().get("model");
	        String nowValue = (String) modelMap.get("now");
	        assertNotNull(nowValue);
	    }

	
}
