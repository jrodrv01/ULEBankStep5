package es.unileon.ulebank.springapp.domain;

import java.io.Serializable;

public class Fee implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;
    private Double feePercentage;
    
    public Double getFeePercentage() {
		return feePercentage;
	}

	public void setFeePercentage(Double feePercentage) {
		this.feePercentage = feePercentage;
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
        buffer.append("Fee Percentage: " + feePercentage);
        return buffer.toString();
    }
}