//package cwJavaAsylum;

//Imports Swing and AWT libraries used for GUI and its functionality
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

/*
* @authors 		Lukas Kubinec, Samuel Wiggans, Jude Nellis, Niamh Brady
* @name 		BlackJack - 21 Card game
* @version 		1.0
* @description	Coursework 2 - Group Project
*/

public class MainWindow extends JFrame {

	// Game variables for player
	int playersChips = 200;		// Amount of chips the player has
	int playersCards = 0;		// Value of cards in players hands
	int playersCardsCount = 0;	// Amount of cards in players hands
	
	// Game variables for dealer
	int dealersCards = 0;		// Value of cards in dealers hands
	int dealersCardsCount = 0;	// Amount of cards in players hands
	
	// Other game variables
	int betAmount = 0;									// Value of chips the player bet in game
	String[] usedCards = new String[10];				// Stores the cards drawn by player and dealer in a game, 10 cards is the maximum amount
	private static final long serialVersionUID = 0L;	// Identifier used to serialise this object ensuring its compatibility
	private JPanel contentGamePanel = new JPanel();		// Panel placed inside the game window that hold all the GUI elements within

	// Game Buttons 
	JButton buttonHit = new JButton("Hit");
	JButton buttonFold = new JButton("Stand");
	JButton buttonDeal = new JButton("Deal");
	JButton buttonGameReset = new JButton("Reset");
	JButton buttonClearBet = new JButton("Clear");

	// Chips betting buttons
	JButton buttonChipsAllin = new JButton("");			// All in
	JButton buttonChipsFivehundred = new JButton(""); 	// bet 500
	JButton buttonChipsOneHundred = new JButton("");		// bet 100
	JButton buttonChipsTwenty = new JButton("");		// bet 20
	
	// Chips images
	JLabel imageChipFiveHundred = new JLabel("");
	JLabel imageChipOneHundred = new JLabel("");
	JLabel imageChipTwenty = new JLabel("");

	// Other text labels
	JLabel textLabelWonLost = new JLabel("LOST / WON");
	JLabel textLabelCurrentBetPanel = new JLabel("Current Bet:");

	// Background graphics elements
	JLabel backgroundImageBetAmountCounter = new JLabel("");	// Bet Value Counter Background
	JLabel backgroundImagePlayersChips = new JLabel("");		// Players Chips Background
	JLabel backgroundImageUpperPanelStrip = new JLabel("");
	JLabel backgroundImageBottomPanelStrip = new JLabel("");
	JLabel backgroundImageBottomPanel = new JLabel("");
	JLabel backgroundImageUpperPanel = new JLabel("");

	// Counters
	JLabel textLabelBetAmount = new JLabel("" + betAmount);								// Bet Value Counter
	JLabel textLabelPlayersChips = new JLabel(""+playersChips);							// Players chips
	JLabel textLabelPlayersCardsValue = new JLabel("Your cards: " + playersCards);		// Players value of cards
	JLabel textLabelDealersCardsValue = new JLabel("Dealer's cards: " + dealersCards);	// Dealers value of cards
	
	// Background Image 
	JLabel backgroundImageTable = new JLabel("");

	// Path for card graphics
	String pathToNewCard = ""; 	// assigned in game based on card drawn form the deck
	
	// Players cards
	private final JLabel objectPlayerCardOne = new JLabel("");
	private final JLabel objectPlayerCardTwo = new JLabel("");
	private final JLabel objectPlayerCardFour = new JLabel("");
	private final JLabel objectPlayerCardThree = new JLabel("");
	private final JLabel objectPlayerCardFive = new JLabel("");
	
	// Dealers cards
	private final JLabel objectDealerCardFive = new JLabel("");
	private final JLabel objectDealerCardFour = new JLabel("");
	private final JLabel objectDealerCardThree = new JLabel("");
	private final JLabel objectDealerCardTwo = new JLabel("");
	private final JLabel objectDealerCardOne = new JLabel("");
	

	/**
	 * Launches the game window
	 * @param args - not currently in use
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Game tries to open a window
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					// If game fails to open a window, an error message gets printed
					System.err.print("Error 02 - Window cannot be created - please report");
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the game window with all its elements
	 * 	Game window is not resizable
	 * 	Window size is fixed to 1200x674
	 * 	Title is set to JavaAsylum
	 */
	public MainWindow() {
		// Window settings
		setResizable(false);
		setTitle("JavaAsylum");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 674);
		
