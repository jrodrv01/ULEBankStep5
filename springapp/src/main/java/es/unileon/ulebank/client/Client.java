/* Application developed for AW subject, belonging to passive operations
 group.*/

package es.unileon.ulebank.client;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.AccountHandler;
import es.unileon.ulebank.handler.Handler;

/**
 *Class tha provides the basic gestion data of a client in a bank
 * 
 * @author Gonzalo Nicolas Barreales
 */
public class Client {
    
    /**
     * Identifier of the client
     */
	@Autowired
    private Handler id;
    
    public void setId(Handler id) {
		this.id = id;
	}

	/**
     * Accounts where the client appear
     */
    private ArrayList<Account> accounts;
    
    /**
     * Client age
     */
    private int age;
    
    /**
     * Constructor of client. Receive the id and initilize the list of accounts
     * 
     * @param clientHandler 
     */
    
    public Client(){
    	 accounts = new ArrayList<Account>();
    }
    public Client(Handler clientHandler){
        accounts = new ArrayList<Account>();
        this.id=clientHandler;
    }
    
    public Client(Handler clientHandler, int age){
        accounts = new ArrayList<Account>();
        this.id=clientHandler;
        this.age = age;
    }
    
    /**
     * Adds an account to the list of clients. If the account exists, it won't be added
     * 
     * @param account 
     */
    public void add(Account account){
        if(!accounts.contains(account)){
            accounts.add(account);
        }
    }
    
    /**
     * Remove the account identified with acountHandler
     * 
     * @param accountHandler
     * @return true if account is deleted, false if account doesn't exists
     */
    public boolean removeAccount(Handler accountHandler){
        boolean result = false;
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            Account account = iterator.next();
            if(account.getID().compareTo(accountHandler)==0){
                result = accounts.remove(account);
            }
        }
        return result;
    }
    
    /**
     * Check if the account idientified with account Handler exists
     * @param accountHandler
     * @return true if the account exists, false if it doesn't exists
     */
    public boolean existsAccount(Handler accountHandler){
        boolean result = false;
        Iterator<Account> iterator = accounts.iterator();
        while(iterator.hasNext()){
            Account account = iterator.next();
            if(account.getID().compareTo(accountHandler)==0){
                result = true;
            }
        }
        return result;
    }
    
    public Account searchAccount(AccountHandler handler) {
		Iterator<Account> iterator = accounts.iterator();
		Account account = null;
		
		if (this.accounts.isEmpty()) {
			throw new NullPointerException("Account list is empty.");
		}
		
		while (iterator.hasNext()) {
			account = iterator.next();
			
			if (account.getID().compareTo(handler) == 0) {
				break;
			}
		}
		
		return account;
	}

    /**
     * @return id of the client
     */
    public Handler getId() {
        return id;
    }
    
    
    public Handler getDni() {
		return id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
}
