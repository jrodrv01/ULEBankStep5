package es.unileon.ulebank.springapp.repository;


import java.util.ArrayList;

import es.unileon.ulebank.fees.LinearFee;

public interface FeeDao {

    public ArrayList<LinearFee> getFeeList();

    public void saveFee(LinearFee prod);

}