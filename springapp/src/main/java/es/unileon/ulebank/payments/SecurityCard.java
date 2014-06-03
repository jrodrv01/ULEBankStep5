package es.unileon.ulebank.payments;

import java.util.Random;

import es.unileon.ulebank.exceptions.SecurityCardException;

/**
 * SecurityCard Class
 * @author Rober dCR
 * @date 26/03/2014
 * @brief Class about the security matrix for the card
 * Based on "CajaEspana" SecurityCard
 */
public class SecurityCard {

	private boolean activate; //Value that indicates if the SecurityCard is given to the owner
	private final int ROW = 4; //Number of the rows in the matrix
	private final int COLUMNS = 10; //Number of the columns in the matrix
	private Integer[][] coordinates; //Matrix which store the coordinates of the security card
	private Card associatedCard; //Card which owns this security card
	
	/**
	 * @brief Security Card constructor
	 */
	public SecurityCard(Card card){
		this.coordinates = new Integer[ROW][COLUMNS];
		this.createCoordinates(this.coordinates);
		this.associatedCard = card;
		this.activate = false;
	}
	
	/**
	 * @brief Method fill the matrix of coordinates randomly
	 * @param coordinates
	 */
	private void createCoordinates(Integer[][] coordinates){
		Random randomGenerator = new Random();
		
		for (int i = 0; i < this.ROW; i++ ){
			for (int j = 0; j < this.COLUMNS; j++){		
				this.coordinates[i][j] = randomGenerator.nextInt(100);
			}
		}
	}
	
	/**
	 * @brief Method get the coordinate
	 * @param row
	 * @param column
	 * @return coordinate
	 * @throws SecurityCardException 
	 */
	private Integer getCoordinate(int row, int column) throws SecurityCardException{
		if ( ( (row >= 0) && (row < this.ROW) ) && ( (column >= 0) && (column < this.COLUMNS) ) )
			return this.coordinates[row][column];
		else
			throw new SecurityCardException("Index out of range");
	}
	
	/**
	 * @brief Method that probe if the coordinate is correct
	 * @param row
	 * @param column
	 * @param coordinate
	 * @return true if coordinate is correct / false another case
	 * @throws SecurityCardException 
	 */
	public boolean checkCoordinates(int row, int column, int coordinate) throws SecurityCardException{
		return this.getCoordinate(row, column).equals(coordinate);
	}
	
	/**
	 * Method that deliver to the owner the security card coordinates only one time
	 * if cardPin is correct
	 * @param cardPin
	 * @return 
	 * @throws SecurityCardException 
	 */
	public Integer[][] deliverSecurityCard(String cardPin) throws SecurityCardException{
		if (this.activate == true)
			throw new SecurityCardException("This Security Card has activated yet");
		else if(!this.associatedCard.checkPin(cardPin))
			throw new SecurityCardException("Invalid pin or this Security Card has activated yet");
		else {
			this.activate = true;
			return this.coordinates;
		}
	}
	
	/**
	 * Getter of the associated Card
	 * @return associatedCard
	 */
	public Card getAssociatedCard(){
		return this.associatedCard;
	}
		
}
