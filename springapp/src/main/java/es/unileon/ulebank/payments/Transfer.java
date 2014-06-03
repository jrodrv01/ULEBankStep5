package es.unileon.ulebank.payments;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.exceptions.TransferException;
import es.unileon.ulebank.handler.TransferHandler;

/**
 * Transfer Class
 * @author Rober dCR
 * @date 01/04/2014
 * @brief Class about transfers between two accounts
 */
public class Transfer {

	private Account senderAccount; //Account from transfer the money
	private Account receiverAccount; //Account which receives the money
	private float quantity; //Quantity of the transfer
	private TransferHandler id; //Identifier
	private Transference transaction; //Transaction of the transfer

	/**
	 * Class constructor
	 * @param sender
	 * @param receiver
	 * @param quantity
	 * @throws TransferException 
	 */
	public Transfer(Account sender, Account receiver, float quantity) throws TransferException{
		if (!sender.equals(receiver)){
			this.senderAccount = sender;
			this.receiverAccount = receiver;
			this.quantity = quantity;
			this.id = new TransferHandler(sender.getID().toString(), receiver.getID().toString());
		}
		else
			throw new TransferException("Sender Account number and Receiver Account number are the same.");
	}
	
	/**
	 * Getter senderAccount
	 * @return senderAccount
	 */
	public Account getSenderAccount() {
		return senderAccount;
	}
	
	/**
	 * Getter receiverAccount
	 * @return receiverAccount
	 */
	public Account getReceiverAccount() {
		return receiverAccount;
	}

	/**
	 * Getter quantity
	 * @return quantity
	 */
	public float getQuantity() {
		return quantity;
	}
	
	/**
	 * Setter quantity
	 * @param quantity
	 */
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	
	/**
	 * Method that transfers money from sender to receiver
	 * @param sender
	 * @param receiver
	 * @param quantity
	 * @throws TransferException 
	 */
	public void transferMoney(String concept) throws TransferException{
		if (this.senderAccount.getBalance() >= quantity){
			this.senderAccount.setBalance(this.senderAccount.getBalance() - quantity);
			this.receiverAccount.setBalance(this.receiverAccount.getBalance() + quantity);
			this.setTransaction(new Transference(this, concept)); //TODO - Actualizar a Transaction
		}
		else
			throw new TransferException("Sender Account has not the balance necessary.");
	}

	/**
	 * Getter id
	 * @return id
	 */
	public String getId() {
		return id.toString();
	}

	/**
	 * Getter transaction
	 * @return the annotation of the transfer
	 */
	public Transference getTransaction() {
		return transaction;
	}

	/**
	 * Setter transaction
	 * @param transaction
	 */
	public void setTransaction(Transference transaction) {
		this.transaction = transaction;
	}

}
