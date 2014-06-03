package es.unileon.ulebank.springapp.repository;

import java.util.ArrayList;

import es.unileon.ulebank.fees.LinearFee;;

public class InMemoryFeeDao implements FeeDao {

    private ArrayList<LinearFee> feeList;

    public InMemoryFeeDao(ArrayList<LinearFee> feeList) {
        this.feeList = feeList;
    }

    public ArrayList<LinearFee> getFeeList() {
        return feeList;
    }

    public void saveFee(LinearFee prod) {
    }

}