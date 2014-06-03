package es.unileon.ulebank.payments;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.client.Client;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.handler.CardHandler;

/**
 * @author Israel
 */
public class PurseCard extends Card {

	public PurseCard(CardHandler cardId, Client owner, Account account,
			CardType type, double buyLimitDiary, double buyLimitMonthly,
			double cashLimitDiary, double cashLimitMonthly,
			         FeeStrategy commissionEmission,
			FeeStrategy commissionMaintenance,
			FeeStrategy commissionRenovate, double limitDebit) {
		super(cardId, type, buyLimitDiary, buyLimitMonthly,
				cashLimitDiary, cashLimitMonthly, commissionEmission,
				commissionMaintenance, commissionRenovate, limitDebit);
		// TODO Auto-generated constructor stub
	}
}
