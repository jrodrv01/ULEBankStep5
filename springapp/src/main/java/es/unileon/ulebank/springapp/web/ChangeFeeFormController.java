package es.unileon.ulebank.springapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.springapp.service.ChangeFee;
import es.unileon.ulebank.springapp.service.ChangeFeeManager;

@Controller
@RequestMapping(value="/changefee.htm")
public class ChangeFeeFormController {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired
    private ChangeFeeManager feeManager;
    
  

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(@Valid ChangeFee changeFee, BindingResult result)
    {
    	
    	
        if (result.hasErrors()) {
            return "changefee";
        }
        
        LinearFee comsion = new LinearFee();
        
        comsion.setFee(changeFee.getPercentage());
        
		
        double currentFee = changeFee.getPercentage();
        logger.info("Decrease fee by " + currentFee);
        
        logger.info("Decrease on fee number " + changeFee.getIndex() );
        feeManager.decreaseFee(currentFee, changeFee.getIndex());

        return "redirect:/ShareBuySellView.htm";
    }

    @RequestMapping(method = RequestMethod.GET)
    protected ChangeFee formBackingObject(HttpServletRequest request) throws ServletException {
        ChangeFee decreaseFee = new ChangeFee();
        decreaseFee.setPercentage(1);
        decreaseFee.setIndex(1);
        return decreaseFee;
    }

    public void setProductManager(ChangeFeeManager feeManager) {
        this.feeManager = feeManager;
    }

    public ChangeFeeManager getChangeFeeManager() {
        return feeManager;
    }
}