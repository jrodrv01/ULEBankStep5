package es.unileon.ulebank.springapp.service;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.unileon.ulebank.fees.LinearFee;
import es.unileon.ulebank.springapp.domain.Fee;

public interface ChangeFeeManager extends Serializable {

    public void decreaseFee(double percentage, int index);
    
    public ArrayList<LinearFee> sharesFee();

}
