package es.unileon.ulebank.handler;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Class of Transfer Handler
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Identifier of Transfer
 */
public class TransferHandler implements Handler {

	private String id;
	private String date;
	private String sender;
	private String receiver;
	private Calendar calendar;
	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 */
	public TransferHandler(String sender, String receiver){
		this.sender = sender.substring(sender.length()/2);
		this.receiver = receiver.substring(receiver.length()/2);
		this.calendar = new GregorianCalendar();
		this.setDateCode();
		this.id = this.sender + this.receiver + this.date;
	}
	
	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}
	
	/**
	 * Getter id
	 * @return
	 */
	public String getId() {
		return id;
	}

	/**
	 * Method that obtains the code from the date
	 */
	private void setDateCode(){
		this.date = this.calendar.get(Calendar.DAY_OF_MONTH) + Integer.toString(this.calendar.get(Calendar.MONTH) + 1) + this.calendar.get(Calendar.YEAR) + this.calendar.get(Calendar.HOUR_OF_DAY) + this.calendar.get(Calendar.MINUTE) + this.calendar.get(Calendar.SECOND);
	}
}
