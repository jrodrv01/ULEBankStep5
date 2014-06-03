package es.unileon.ulebank.payments;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.unileon.ulebank.exceptions.IncorrectLimitException;
import es.unileon.ulebank.fees.FeeStrategy;
import es.unileon.ulebank.handler.CardHandler;

/**
 * @author Israel
 */
public abstract class Card {
	//Constantes para los limites por defecto
	private final double BUY_LIMIT_DIARY_DEFAULT = 400.0;
	private final double BUY_LIMIT_MONTHLY_DEFAULT = 1000.0;
	private final double CASH_LIMIT_DIARY_DEFAULT = 400.0;
	private final double CASH_LIMIT_MONTHLY_DEFAULT = 1000.0;
	//Constante para el limite minimo
	private final double MINIMUM_LIMIT = 200.0;
	//Constante para la cantidad de agnos de caducidad por defecto
	private final int EXPIRATION_YEAR = 3;
	//Constante para el tamagno del CVV
	private final int CVV_SIZE = 3;
	//Constante para el tamagno del PIN
	private final int PIN_SIZE = 4;
	
	//Identificador de la tarjeta
	private CardHandler cardId;
	//Codigo PIN de la tarjeta
	private String pin;
	//Limite de compra diario de la tarjeta
	private double buyLimitDiary;
	//Limite de compra mensual de la tarjeta
	private double buyLimitMonthly;
	//Limite de extraccion en cajero diario de la tarjeta
	private double cashLimitDiary;
	//Limite de extraccion en cajero mensual de la tarjeta
	private double cashLimitMonthly;
	//Fecha de emision de la tarjeta
	private String emissionDate;
	//Fecha de caducidad de la tarjeta
	private String expirationDate;
	//Tipo de tarjeta
	private CardType cardType;
	//Codigo CVV de la tarjeta
	private String cvv;
	//Comision de emision de la tarjeta
	private FeeStrategy commissionEmission;
	//Comision de mantenimiento de la tarjeta
	private FeeStrategy commissionMaintenance;
	//Comision de renovacion de la tarjeta
	private FeeStrategy commissionRenovate;
	//Limite de deuda de la tarjeta (Solo en el caso de las de credito)
	private double limitDebit;
	
	/**
	 * Crea una nueva tarjeta con los parametros indicados
	 * @param cardId
	 * @param owner
	 * @param account
	 * @param type
	 * @param buyLimitDiary
	 * @param buyLimitMonthly
	 * @param cashLimitDiary
	 * @param cashLimitMonthly
	 * @param commissionEmission
	 * @param commissionMaintenance
	 * @param commissionRenovate
	 * @param limitDebit
	 */
	public Card(CardHandler cardId, CardType type,
			double buyLimitDiary, double buyLimitMonthly, double cashLimitDiary, double cashLimitMonthly,
			FeeStrategy commissionEmission, FeeStrategy commissionMaintenance, 
			FeeStrategy commissionRenovate, double limitDebit) {
		this.cardId = cardId;
		this.cardType = type;
		this.pin = generatePinCode();
		this.buyLimitDiary = buyLimitDiary;
		this.buyLimitMonthly = buyLimitMonthly;
		this.cashLimitDiary = cashLimitDiary;
		this.cashLimitMonthly = cashLimitMonthly;
		this.emissionDate = generateEmissionDate();
		this.expirationDate = generateExpirationDate();
		this.cvv = this.generateCVV();
		this.commissionEmission = commissionEmission;
		this.commissionMaintenance = commissionMaintenance;
		this.commissionRenovate = commissionRenovate;
		this.limitDebit = limitDebit;
	}

	/**
	 * Genera el codigo pin de la tarjeta
	 * @return
	 */
	public String generatePinCode() {
		StringBuilder result = new StringBuilder();
		//Generamos tantos numeros aleatorios como indique la constante PIN_SIZE para formar el codigo PIN
		for (int i = 0; i < PIN_SIZE; i++) {
			result.append((int) (Math.random()*10));
		}
		
		return result.toString();
	}
	
	/**
	 * Genera la fecha de emision de la tarjeta
	 * @return
	 */
	public String generateEmissionDate() {
		//Generamos la fecha dandole el formato estandar dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		return dateFormat.format(new Date());
	}
	
	/**
	 * Genera una fecha de caducidad para la tarjeta
	 * @return
	 */
	public String generateExpirationDate() {
		//Obtenemos una instancia del calendario
		Calendar calendar = Calendar.getInstance();
		
		//Obtenemos el mes actual que sera devuelto en forma de int comenzando en 0 (Enero) por tanto debemos sumarle 1
		String month = Integer.toString(calendar.get(Calendar.MONTH) + 1); 
		//Si el mes esta entre enero y septiembre agnadimos un 0 delante al String para que tenga 2 cifras
		if (month.length() == 1) {
			month = "0" + month;
		}
		
		//Obtenemos el agno actual y cortamos el substring para coger unicamente las dos ultimas cifras
		String year = Integer.toString(EXPIRATION_YEAR + calendar.get(Calendar.YEAR)).substring(2);
		//Devolvemos el String con el formato MM/yy
		return month + "/" + year;
	}
	
