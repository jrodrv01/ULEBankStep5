package es.unileon.ulebank.handler;

import es.unileon.ulebank.exceptions.PaymentHandlerException;

/**
 * Class of Payment Handler
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Payment formed by the 5 last numbers of Card Handler,
 * 3 first chars from concept (if concept is empty it will be BBB) and 4 numbers of the month and year
 * of the payment (example 30th April of 2014 -> 0414).
 */
public class PaymentHandler implements Handler {

	private final int LENGTH = 12;
	private final int POSITION_CARD = 10;
	private final int NUMBER_INITIALS = 5;
	private final int NUMBER_MEDIUM = 3;
	private String id;
	
	/**
	 * Class constructor
	 * @param cardNumber
	 * @param concept
	 * @param date
	 * @throws PaymentHandlerException
	 */
	public PaymentHandler(String cardNumber, String concept, String date) throws PaymentHandlerException{
		this.id = this.obtainInitials(cardNumber) + this.obtainChars(concept) + this.obtainFinals(date);
		if (this.id.length() != this.LENGTH)
			throw new PaymentHandlerException("Longitud de Payment Handler err�nea");
	}

	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}

	/**
	 * @brief Method that obtains the first 5 numbers of the handler
	 * @param cardNumber
	 * @return 5 initials
	 * @throws PaymentHandlerException
	 */
	private String obtainInitials(String cardNumber) throws PaymentHandlerException{
		if (cardNumber.substring(this.POSITION_CARD).length() == this.NUMBER_INITIALS)
			return cardNumber.substring(this.POSITION_CARD);
		else
			throw new PaymentHandlerException("Longitud de los numeros iniciales incorrecta");
	}
	
	/**
	 * Method that obtains the 4 final numbers
	 * @param date
	 * @return
	 */
	private String obtainFinals(String date){
		//Format date dd/MM/yyyy HH:mm:ss
		return date.substring(4, 5) + date.substring(9, 10);
	}
	
	/**
	 * Method that obtains the 3 characters of the medium
	 * @param concept
	 * @return 3 characters for the medium of the id
	 */
	private String obtainChars(String concept){
		if (concept.length() < this.NUMBER_MEDIUM)
			for (int i = concept.length(); i < this.NUMBER_MEDIUM; i++)
				concept.concat("B");
		
		return concept.substring(0, 2);
	}
	
	/**
	 * Getter of id
	 * @return
	 */
	public String getId() {
		return id;
	}

}
