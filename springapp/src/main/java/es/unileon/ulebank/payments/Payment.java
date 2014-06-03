package es.unileon.ulebank.payments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.unileon.ulebank.exceptions.PaymentHandlerException;
import es.unileon.ulebank.handler.Handler;
import es.unileon.ulebank.handler.PaymentHandler;

//TODO - Cambiar a Transaction (Trabajo de Control)

/**
 * Payment Class
 * @author Rober dCR
 * @date 26/03/2014
 * @brief Aggregation of the payment done by card
 */
public class Payment {

	private float quantity; //Quantity of the payment
	private String concept; //Concept of the payment
	private Calendar paymentDate; //Date of the payment
	private Card payCard; //Card with which is taken the payment
	private Handler id_payment; //Identifier
	
	/**
	 * Class constructor
	 * @param card
	 * @param payQuantity
	 * @param payConcept
	 * @throws PaymentHandlerException 
	 */
	Payment(Card card, float payQuantity, String payConcept) throws PaymentHandlerException{
		this.setPayCard(card);
		this.setQuantity(payQuantity);
		this.setConcept(payConcept);
		this.setPaymentDate(Calendar.getInstance());
		this.id_payment = new PaymentHandler(card.getCardId(), payConcept, this.getPaymentDate());
	}

	/**
	 * Getter of Quantity
	 * @return payment quantity 
	 */
	public float getQuantity() {
		return quantity;
	}

	/**
	 * Setter of Quantity
	 * @param quantity
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

	/**
	 * Getter Concept
	 * @return payment concept
	 */
	public String getConcept() {
		return concept;
	}

	/**
	 * Setter Concept
	 * @param concept
	 */
	public void setConcept(String concept) {
		this.concept = concept;
	}

	/**
	 * Getter date of payment in the format
	 * "dd/MM/yyyy HH:mm:ss"
	 * @return date
	 */
	public String getPaymentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(paymentDate);
	}

	/**
	 * Setter payment date
	 * @param paymentDate
	 */
	public void setPaymentDate(Calendar paymentDate) {
		this.paymentDate = paymentDate;
	}

	/**
	 * Getter Card which make the payment
	 * @return card
	 */
	public Card getPayCard() {
		return payCard;
	}

	/**
	 * Setter Card which make the payment
	 * @param payCard
	 */
	public void setPayCard(Card payCard) {
		this.payCard = payCard;
	}

	/**
	 * Getter of id
	 * @return id
	 */
	public Handler getId_payment() {
		return id_payment;
	}
	
}
