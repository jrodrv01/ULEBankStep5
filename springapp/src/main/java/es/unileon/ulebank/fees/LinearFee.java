package es.unileon.ulebank.fees;

import java.io.Serializable;

import es.unileon.ulebank.springapp.domain.Fee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Simple fee that applies a minimum plus a percentage of the given amount.
 * 
 * @author roobre
 */


@Entity
@Table(name="fees") 
public class LinearFee implements FeeStrategy, Serializable {

	/**
	 * Fee to be applied as amount multiplicator, THUS ONE (a 2% fee is storead
	 * as 0.02).
	 */
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    } 
    
    @Id
    @Column(name = "idAccount")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAccount;
    
	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	private double fee;
	private String description;
	
	public double getFee() {
		return this.fee;
	}

	public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("Description: " + description + ";");
        buffer.append("Fee Percentage: " + fee);
        return buffer.toString();
    }

	/**
	 * Minimum value which is always paid.
	 */
	public LinearFee() {
		// TODO Auto-generated constructor stub
	}

	private double minimum;
	
	

	public LinearFee(double fee, double minimum) throws InvalidFeeException {
		if (fee < 0 || minimum < 0) {
			throw new InvalidFeeException();
		}

		this.fee = fee;
		this.minimum = minimum;
	}

	public double getFee(double value) {
		double total = this.fee * value;
		if (total < this.minimum) {
			total = this.minimum;
		}

		return total;

	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	public double getMinimum() {
		return minimum;
	}

	public void setMinimum(double minimum) {
		this.minimum = minimum;
	}

}
