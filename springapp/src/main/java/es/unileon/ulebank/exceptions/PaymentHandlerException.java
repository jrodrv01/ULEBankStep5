package es.unileon.ulebank.exceptions;

/**
 * Class PaymentHandler Exception
 * @author Rober dCR
 * @date 09/04/2014
 * @brief Controls exception in Payment Handler
 */
public class PaymentHandlerException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Class constructor
	 * @param message
	 */
	public PaymentHandlerException(String message){
		super(message);
	}
}
