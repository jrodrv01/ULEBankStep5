package es.unileon.ulebank.springapp.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import es.unileon.ulebank.fees.LinearFee;

import es.unileon.ulebank.springapp.repository.FeeDao;

@Component
public class SimpleChangeFeeManager implements ChangeFeeManager {
	
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private FeeDao feeDao;
    
    private ArrayList<LinearFee> shareFees;
    
    public void setFeeDao(FeeDao feeDao) {
        this.feeDao = feeDao;
    }

    public void decreaseFee(double percentage, int index) {
    	
    	ArrayList<LinearFee> shareFees = feeDao.getFeeList();
    	
    	if (shareFees != null) {
            LinearFee fee = shareFees.get(index-1);
                double newFee = fee.getFee()* 
                                    (100 - percentage)/100;
                fee.setFee(newFee);
                feeDao.saveFee(fee);
        }        
	}
	
    public void setShareFees (ArrayList<LinearFee> shareFees) {     	
    	this.shareFees = shareFees;
    }
    
    public List<LinearFee> getShareFees() {
    	return feeDao.getFeeList();      
    }

	
	public ArrayList<LinearFee> sharesFee() {
		return this.shareFees;
	}
}