package es.unileon.ulebank.payments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Transference Class
 * @author Rober dCR
 * @date 01/04/2014
 * @brief Aggregation about transfers between two accounts
 */
public class Transference {

	private Calendar transferDate;
	private String concept;
	private Transfer transfer;
	
	/**
	 * Class constructor
	 * @param transfer
	 * @param concept
	 */
	public Transference(Transfer transfer, String concept){
		this.setTransfer(transfer);
		this.setConcept(concept);
		this.setTransferDate(Calendar.getInstance());
	}
	
	/**
	 * Getter date of the transference in the format
	 * "dd/MM/yyyy HH:mm:ss"
	 * @return date
	 */
	public String getTransferDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(transferDate);
	}

	/**
	 * Setter of the transferDate
	 * @param transferDate
	 */
	public void setTransferDate(Calendar transferDate) {
		this.transferDate = transferDate;
	}

	/**
	 * Getter of the transfer concept
	 * @return concept
	 */
	public String getConcept() {
		return concept;
	}

	/**
	 * Setter of the transfer concept
	 * @param concept
	 */
	public void setConcept(String concept) {
		this.concept = concept;
	}

	/**
	 * Getter of the transfer
	 * @return transfer
	 */
	public Transfer getTransfer() {
		return transfer;
	}

	/**
	 * Setter of the transfer
	 * @param transfer
	 */
	public void setTransfer(Transfer transfer) {
		this.transfer = transfer;
	}
}
