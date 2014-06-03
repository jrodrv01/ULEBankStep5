package es.unileon.ulebank.springapp.repository;


import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.unileon.ulebank.fees.LinearFee;

@Repository(value = "feeDao")
public class JPAFeeDao implements FeeDao {

    private EntityManager em = null;

    /*
     * Sets the entity manager.
     */
    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Transactional(readOnly = true)
    @SuppressWarnings("unchecked")
    public ArrayList<LinearFee> getFeeList() {
        return (ArrayList<LinearFee>) em.createQuery("select p from LinearFee p order by p.id").getResultList();
    }

    @Transactional(readOnly = false)
    public void saveFee(LinearFee prod) {
        em.merge(prod);
    }

}