	/**
	 * Genera el codigo de validacion CVV
	 * @return
	 */
	public String generateCVV() {
		StringBuilder result = new StringBuilder();
		//Generamos tantos numeros aleatorios como indique la constante CVV_SIZE para formar el codigo CVV
		for (int i = 0; i < CVV_SIZE; i++) {
			result.append((int) (Math.random()*10));
		}
		
		return result.toString();
	}
	
	/**
	 * Devuelve el identificador de la tarjeta
	 * @return
	 */
	public String getCardId() {
		return this.cardId.toString();
	}
	
	/**
	 * Devuelve el codigo PIN de la tarjeta
	 * @return
	 */
	public String getPin() {
		return this.pin;
	}
	
	/**
	 * Cambia el codigo PIN de la tarjeta por el que recibe
	 * @param pin
	 * @throws IOException 
	 */
	public void setPin(String pin) throws IOException {
		//Comprobamos que el String recibido contiene solo numeros
		if (checkStringNumber(pin)) {
			//Si el pin tiene el tamagno adecuado lo cambiamos
			if (pin.length() == PIN_SIZE) {
				this.pin = pin;
			//Sino lanzamos una excepcion
			} else {
				throw new IOException("Incorrect length");
			}
		//Sino lanzamos una excepcion ya que solo puede haber numeros
		} else {
			throw new IOException("The pin must only contain numbers");
		}
	}
	
	/**
	 * Comprueba que el pin sea correcto
	 * @param pin
	 * @return
	 */
	public boolean checkPin(String pin) {
		//Si el pin coincide devuelve true
		if (pin.equals(this.pin)) {
			return true;
		//Sino devuelve false
		} else {
			return false;
		}
	}
	
	/**
	 * Devuelve el limite de la tarjeta diario para compras
	 * @return
	 */
	public double getBuyLimitDiary() {
		return this.buyLimitDiary;
	}
	
	/**
	 * Cambia el linmite de la tarjeta diario para compras
	 * @param newAmount
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitDiary(double newAmount) throws IncorrectLimitException {
		//Si el limite mensual es mayor que el limite diario a cambiar y este ultimo es mayor 
		//o igual que el limite minimo, cambiamos el limite
		if (this.buyLimitMonthly > newAmount && newAmount >= MINIMUM_LIMIT) {
			this.buyLimitDiary = newAmount;
		//en caso contrario lanzamos una excepcion
		} else {
			throw new IncorrectLimitException("The limit is bigger than current monthly limit or is too small.");
		}
	}
	
	/**
	 * Comprueba que el precio no exceda el limite de la tarjeta
	 * @param price
	 * @return
	 */
	public boolean checkBuyLimitDiary(double price) {
		//Si el precio es mayor que el limite de compra diario devuelve false
		if (price > buyLimitDiary) {
			return false;
		//sino devuelve true
		} else {
			return true;
		}
	}
	
	/**
	 * Devuelve el limite de la tarjeta mensual para compras
	 * @return
	 */
	public double getCashLimitMonthly() {
		return cashLimitMonthly;
	}
	
	/**
	 * Devuelve el limite de compra mensual
	 * @return
	 */
	public double getBuyLimitMonthly() {
		return buyLimitMonthly;
	}

