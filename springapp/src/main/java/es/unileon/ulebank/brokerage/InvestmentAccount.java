package es.unileon.ulebank.brokerage;

import java.util.ArrayList;

import es.unileon.ulebank.account.Account;
import es.unileon.ulebank.account.exception.BalanceException;
import es.unileon.ulebank.brokerage.buyable.Enterprise;
import es.unileon.ulebank.brokerage.buyable.InvalidBuyableException;
import es.unileon.ulebank.brokerage.buyable.InvestmentFund;
import es.unileon.ulebank.brokerage.buyable.NotEnoughParticipationsException;
import es.unileon.ulebank.brokerage.pack.InvestmentFundPack;
import es.unileon.ulebank.brokerage.pack.Pack;
import es.unileon.ulebank.brokerage.pack.StockPack;
import es.unileon.ulebank.fees.DefaultFeeProvider;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.users.Employee;

public class InvestmentAccount {

    private final ArrayList<Pack> stockPacks;
    private final ArrayList<Pack> fundPacks;

    private FeeStrategy buyStockageFee, sellStockageFee;

    public FeeStrategy getBuyStockageFee() {
		return buyStockageFee;
	}

	public void setBuyStockageFee(FeeStrategy buyStockageFee) {
		this.buyStockageFee = buyStockageFee;
	}

	public FeeStrategy getSellStockageFee() {
		return sellStockageFee;
	}

	public void setSellStockageFee(FeeStrategy sellStockageFee) {
		this.sellStockageFee = sellStockageFee;
	}

	public InvestmentAccount() {
        this.stockPacks = new ArrayList<Pack>();
        this.fundPacks = new ArrayList<Pack>();

      

        this.buyStockageFee = DefaultFeeProvider.getInstance().getDefaultFee();
        this.sellStockageFee = DefaultFeeProvider.getInstance().getDefaultFee();
    }

    /**
     * Builds a new StockPack, generates the transaction and adds it to the
     * history and withraws the money from the buyer's account.
     *
     * @param e
     * @param acc Account where the amount will be charged.
     * @param amount Amount of stockage to buy
     * @param operator Bank operator which performs the transaction (the one who
     * is logged in probably).
     * @throws es.unileon.ulebank.brokerage.buyable.InvalidBuyableException
     * @throws es.unileon.ulebank.account.exception.BalanceException
     * @throws
     * es.unileon.ulebank.brokerage.buyable.NotEnoughParticipationsException
     * @throws es.unileon.ulebank.history.TransactionException
     */
    public void buyStockage(Enterprise e, Account acc, int amount, Employee operator) throws InvalidBuyableException, BalanceException, NotEnoughParticipationsException {
        StockPack p = e.buy(amount, operator);
        p.setAccount(acc);
        stockPacks.add(p);

        double price = amount * e.getPPP();
    }

       

    public void sellStockage(StockPack p, int amount, Employee operator) throws NotEnoughParticipationsException {
        if (p.getAmount() < amount) {
            throw new NotEnoughParticipationsException();
        }

        p.setAmount(p.getAmount() - amount);

        double price = p.getProduct().getPPP() * amount;
        
        

        this.prunePacks();
    }

    public void buyInvestmentFund(InvestmentFund i, Account acc, int amount, Employee operator) throws InvalidBuyableException, BalanceException, NotEnoughParticipationsException {
        InvestmentFundPack ifp = i.buy(amount, operator);
        fundPacks.add(ifp);
        double price = amount * i.getPPP();
       

        
    }

    /**
     * @return the fundHistory
     */
 

    private void prunePacks() {
        // TODO: Delete 0-participations packs.
    }
}
