package es.unileon.ulebank.account.liquidation;

import es.unileon.ulebank.history.Transaction;
import java.util.Collection;

/**
 * 
 * @author runix
 */
public interface LiquidationStrategy {

	/**
	 * Perform liquitadation based on account history.
	 * 
	 * @param transactions
	 *            ( The transactions for doing the liquidation )
	 * @param months
	 *            ( The number of months )
	 * 
	 *            max(transaction.date)-min(transaction.date)==months
	 * 
	 * @return (The amount of money to pay )
	 */
	public double doLiquidation(Collection<Transaction> transactions, int months);
}