	/**
	 * Cambia el linmite de la tarjeta mensual para compras
	 * @param buyLimit
	 * @throws IncorrectLimitException 
	 */
	public void setBuyLimitMonthly(double newAmount) throws IncorrectLimitException {
		//Si el limite recibido es mayor o igual que el limite diario lo cambiamos
		if (newAmount >= this.buyLimitDiary) {
			this.buyLimitMonthly = newAmount;
		//sino se lanza una excepcion
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
	}

	/**
	 * Devuelve el limite de la tarjeta para extracciones en cajeros
	 * @return
	 */
	public double getCashLimitDiary() {
		return this.cashLimitDiary;
	}
	
	/**
	 * Cambia el limite de la tarjeta para extracciones en cajeros
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitDiary(double newAmount) throws IncorrectLimitException {
		//Si el limite mensual es mayor que el limite diario a cambiar y este ultimo es mayor 
		//o igual que el limite minimo, cambiamos el limite
		if (this.cashLimitMonthly >= newAmount && newAmount >= MINIMUM_LIMIT) {
			this.cashLimitDiary = newAmount;
		//sino lanzamos una excepcion
		} else {
			throw new IncorrectLimitException("The limit is bigger than current monthly limit or is too small.");
		}
	}
	
	/**
	 * Comprueba que la cantidad solicitada para extraer en cajero no exceda el limite de la tarjeta
	 * @param cash
	 * @return
	 */
	public boolean checkCashLimitDiary(double cash) {
		//Si la cantidad solicitada para extraer es mayor que la cantidad maxima diaria devuelve false
		if (cash > this.cashLimitDiary) {
			return false;
		//sino devuelve true
		} else {
			return true;
		}
	}

	/**
	 * Cambia la cantidad maxima para extraer en un cajero al mes
	 * @param newAmount
	 * @throws IncorrectLimitException 
	 */
	public void setCashLimitMonthly(double newAmount) throws IncorrectLimitException {
		//Si el limite recibido es mayor o igual que el limite diario lo cambiamos
		if (newAmount >= this.cashLimitDiary) {
			this.cashLimitMonthly = newAmount;
		//en caso contrario se lanza una excepcion
		} else {
			throw new IncorrectLimitException("Monthly limit must be greater than diary limit");
		}
	}
	
	/**
	 * Devuelve la fecha de emision de la tarjeta
	 * @return
	 */
	public String getEmissionDate() {
		return emissionDate;
	}

	/**
	 * Devuelve la fecha de caducidad de la tarjeta
	 * @return
	 */
	public String getExpirationDate() {
		return this.expirationDate;
	}
	
	/**
	 * Cambia la fecha de caducidad por una nueva
	 * @param expirationDate
	 */
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Devuelve el tipo de tarjeta
	 * @return
	 */
	public CardType getCardType() {
		return this.cardType;
	}

	/**
	 * Devuelve el codigo de validacion CVV
	 * @return
	 */
	public String getCvv() {
		return this.cvv;
	}

	/**
	 * Cambia el CVV por el nuevo que ha recibido
	 * @param cvv
	 * @throws IOException 
	 */
	public void setCvv(String cvv) throws IOException {
		//Comprueba que el String contenga solo numeros
		if (checkStringNumber(cvv)) {
			//Si el tamagno del cvv es correcto se cambia
			if (cvv.length() == CVV_SIZE) {
				this.cvv = cvv;
			//sino se lanza una excepcion
			} else {
				throw new IOException("Incorrect length");
			}
		//sino se lanza una excepcion
		} else {
			throw new IOException("The cvv must only contains numbers");
		}
	}

	/**
	 * Devuelve el numero de la tarjeta
	 * @return
	 */
	public CardHandler getCardNumber() {
		return cardId;
	}

	/**
	 * Devuelve el limite diario de compra por defecto
	 * @return
	 */
	public double getBuyLimitDiaryDefault() {
		return BUY_LIMIT_DIARY_DEFAULT;
	}

	/**
	 * Devuelve el limite mensual de compra por defecto
	 * @return
	 */
	public double getBuyLimitMonthlyDefault() {
		return BUY_LIMIT_MONTHLY_DEFAULT;
	}

	/**
	 * Devuelve el limite diario de extraccion en cajero por defecto
	 * @return
	 */
	public double getCashLimitDiaryDefault() {
		return CASH_LIMIT_DIARY_DEFAULT;
	}

	/**
	 * Devuelve el limite mensual de extraccion en cajero por defecto
	 * @return
	 */
	public double getCashLimitMonthlyDefault() {
		return CASH_LIMIT_MONTHLY_DEFAULT;
	}

	/**
	 * Devuelve la comision de emision de la tarjeta
	 * @return
	 */
	public double getCommissionEmission(double quantity) {
		return commissionEmission.getFee(quantity);
	}

	/**
	 * Cambia la comision de emision por la que recibe
	 * @param commissionEmission
	 */
	public void setCommissionEmission(FeeStrategy commissionEmission) {
		this.commissionEmission = commissionEmission;
	}

	/**
	 * Devuelve la comisionde mantenimiento de la tarjeta
	 * @return
	 */
	public double getCommissionMaintenance(double quantity) {
		return commissionMaintenance.getFee(quantity);
	}

	/**
	 * Cambia la comision de mantenimiento por la que se indica
	 * @param commissionMaintenance
	 */
	public void setCommissionMaintenance(FeeStrategy commissionMaintenance) {
		this.commissionMaintenance = commissionMaintenance;
	}

	/**
	 * Devuelve la comision de renovacion de la tarjeta
	 * @return
	 */
	public double getCommissionRenovate(double quantity) {
		return commissionRenovate.getFee(quantity);
	}

	/**
	 * Cambia la comision de renovacion por la que se recibe
	 * @param commissionRenovate
	 */
	public void setCommissionRenovate(FeeStrategy commissionRenovate) {
		this.commissionRenovate = commissionRenovate;
	}

	/**
	 * Devuelve el limite de deuda de la tarjeta
	 * @return
	 */
	public double getLimitDebit() {
		return limitDebit;
	}

	/**
	 * Cambia el limite de deuda de la tarjeta por el recibido
	 * @param limitDebt
	 */
	public void setLimitDebit(double limitDebt) {
		this.limitDebit = limitDebt;
	}
	
	/**
	 * Comprueba que el String recibido sea solo numerico
	 * @param string
	 * @return
	 */
	private boolean checkStringNumber(String string) {
		//Crea un patron para indicar que solo debe contener numeros
		Pattern pattern = Pattern.compile("^[0-9]*$");
		//Comprueba que el String recibido cumple el patron
		Matcher matcher = pattern.matcher(string);
		
		//Si se cumple el patron devuelve true
		if (matcher.find()) {
			return true;
		//sino devuelve false
		} else {
			return false;
		}
	}
}
