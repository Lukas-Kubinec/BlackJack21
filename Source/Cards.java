//package cwJavaAsylum;

import java.util.Random;

/**
 * Card class that chooses a random card from the deck of cards.
 * It returns the cards name, its value and image file path.
 */

public class Cards extends MainWindow {

	// Game variables
	private static final long serialVersionUID = 0L;	// Identifier used to serialise this object ensuring its compatibility
	private int cardValue = 0;			// Value of the generated card
	private String chosenSuit = "";		// Suit of the generated card
	private String chosenType = "";		// Type of the generated card
	private String chosenCard = "";		// Full name of the generated card
	private String cardFilePath = "";	// File path for the cards image
	
	// Arrays used to store the deck of cards
	String[] suits = {"Spade", "Heart", "Diamond", "Club"};
	String[] types = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

	// Random function that will generate random numbers
	Random randomNumber = new Random();

	public Cards() {
		// Empty constructor
	}

	public String getCard(String[] UsedCards) {
		// Variable for a check to show if card is already drawn
		boolean cardIsAlreadyUsed = false;

		// Randomly assigning suit
		int x;
		x = randomNumber.nextInt(0, 4);
		chosenSuit = suits[x];

		// Randomly assigning type
		int y;
		y = randomNumber.nextInt(0, 13);
		chosenType = types[y];

		// Updates the cards value based on its position within the array
		if (y < 10) {
			cardValue = y + 1;
		} else {
			cardValue = 10;	// Highest possible value of a card in BlackJack besides Ace
		}

		// Creates the card and its file path for its image
		chosenCard = chosenType + chosenSuit;
		cardFilePath = chosenType.toLowerCase() + "_" + chosenSuit.toLowerCase() + ".png";

		// Checks if the card was already drawn before
		for (int j=0; j < UsedCards.length; j++) {
			if (chosenCard.equals(UsedCards[j])) {
				cardIsAlreadyUsed = true;
				getCard(UsedCards);
				break;
			}
		}

		// Returns the card if the card wasn't used before
		if (cardIsAlreadyUsed == false) {
			return chosenCard;
		} else if (cardIsAlreadyUsed == true) {
			// Returns zero, so the method knows that the card was already used
			return "0";
		}
		
		// In case everything fails
		return "0";
	}
	
	// Returns the card file path for its image
	public String getFilePath() {
		return cardFilePath;
	}
	
	// Returns the cards value 
	public int getCardValue() {
		return cardValue;
	}
}