		// Game area of the window
		contentGamePanel.setBackground(new Color(0, 102, 0));
		contentGamePanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentGamePanel);
		contentGamePanel.setLayout(null);
		
		// Images of chips used for bet; visible on the left side
		// 500 Chip
		imageChipFiveHundred.setVisible(false);
		imageChipFiveHundred.setHorizontalAlignment(SwingConstants.CENTER);
		imageChipFiveHundred.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/chips/500_chip.png")));
		imageChipFiveHundred.setBounds(41, 346, 90, 90);
		contentGamePanel.add(imageChipFiveHundred);
		
		// 100 Chip
		imageChipOneHundred.setVisible(false);
		imageChipOneHundred.setHorizontalAlignment(SwingConstants.CENTER);
		imageChipOneHundred.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/chips/100_chip.png")));
		imageChipOneHundred.setBounds(112, 261, 90, 90);
		contentGamePanel.add(imageChipOneHundred);
		
		// 20 Chip
		imageChipTwenty.setVisible(false);
		imageChipTwenty.setHorizontalAlignment(SwingConstants.CENTER);
		imageChipTwenty.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/chips/20_chip.png")));
		imageChipTwenty.setBounds(41, 190, 90, 90);
		contentGamePanel.add(imageChipTwenty);		
		
		// Dealers cards
		// Fifth card
		objectDealerCardFive.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectDealerCardFive.setHorizontalAlignment(SwingConstants.CENTER);
		objectDealerCardFive.setBackground(Color.WHITE);
		objectDealerCardFive.setAlignmentX(0.5f);
		objectDealerCardFive.setBounds(776, 90, 100, 144);
		contentGamePanel.add(objectDealerCardFive);
		
		// Fourth card
		objectDealerCardFour.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectDealerCardFour.setHorizontalAlignment(SwingConstants.CENTER);
		objectDealerCardFour.setBackground(Color.WHITE);
		objectDealerCardFour.setAlignmentX(0.5f);
		objectDealerCardFour.setBounds(656, 90, 100, 144);
		contentGamePanel.add(objectDealerCardFour);
		
		// Third card
		objectDealerCardThree.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectDealerCardThree.setHorizontalAlignment(SwingConstants.CENTER);
		objectDealerCardThree.setBackground(Color.WHITE);
		objectDealerCardThree.setAlignmentX(0.5f);
		objectDealerCardThree.setBounds(536, 90, 100, 144);
		contentGamePanel.add(objectDealerCardThree);
		
		// Second card
		objectDealerCardTwo.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectDealerCardTwo.setHorizontalAlignment(SwingConstants.CENTER);
		objectDealerCardTwo.setBackground(Color.WHITE);
		objectDealerCardTwo.setAlignmentX(0.5f);
		objectDealerCardTwo.setBounds(416, 90, 100, 144);
		contentGamePanel.add(objectDealerCardTwo);
		
		// First card
		objectDealerCardOne.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectDealerCardOne.setHorizontalAlignment(SwingConstants.CENTER);
		objectDealerCardOne.setBackground(Color.WHITE);
		objectDealerCardOne.setAlignmentX(0.5f);
		objectDealerCardOne.setBounds(296, 90, 100, 144);
		contentGamePanel.add(objectDealerCardOne);
		
		// Players cards
		// Fifth dard
		objectPlayerCardFive.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectPlayerCardFive.setHorizontalAlignment(SwingConstants.CENTER);
		objectPlayerCardFive.setBackground(Color.WHITE);
		objectPlayerCardFive.setAlignmentX(0.5f);
		objectPlayerCardFive.setBounds(776, 365, 100, 144);
		contentGamePanel.add(objectPlayerCardFive);
		
		// Fourth card
		objectPlayerCardFour.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectPlayerCardFour.setHorizontalAlignment(SwingConstants.CENTER);
		objectPlayerCardFour.setBackground(Color.WHITE);
		objectPlayerCardFour.setAlignmentX(0.5f);
		objectPlayerCardFour.setBounds(656, 365, 100, 144);
		contentGamePanel.add(objectPlayerCardFour);
		
		// Third card
		objectPlayerCardThree.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectPlayerCardThree.setHorizontalAlignment(SwingConstants.CENTER);
		objectPlayerCardThree.setBackground(Color.WHITE);
		objectPlayerCardThree.setAlignmentX(0.5f);
		objectPlayerCardThree.setBounds(536, 365, 100, 144);
		contentGamePanel.add(objectPlayerCardThree);
		
		// Second card
		objectPlayerCardTwo.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectPlayerCardTwo.setHorizontalAlignment(SwingConstants.CENTER);
		objectPlayerCardTwo.setBackground(Color.WHITE);
		objectPlayerCardTwo.setAlignmentX(0.5f);
		objectPlayerCardTwo.setBounds(416, 365, 100, 144);
		contentGamePanel.add(objectPlayerCardTwo);
		
		// First card
		objectPlayerCardOne.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/empty_card.png")));
		objectPlayerCardOne.setHorizontalAlignment(SwingConstants.CENTER);
		objectPlayerCardOne.setAlignmentX(Component.CENTER_ALIGNMENT);
		objectPlayerCardOne.setBackground(Color.WHITE);
		objectPlayerCardOne.setBounds(296, 365, 100, 144);
		contentGamePanel.add(objectPlayerCardOne);
		
		// Game buttons
		// Reset the game - visible only when player looses all their chips
		buttonGameReset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonGameReset.setVisible(false);
		buttonGameReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Restarts the game by resetting the game to the initial state
				restartGame();
			}
		});
		buttonGameReset.setEnabled(false);
		buttonGameReset.setBounds(1086, 10, 90, 55);
		contentGamePanel.add(buttonGameReset);
		
		// Clear the bet Button
		buttonClearBet.setFocusTraversalKeysEnabled(false);
		buttonClearBet.setFocusPainted(false);
		buttonClearBet.setFocusable(false);
		buttonClearBet.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonClearBet.setForeground(Color.WHITE);
		buttonClearBet.setFont(new Font("ADLaM Display", Font.PLAIN, 30));
		buttonClearBet.setBorder(null);
		buttonClearBet.setBorderPainted(false);
		buttonClearBet.setContentAreaFilled(false);
		buttonClearBet.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonClearBet.setVisible(true);
		buttonClearBet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Resets the bet value to 0
				betAmount = 0;
				checkChipsImages();
				textLabelBetAmount.setText(Integer.toString(betAmount));
			}
		});
		buttonClearBet.setHorizontalAlignment(SwingConstants.CENTER);
		buttonClearBet.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonClearBet.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonClearBet.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/clear_button.png")));
		buttonClearBet.setBounds(1086, 535, 92, 92);
		contentGamePanel.add(buttonClearBet);
		
		// Hit Button
		buttonHit.setFocusTraversalKeysEnabled(false);
		buttonHit.setFocusPainted(false);
		buttonHit.setFocusable(false);
		buttonHit.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonHit.setForeground(Color.WHITE);
		buttonHit.setFont(new Font("ADLaM Display", Font.PLAIN, 30));
		buttonHit.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonHit.setBorder(null);
		buttonHit.setBorderPainted(false);
		buttonHit.setContentAreaFilled(false);
		buttonHit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonHit.setVisible(false);
		buttonHit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Checks if the Player can draw another card
				if (playersCardsCount < 5 & playersCards < 21) {
					// Gives the player new card
					gameGivePlayerCard();
					checkWin();
				}
			}
		});
		buttonHit.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/hit_button.png")));
		buttonHit.setHorizontalAlignment(SwingConstants.CENTER);
		buttonHit.setBounds(986, 535, 92, 92);
		contentGamePanel.add(buttonHit);
		
		// Fold Button
		buttonFold.setFocusTraversalKeysEnabled(false);
		buttonFold.setFocusPainted(false);
		buttonFold.setFocusable(false);
		buttonFold.setBorder(null);
		buttonFold.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonFold.setForeground(Color.WHITE);
		buttonFold.setFont(new Font("ADLaM Display", Font.PLAIN, 30));
		buttonFold.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonFold.setContentAreaFilled(false);
		buttonFold.setBorderPainted(false);
		buttonFold.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonFold.setVisible(false);
		buttonFold.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Player folds the cards
				foldCards();
			}
		});
		buttonFold.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/stand_button.png")));
		buttonFold.setHorizontalAlignment(SwingConstants.CENTER);
		buttonFold.setBounds(112, 535, 92, 92);
		contentGamePanel.add(buttonFold);
		
		// Deal button
		buttonDeal.setFocusTraversalKeysEnabled(false);
		buttonDeal.setFocusPainted(false);
		buttonDeal.setFocusable(false);
		buttonDeal.setHorizontalTextPosition(SwingConstants.CENTER);
		buttonDeal.setForeground(Color.WHITE);
		buttonDeal.setFont(new Font("ADLaM Display", Font.PLAIN, 30));
		buttonDeal.setAlignmentX(Component.CENTER_ALIGNMENT);
		buttonDeal.setBorder(null);
		buttonDeal.setBorderPainted(false);
		buttonDeal.setContentAreaFilled(false);
		buttonDeal.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonDeal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Checks if the Player doesn't already have cards and if the bet value is higher than 0
				if (playersCardsCount < 1 & betAmount > 0) {					
					// Resets all the Players and Dealers cards
					for (int i=1; i <= 5; i++) {
						handlePlayersCardImages("empty_card.png", i);
						handleDealersCardImages("empty_card.png", i);
					}
					
					// Subtracts the bet from players chips
					playersChips -= betAmount;
					textLabelPlayersChips.setText(""+playersChips);
					
					// Gives dealer one card at the beginning of the game
					gameGiveDealerCard();
					
					// Gives player two cards at the beginning of the game
					for (int j=0; j<2; j++) {
						gameGivePlayerCard();
					}
					
					// Enables the chips images depending on the size of the bet
					checkChipsImages();
				}
			}
		});
		buttonDeal.setHorizontalAlignment(SwingConstants.CENTER);
		buttonDeal.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/deal_button.png")));
		buttonDeal.setBounds(10, 535, 92, 92);
		contentGamePanel.add(buttonDeal);
		
		// Bet buttons
		// Bet 20 chips
		buttonChipsTwenty.setFocusTraversalKeysEnabled(false);
		buttonChipsTwenty.setFocusPainted(false);
		buttonChipsTwenty.setFocusable(false);
		buttonChipsTwenty.setBorder(null);
		buttonChipsTwenty.setBorderPainted(false);
		buttonChipsTwenty.setContentAreaFilled(false);
		buttonChipsTwenty.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonChipsTwenty.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Checks if player has enough chips to raise the bet
				if (betAmount < playersChips) {
					// Raises the bet by 20 chips
					betAmount += 20;
					checkChipsImages();
					textLabelBetAmount.setText(Integer.toString(betAmount));
				}
			}
		});
		buttonChipsTwenty.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/20_button.png")));
		buttonChipsTwenty.setHorizontalAlignment(SwingConstants.CENTER);
		buttonChipsTwenty.setBounds(1085, 414, 95, 95);
		contentGamePanel.add(buttonChipsTwenty);
		
		// Bet 100 chips
		buttonChipsOneHundred.setFocusTraversalKeysEnabled(false);
		buttonChipsOneHundred.setFocusPainted(false);
		buttonChipsOneHundred.setFocusable(false);
		buttonChipsOneHundred.setBorder(null);
		buttonChipsOneHundred.setBorderPainted(false);
		buttonChipsOneHundred.setContentAreaFilled(false);
		buttonChipsOneHundred.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonChipsOneHundred.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Checks if player has enough chips to raise the bet
				if (betAmount+100 <= playersChips) {
					// Raises the bet by 100 chips
					betAmount += 100;
					checkChipsImages();
					textLabelBetAmount.setText(Integer.toString(betAmount));
				}
			}
		});
		buttonChipsOneHundred.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/100_button.png")));
		buttonChipsOneHundred.setHorizontalAlignment(SwingConstants.CENTER);
		buttonChipsOneHundred.setBounds(1086, 312, 95, 95);
		contentGamePanel.add(buttonChipsOneHundred);
		
		// Bet 500 chips
		buttonChipsFivehundred.setFocusTraversalKeysEnabled(false);
		buttonChipsFivehundred.setFocusPainted(false);
		buttonChipsFivehundred.setFocusable(false);
		buttonChipsFivehundred.setBorder(null);
		buttonChipsFivehundred.setBorderPainted(false);
		buttonChipsFivehundred.setContentAreaFilled(false);
		buttonChipsFivehundred.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonChipsFivehundred.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Checks if player has enough chips to raise the bet
				if (betAmount+500 <= playersChips) {
					// Raises the bet by 500 chips
					betAmount += 500;
					checkChipsImages();
					textLabelBetAmount.setText(Integer.toString(betAmount));
				}
			}
		});
		buttonChipsFivehundred.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/500_button.png")));
		buttonChipsFivehundred.setHorizontalAlignment(SwingConstants.CENTER);
		buttonChipsFivehundred.setBounds(1086, 205, 95, 95);
		contentGamePanel.add(buttonChipsFivehundred);
		
		// Bet all the chips
		buttonChipsAllin.setFocusTraversalKeysEnabled(false);
		buttonChipsAllin.setFocusPainted(false);
		buttonChipsAllin.setFocusable(false);
		buttonChipsAllin.setBorder(null);
		buttonChipsAllin.setBorderPainted(false);
		buttonChipsAllin.setContentAreaFilled(false);
		buttonChipsAllin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		buttonChipsAllin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Sets the bet to the amount of players chips
				betAmount = playersChips;
				checkChipsImages();
				textLabelBetAmount.setText(Integer.toString(betAmount));
			}
		});
		buttonChipsAllin.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/buttons/all_in_button.png")));
		buttonChipsAllin.setHorizontalAlignment(SwingConstants.CENTER);
		buttonChipsAllin.setBounds(1086, 84, 95, 109);
		contentGamePanel.add(buttonChipsAllin);
		
		// Background - above the main background
		// Upper strip
		backgroundImageUpperPanelStrip.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/ui/gold_strip.png")));
		backgroundImageUpperPanelStrip.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundImageUpperPanelStrip.setBounds(0, 70, 1186, 12);
		contentGamePanel.add(backgroundImageUpperPanelStrip);
		
		// Bottom strip
		backgroundImageBottomPanelStrip.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundImageBottomPanelStrip.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/ui/gold_strip.png")));
		backgroundImageBottomPanelStrip.setBounds(0, 517, 1186, 12);
		contentGamePanel.add(backgroundImageBottomPanelStrip);
		
		// Game elements
		// Bet amount counter
		textLabelBetAmount.setHorizontalAlignment(SwingConstants.CENTER);
		textLabelBetAmount.setForeground(Color.WHITE);
		textLabelBetAmount.setFont(new Font("ADLaM Display", Font.BOLD, 30));
		textLabelBetAmount.setFocusable(false);
		textLabelBetAmount.setAlignmentX(0.5f);
		textLabelBetAmount.setBounds(435, 537, 541, 90);
		contentGamePanel.add(textLabelBetAmount);
		
		// Current bet value counter
		textLabelCurrentBetPanel.setHorizontalAlignment(SwingConstants.CENTER);
		textLabelCurrentBetPanel.setForeground(Color.WHITE);
		textLabelCurrentBetPanel.setFont(new Font("ADLaM Display", Font.BOLD, 30));
		textLabelCurrentBetPanel.setFocusable(false);
		textLabelCurrentBetPanel.setAlignmentX(0.5f);
		textLabelCurrentBetPanel.setBounds(212, 537, 238, 90);
		contentGamePanel.add(textLabelCurrentBetPanel);

		// Bet counter background
		backgroundImageBetAmountCounter.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/ui/current_bet_box.png")));
		backgroundImageBetAmountCounter.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundImageBetAmountCounter.setBounds(212, 537, 764, 90);
		contentGamePanel.add(backgroundImageBetAmountCounter);

		// Players chips label
		textLabelPlayersChips.setHorizontalAlignment(SwingConstants.CENTER);
		textLabelPlayersChips.setForeground(Color.WHITE);
		textLabelPlayersChips.setFont(new Font("ADLaM Display", Font.BOLD, 30));
		textLabelPlayersChips.setFocusable(false);
		textLabelPlayersChips.setAlignmentX(0.5f);
		textLabelPlayersChips.setBounds(95, 2, 265, 67);
		contentGamePanel.add(textLabelPlayersChips);

		// Players chips label background
		backgroundImagePlayersChips.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/ui/total_chips_box.png")));
		backgroundImagePlayersChips.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundImagePlayersChips.setBounds(10, 2, 350, 67);
		contentGamePanel.add(backgroundImagePlayersChips);

		// Dealers cards value
		textLabelDealersCardsValue.setHorizontalTextPosition(SwingConstants.CENTER);
		textLabelDealersCardsValue.setForeground(new Color(255, 255, 255));
		textLabelDealersCardsValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		textLabelDealersCardsValue.setHorizontalAlignment(SwingConstants.CENTER);
		textLabelDealersCardsValue.setFont(new Font("ADLaM Display", Font.PLAIN, 25));
		textLabelDealersCardsValue.setBounds(435, 237, 303, 40);
		contentGamePanel.add(textLabelDealersCardsValue);

		// Counter of players cards value
		textLabelPlayersCardsValue.setHorizontalTextPosition(SwingConstants.CENTER);
		textLabelPlayersCardsValue.setForeground(new Color(255, 255, 255));
		textLabelPlayersCardsValue.setAlignmentX(Component.CENTER_ALIGNMENT);
		textLabelPlayersCardsValue.setFont(new Font("ADLaM Display", Font.PLAIN, 25));
		textLabelPlayersCardsValue.setHorizontalAlignment(SwingConstants.CENTER);
		textLabelPlayersCardsValue.setBounds(435, 326, 303, 40);
		contentGamePanel.add(textLabelPlayersCardsValue);

		// Won / Lost Label
		textLabelWonLost.setVisible(false);
		textLabelWonLost.setBackground(new Color(0, 102, 0));
		textLabelWonLost.setOpaque(true);
		textLabelWonLost.setForeground(new Color(255, 255, 255));
		textLabelWonLost.setFont(new Font("ADLaM Display", Font.BOLD, 50));
		textLabelWonLost.setHorizontalAlignment(SwingConstants.CENTER);
		textLabelWonLost.setBounds(0, 70, 1186, 457);
		contentGamePanel.add(textLabelWonLost);
		
		// Game background images
		// Bottom panel 
		backgroundImageBottomPanel.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/ui/black_leather_panel.png")));
		backgroundImageBottomPanel.setBounds(0, 530, 1186, 107);
		contentGamePanel.add(backgroundImageBottomPanel);
		
		// Upper panel
		backgroundImageUpperPanel.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/ui/wooden_panel.png")));
		backgroundImageUpperPanel.setBounds(0, 0, 1186, 70);
		contentGamePanel.add(backgroundImageUpperPanel);
		
		// Main background image 
		backgroundImageTable.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/ui/background.png")));
		backgroundImageTable.setHorizontalTextPosition(SwingConstants.CENTER);
		backgroundImageTable.setHorizontalAlignment(SwingConstants.CENTER);
		backgroundImageTable.setBounds(0, 0, 1186, 637);
		contentGamePanel.add(backgroundImageTable);
	}

	// Method that gives the player a card
	public void gameGivePlayerCard() {
		// Handles buttons
		handleButtonsInNewGame();

		// Randomly chooses a new card
		Cards crdsP = new Cards();
		usedCards[playersCardsCount] = crdsP.getCard(usedCards);
		
		// Checks if the card was already used
		if (usedCards[playersCardsCount].equals("0")) {
			gameGivePlayerCard();
		} else {
			// Gets the file path for card
			pathToNewCard = crdsP.getFilePath();

			// Changes the image of the correct card
			handlePlayersCardImages(pathToNewCard, playersCardsCount+1);

			// Checks if the player score is 10, if so gives the ace a value od 11
			if (playersCards == 10 & crdsP.getCardValue() == 1) {
				playersCards += 11;
			} else {
				// Otherwise the value will be taken from the c.value
				playersCards += crdsP.getCardValue();
			}
			
			// Updates the player's cards value label
			textLabelPlayersCardsValue.setText("Your cards: " + playersCards);
			
			// Adds one to the amount of drawn cards
			playersCardsCount += 1;
			
			// Checks if the player won
			checkWin();
		}
	}
	
	// Method that gives the dealer a card
		public void gameGiveDealerCard() {
			// Randomly chooses a new card
			Cards crdsD = new Cards();
			usedCards[dealersCardsCount+5] = crdsD.getCard(usedCards);
			if (usedCards[dealersCardsCount+5].equals("0")) {
				gameGiveDealerCard();
			} else {
				// Gets the file path for card
				pathToNewCard = crdsD.getFilePath();

				// Changes the image of the correct card
				handleDealersCardImages(pathToNewCard, dealersCardsCount+1);

				// Checks if the dealers score is 10, if so gives the ace a value of 11
				if (dealersCards == 10 & crdsD.getCardValue() == 1) {
					dealersCards += 11;
				} else {
					// Otherwise the value will be taken from the c.value 
					dealersCards += crdsD.getCardValue();
				}

				// Updates the dealer's cards value label
				textLabelDealersCardsValue.setText("Dealers cards: " + dealersCards);
				
				// Adds one to the amount of drawn cards
				dealersCardsCount += 1;
			}
		}
	
		// Function that handles the images of players cards
	public void handlePlayersCardImages(String cardPath, int idOfCard) {
		switch(idOfCard) {
		case 1:
			objectPlayerCardOne.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 2:
			objectPlayerCardTwo.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 3:
			objectPlayerCardThree.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 4:
			objectPlayerCardFour.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 5:
			objectPlayerCardFive.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		default:
			// Empty
			break;
		}
	}
	
	// Function that handles the images of dealers cards
	public void handleDealersCardImages(String cardPath, int idOfCard) {
		switch(idOfCard) {
		case 1:
			objectDealerCardOne.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 2:
			objectDealerCardTwo.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 3:
			objectDealerCardThree.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 4:
			objectDealerCardFour.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		case 5:
			objectDealerCardFive.setIcon(new ImageIcon(MainWindow.class.getResource("/imgs/cards/" + cardPath)));
			break;
		default:
			// Empty
			break;
		}
	}

	public void handleButtonsInNewGame() {				
		// Removes the game result text
		textLabelWonLost.setVisible(false);

		// Disables the buttons that control the options before game starts
		buttonDeal.setVisible(false);
		buttonClearBet.setVisible(false);
		buttonChipsTwenty.setVisible(false);
		buttonChipsOneHundred.setVisible(false);
		buttonChipsFivehundred.setVisible(false);
		buttonChipsAllin.setVisible(false);

		// Enables buttons needed when in game
		buttonHit.setVisible(true);
		buttonFold.setVisible(true);
	}

	public void handleButtonsBeforeNewGame() {
		// Enables the buttons that control the options before game starts
		buttonDeal.setVisible(true);
		buttonClearBet.setVisible(true);
		buttonChipsTwenty.setVisible(true);
		buttonChipsOneHundred.setVisible(true);
		buttonChipsFivehundred.setVisible(true);
		buttonChipsAllin.setVisible(true);

		// Disables buttons needed when in game
		buttonHit.setVisible(false);
		buttonFold.setVisible(false);
	}

	// resets values between rounds
	public void resetGameValues() {
		// Resets players values
		betAmount = 0;
		playersCards = 0;
		playersCardsCount = 0;
		
		// Resets dealers values
		dealersCards = 0;
		dealersCardsCount = 0;
		
		// Resets other values
		usedCards = new String[10];	// Removes all the cards drawn in game

		// Updates the labels
		textLabelBetAmount.setText("" + betAmount);
		textLabelPlayersChips.setText(""+playersChips);
	}

	// restarts game after player looses all money
	public void restartGame() {
		// Resets players values
		playersChips = 200;
		betAmount = 0;
		playersCards = 0;
		playersCardsCount = 0;
		usedCards = new String[10];	// Removes all the cards drawn in game
		
		// Resets dealers values
		dealersCards = 0;
		dealersCardsCount = 0;

		// Updates the labels
		textLabelBetAmount.setText("" + betAmount);
		textLabelPlayersChips.setText(""+playersChips);
		textLabelWonLost.setVisible(false);

		// Removes the reset button
		buttonGameReset.setVisible(false);
	}
	
	// Function that handles the chips images on the left side based on the bet value
	public void checkChipsImages() {
		if (betAmount >= 500) {
			imageChipFiveHundred.setVisible(true);
			imageChipOneHundred.setVisible(true);
			imageChipTwenty.setVisible(true);
		} else if (betAmount >= 100) {
			imageChipFiveHundred.setVisible(false);
			imageChipOneHundred.setVisible(true);
			imageChipTwenty.setVisible(true);
		} else if (betAmount >= 20) {
			imageChipFiveHundred.setVisible(false);
			imageChipOneHundred.setVisible(false);
			imageChipTwenty.setVisible(true);
		} else {
			imageChipFiveHundred.setVisible(false);
			imageChipOneHundred.setVisible(false);
			imageChipTwenty.setVisible(false);
		}
	}
	
	// Function that allows user to fold their cards
	public void foldCards() {
		// Gives a card to the dealer while his cards value is lower than players
		while (playersCards >= dealersCards & dealersCardsCount < 5) {
			gameGiveDealerCard();
		}
		
		// Compares the Players and Dealers cards
		if (dealersCards > 21 & playersCards <= 21) {
			gameWon();
		} else if (playersCards < dealersCards & dealersCards <= 21) {
			gameLost();
		} else if (playersCards == dealersCards & dealersCardsCount == 5) {
			gameDraw();
		} else if (playersCards > dealersCards & dealersCardsCount == 5) {
			gameWon();
		} else {
			System.err.println("Error 01 - Unexpected behaviour - please report");
		}
	}

	// Player runs out of chips
	public void noMoreChips() {
		// Enables the game reset button
		buttonGameReset.setVisible(true);
		buttonGameReset.setEnabled(true);;

		// Disables buttons needed when in game
		buttonHit.setVisible(false);
		buttonFold.setVisible(false);

		// Disables the buttons that control the options before game starts
		buttonDeal.setVisible(false);
		buttonClearBet.setVisible(false);
		buttonChipsTwenty.setVisible(false);
		buttonChipsOneHundred.setVisible(false);
		buttonChipsAllin.setVisible(false);
	}
	
	// Checks if the Player / Dealer won
	public void checkWin() {
		// Checks the amount of cards drawn by player
		if (playersCardsCount == 5) {
			buttonHit.setVisible(false);
		}
		
		// Checks players cards value and amount of chips
		if (dealersCards < playersCards & playersCards == 21) {
			gameWon();
		} else if (playersChips <= 0 & playersCards > 21) {
			gameLost();
		} else if (playersCards > 21) {
			gameLost();
		}
	}

	// Player wins the game
	public void gameWon() {
		// Shows YOU WIN text with green background
		textLabelWonLost.setVisible(true);
		textLabelWonLost.setText("You win!");
		textLabelWonLost.setBackground(new Color(0, 153, 51));
		
		handleButtonsBeforeNewGame();	// Resets the button controls
		playersChips += betAmount*2; 	// Player wins chips 
		resetGameValues();				// Resets the game values to their initial states
		checkChipsImages();				// Removes the chips images from left side
	}

	// Player loses the game
	public void gameLost() {
		// Checks if the player ran out of chips
		if (playersChips <= 0) {
			noMoreChips();				// Runs a method that handles the game in case the player has no more chips
			checkChipsImages();			// Removes the chips images from left side
		}
		// Shows YOU LOOSE text with red background
		textLabelWonLost.setVisible(true);
		textLabelWonLost.setText("You loose!");
		textLabelWonLost.setBackground(new Color(255, 51, 51));
		
		handleButtonsBeforeNewGame();	// Resets the button controls
		resetGameValues();				// Resets the game values to their initial states
		checkChipsImages();				// Removes the chips images from left side
	}
	
	// Player and Dealer draws game
	public void gameDraw() {
		// Shows DRAW text with brownish background
		textLabelWonLost.setVisible(true);
		textLabelWonLost.setText("Draw!");
		textLabelWonLost.setBackground(new Color(155,100,50));
		
		handleButtonsBeforeNewGame();	// Resets the button controls
		playersChips += betAmount; 		// Player gets back their chips
		resetGameValues();				// Resets the game values to their initial states
		checkChipsImages();				// Removes the chips images from left side
	}
}
