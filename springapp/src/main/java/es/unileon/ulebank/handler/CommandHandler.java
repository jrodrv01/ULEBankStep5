package es.unileon.ulebank.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * CommandHandler class
 * @author Rober dCR
 * @date 9/04/2014
 * @brief Class of the identifier for Commands
 */
public class CommandHandler implements Handler {

    private UUID uuid;
	private Handler id;
	private String date;
	
	/**
	 * Class constructor
	 * @param id
	 */
	public CommandHandler(Handler id){
		this.id = id;
                this.uuid = UUID.randomUUID();
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		this.date = dateFormat.format(new Date());
	}
	
	@Override
	public int compareTo(Handler another) {
		return this.toString().compareTo(another.toString());
	}
	
	/**
	 * Getter id
	 * @return id
	 */
	public Handler getId(){
		return this.id;
	}

	/**
	 * Getter date
	 * @return date
	 */
	public String getDate(){
		return this.date.toString();
	}
	
	public String toString() {
		return this.id.toString() + " " + date.toString();
	}
}
