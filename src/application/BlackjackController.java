/*
 * Created by Austin Pennartz - alm996
 * 
 * This is a fully functioning Blackjack simulator.
 * It consists of one player (the user) and one AI (the dealer)
 * You can enter an amount to bet and see your earnings just below that
 * The options during play are: hit, stand, and double down
 * Splitting is currently not allowed
  //
 * 
 * 
 */
package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

//This is the fully functional Blackjack class that handles all operations
public class BlackjackController implements Initializable {
	// Several buttons used in the game
	@FXML
	private AnchorPane rootPane;
	@FXML
	private Button returnButton;
	@FXML
	private Button doubledownButton;
	@FXML
	private Button hitButton;
	@FXML
	private Button standButton;
	@FXML
	private Button playagainButton;

	// several labels that hide and show at appropriate times
	@FXML
	private Label dealersCards;
	@FXML
	private Label playersCards;
	@FXML
	private Label startLabel;
	@FXML
	private Label yourTurn;
	@FXML
	private Label youWin;
	@FXML
	private Label youLose;
	@FXML
	private Label youWinnot21;
	@FXML
	private Label youLosenot21;
	@FXML
	private Label youPush;
	@FXML
	private Label youBust;
	@FXML
	private Label playAgain;
	@FXML
	private Label enterBet;
	@FXML
	private ImageView displayCard;

	// winnings result
	@FXML
	private TextArea betResult;

	// Total cards for the dealer and player
	private int dealercardNumber = 1;
	@FXML
	private ImageView dealercard1;
	@FXML
	private ImageView dealercard2;
	@FXML
	private ImageView dealercard3;
	@FXML
	private ImageView dealercard4;
	@FXML
	private ImageView dealercard5;
	@FXML
	private ImageView dealercard6;
	@FXML
	private ImageView dealercard7;

	private int playercardNumber = 1;
	@FXML
	private ImageView playercard1;
	@FXML
	private ImageView playercard2;
	@FXML
	private ImageView playercard3;
	@FXML
	private ImageView playercard4;
	@FXML
	private ImageView playercard5;
	@FXML
	private ImageView playercard6;
	@FXML
	private ImageView playercard7;
	@FXML
	private ImageView ghostcard;
	@FXML
	private ImageView backround;

	// more misc items needed for the game
	@FXML
	private Button nextButton;
	@FXML
	private Button startButton;
	@FXML
	private TextField betAmount;

	private String dealersPath;
	private boolean doubledownFlag;
	private int bettingNumber;
	private boolean playeraceFlag;
	private boolean dealeraceFlag;
	private int lastNum;
	private int playerslastNum;
	private int dealerslastNum;
	private int playertotal;
	private int dealertotal;
	private int lastchar;
	private boolean maybeD;
	private boolean maybeP;
	private int DorP;
	private String basePath;

	Map<Integer, Character> s = new HashMap<Integer, Character>();
	Map<Integer, Character> h = new HashMap<Integer, Character>();
	Map<Integer, Character> c = new HashMap<Integer, Character>();
	Map<Integer, Character> d = new HashMap<Integer, Character>();

	public int playerXOffset = 494;
	public int playerYOffset = 554;
	public int dealerOffset = 474;

	// deals the dealer a card and gives the second one face down
	public void dealdealerCard(String path) throws IOException {

		if (path == "bin\\assets\\cards") {
			path = "bin\\assets\\cards\\4D.png";
			dealertotal -= dealerslastNum;
			dealertotal += 4;
		}

		InputStream stream = new FileInputStream(path);

		Image image = new Image(stream);
		if (dealercardNumber == 1)
			dealercard1.setImage(image);
		else if (dealercardNumber == 2)
			System.out.println("");
		else if (dealercardNumber == 3)
			dealercard3.setImage(image);
		else if (dealercardNumber == 4)
			dealercard4.setImage(image);
		else if (dealercardNumber == 5)
			dealercard5.setImage(image);
		else if (dealercardNumber == 6)
			dealercard6.setImage(image);
		else if (dealercardNumber == 7)
			dealercard7.setImage(image);

		TranslateTransition transition1 = new TranslateTransition();
		transition1.setDuration(Duration.seconds(.5));
		if (dealercardNumber == 1)
			transition1.setNode(dealercard1);
		else if (dealercardNumber == 2)
			transition1.setNode(dealercard2);
		else if (dealercardNumber == 3)
			transition1.setNode(dealercard3);
		else if (dealercardNumber == 4)
			transition1.setNode(dealercard4);
		else if (dealercardNumber == 5)
			transition1.setNode(dealercard5);
		else if (dealercardNumber == 6)
			transition1.setNode(dealercard6);
		else if (dealercardNumber == 7)
			transition1.setNode(dealercard7);

		transition1.setToX(dealerOffset);
		transition1.setToY(45);
		dealerOffset += 25;
		RotateTransition transition2 = new RotateTransition(Duration.seconds(.5), ghostcard);
		RotateTransition transition3 = new RotateTransition(Duration.seconds(2), ghostcard);
		FadeTransition ft = new FadeTransition(Duration.millis(1000), yourTurn);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);

		if (dealercardNumber == 1)
			transition2 = new RotateTransition(Duration.seconds(.5), dealercard1);
		else if (dealercardNumber == 2)
			transition2 = new RotateTransition(Duration.seconds(.5), dealercard2);
		else if (dealercardNumber == 3)
			transition2 = new RotateTransition(Duration.seconds(.5), dealercard3);
		else if (dealercardNumber == 4)
			transition2 = new RotateTransition(Duration.seconds(.5), dealercard4);
		else if (dealercardNumber == 5)
			transition2 = new RotateTransition(Duration.seconds(.5), dealercard5);
		else if (dealercardNumber == 6)
			transition2 = new RotateTransition(Duration.seconds(.5), dealercard6);
		else if (dealercardNumber == 7)
			transition2 = new RotateTransition(Duration.seconds(.5), dealercard7);

		transition2.setByAngle(360);
		transition3.setByAngle(320);

		if (dealercardNumber == 1) {
			ParallelTransition p = new ParallelTransition(transition1, transition2);
			SequentialTransition t = new SequentialTransition(transition3, p,
					new PauseTransition(Duration.millis(2000)));
			t.play();
		} else if (dealercardNumber == 2) {
			dealersPath = path;
			ParallelTransition p = new ParallelTransition(transition1, transition2);
			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(3000)),
					p, ft);

			t.play();
		} else {
			ParallelTransition p = new ParallelTransition(transition1, transition2);
			transition3 = new RotateTransition(Duration.seconds(2), ghostcard);

			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(1000)),
					p);
			t.play();
		}

		dealercardNumber++;
	}

	// deals a card to the player on the start of the game and on 'hit'
	public void dealplayerCard(String path) throws IOException { // deals a card to a party, probably called from

		if (path == "bin\\assets\\cards\\") {
			// System.out.println("Adjusting");
			playertotal -= playerslastNum;
			playertotal += 9;
			path = "bin\\assets\\cards\\9D.png";
		}
		InputStream stream = new FileInputStream(path);
		Image image = new Image(stream);

		if (playercardNumber == 1)
			playercard1.setImage(image);
		else if (playercardNumber == 2)
			playercard2.setImage(image);
		else if (playercardNumber == 3)
			playercard3.setImage(image);
		else if (playercardNumber == 4)
			playercard4.setImage(image);
		else if (playercardNumber == 5)
			playercard5.setImage(image);
		else if (playercardNumber == 6)
			playercard6.setImage(image);
		else if (playercardNumber == 7)
			playercard7.setImage(image);

		TranslateTransition transition1 = new TranslateTransition();
		// transition1.setDuration(Duration.seconds(.5));

		if (playercardNumber == 1) {
			transition1.setDuration(Duration.seconds(.5));
			transition1.setNode(playercard1);

		} else if (playercardNumber == 2) {
			transition1.setDuration(Duration.seconds(.5));
			transition1.setNode(playercard2);
		} else if (playercardNumber == 3)
			transition1.setNode(playercard3);
		else if (playercardNumber == 4)
			transition1.setNode(playercard4);
		else if (playercardNumber == 5)
			transition1.setNode(playercard5);
		else if (playercardNumber == 6)
			transition1.setNode(playercard6);
		else if (playercardNumber == 7)
			transition1.setNode(playercard7);

		transition1.setToX(playerXOffset);
		playerXOffset += 25;
		transition1.setToY(playerYOffset);
		playerYOffset -= 40;
		RotateTransition transition2 = new RotateTransition(Duration.seconds(.5), ghostcard);
		if (playercardNumber == 1)
			transition2 = new RotateTransition(Duration.seconds(.5), playercard1);
		else if (playercardNumber == 2)
			transition2 = new RotateTransition(Duration.seconds(.5), playercard2);
		else if (playercardNumber == 3)
			transition2 = new RotateTransition(Duration.seconds(.5), playercard3);
		else if (playercardNumber == 4)
			transition2 = new RotateTransition(Duration.seconds(.5), playercard4);
		else if (playercardNumber == 5)
			transition2 = new RotateTransition(Duration.seconds(.5), playercard5);
		else if (playercardNumber == 6)
			transition2 = new RotateTransition(Duration.seconds(.5), playercard6);
		else if (playercardNumber == 7)
			transition2 = new RotateTransition(Duration.seconds(.5), playercard7);

		if (doubledownFlag == true) {
			transition2.setByAngle(450);
			transition1.setToX(playerXOffset + 20);
		} else {
			transition2.setByAngle(360);
		}

		RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);
		transition3.setByAngle(360);

		if (playercardNumber == 1) {
			ParallelTransition p = new ParallelTransition(transition1, transition2);
			SequentialTransition t = new SequentialTransition(transition3, p);
			t.play();
		} else if (playercardNumber == 2) {
			ParallelTransition p = new ParallelTransition(transition1, transition2);
			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(3000)),
					p);
			t.play();
		} else {
			ParallelTransition p = new ParallelTransition(transition1, transition2);
			SequentialTransition t = new SequentialTransition(transition3, p);
			t.play();
		}

		playercardNumber++;
	}

	// handles the case that the game is over and hides much of the UI elements
	public void removeallCards() {// resets the board for the next game

		/*
		 * https://docs.oracle.com/javafx/2/api/javafx/animation/TranslateTransition.
		 * html
		 * https://docs.oracle.com/javafx/2/api/javafx/animation/RotateTransition.html
		 * https://docs.oracle.com/javafx/2/api/javafx/animation/ParallelTransition.html
		 * https://docs.oracle.com/javafx/2/api/javafx/animation/SequentialTransition.
		 * html
		 * 
		 * 
		 * InputStream stream = new FileInputStream(path); Image image = new
		 * Image(stream); playercard1.setImage(image);
		 * 
		 * TranslateTransition transition1 = new TranslateTransition();
		 * transition1.setDuration(Duration.seconds(.5));
		 * transition1.setNode(playercard1); transition1.setToX(playerXOffset);
		 * 
		 * RotateTransition transition2 = new RotateTransition(Duration.seconds(.5),
		 * playercard1); transition2.setByAngle(360);
		 * 
		 * ParallelTransition p = new ParallelTransition(transition1, transition2);
		 * p.play();
		 */

		/*
		 * dealercard1.setVisible(false); dealercard2.setVisible(false);
		 * dealercard3.setVisible(false); dealercard4.setVisible(false);
		 * dealercard5.setVisible(false); dealercard6.setVisible(false);
		 * dealercard7.setVisible(false);
		 * 
		 * playercard1.setVisible(false); playercard2.setVisible(false);
		 * playercard3.setVisible(false); playercard4.setVisible(false);
		 * playercard5.setVisible(false); playercard6.setVisible(false);
		 * playercard7.setVisible(false);
		 */

		dealersCards.setVisible(false);
		playersCards.setVisible(false);
		yourTurn.setVisible(false);
		hitButton.setVisible(false);
		hitButton.setDisable(true);
		standButton.setVisible(false);
		standButton.setDisable(true);
		doubledownButton.setVisible(false);
		doubledownButton.setDisable(true);

	}

	// displays the outcome of the game if the dealer of user immediately have 21
	public void displayOutcome(int result, String dealerPath) throws FileNotFoundException {// shows whether the user
																							// won or lost
		if (result == 0) {
			InputStream stream = new FileInputStream(dealerPath);
			Image image = new Image(stream);
			dealercard2.setImage(image);
			FadeTransition ft = new FadeTransition(Duration.millis(1000), youLose);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);

			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(3500)),
					ft);
			t.play();
			yourTurn.setVisible(false);
			youLose.setVisible(true);
			bettingNumber = 0;
			endGame();

		} else if (result == 100) {
			FadeTransition ft = new FadeTransition(Duration.millis(1000), youWin);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);

			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(3000)),
					ft);
			t.play();
			yourTurn.setVisible(false);
			youWin.setVisible(true);
			bettingNumber *= 1.5;
			endGame();

		} else if (result == 50) {
			InputStream stream = new FileInputStream(dealerPath);
			Image image = new Image(stream);
			dealercard2.setImage(image);
			FadeTransition ft = new FadeTransition(Duration.millis(1000), youPush);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);

			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(3000)),
					ft);
			t.play();
			yourTurn.setVisible(false);
			youPush.setVisible(true);
			endGame();
		}
	}

	public void setstartAmount() { // user can select the amount of 'money' they want to begin the game with, max
									// $1000

	}

	// handles the case that the user wants another card
	public void Hit() throws IOException {
		yourTurn.setVisible(false);
		String playersCard = generateCard(5);
		dealplayerCard(playersCard);
		// System.out.println(playeraceFlag + " before");
		if (playertotal > 21 && playeraceFlag == false) {

			FadeTransition ft = new FadeTransition(Duration.millis(1000), youBust);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);
			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(0)), ft);
			t.play();
			youBust.setVisible(true);
			bettingNumber = 0;
			endGame();
			// endGame(0);
		} else if (playertotal > 21 && playeraceFlag == true) {
			playertotal -= 10;
			playeraceFlag = false;
		}
		if (playertotal == 21) {
			//
		}
	}

	// handles the case that the user doubles down and cant draw anymore cards
	public void doubleDown() throws IOException {
		bettingNumber *= 2;
		betAmount.setText(String.valueOf(bettingNumber));
		doubledownFlag = true;

		yourTurn.setVisible(false);
		String playersCard = generateCard(5);
		dealplayerCard(playersCard);
		if (playertotal > 21 && playeraceFlag == false) {

			FadeTransition ft = new FadeTransition(Duration.millis(1000), youBust);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);
			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(0)), ft);
			t.play();
			youBust.setVisible(true);
			bettingNumber = 0;
			endGame();

		} else if (playertotal > 21 && playeraceFlag == true) {
			playertotal -= 10;
			playeraceFlag = false;
			;
			Stand();
			endGame();
		}

		else if (playertotal <= 21) {
			// System.out.println("entered less than 21 dd");
			Stand();
			endGame();
		}
	}

	// handles the case that the user wants to stand and let the dealer draw
	public void Stand() throws IOException {
		yourTurn.setVisible(false);
		InputStream stream = new FileInputStream(dealersPath);
		Image image = new Image(stream);
		dealercard2.setImage(image);
		if (dealertotal <= 16) {

			while (dealertotal <= 16) {
				String dealersCard = generateCard(2);
				dealdealerCard(dealersCard);
			}
		}
		if (dealertotal >= 17 && dealertotal <= 21) {
			if (dealertotal > playertotal) {
				FadeTransition ft = new FadeTransition(Duration.millis(1000), youLosenot21);
				ft.setFromValue(0.0);
				ft.setToValue(1.0);
				RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);
				youLosenot21.setVisible(true);
				SequentialTransition t = new SequentialTransition(transition3,
						new PauseTransition(Duration.millis(2000)), ft);
				t.play();
				bettingNumber = 0;
				endGame();

				// System.out.println("dealerWIns");
			}
			if (dealertotal < playertotal) {
				FadeTransition ft = new FadeTransition(Duration.millis(1000), youWinnot21);
				ft.setFromValue(0.0);
				ft.setToValue(1.0);
				RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);
				youWinnot21.setVisible(true);
				SequentialTransition t = new SequentialTransition(transition3,
						new PauseTransition(Duration.millis(2000)), ft);
				t.play();
				bettingNumber *= 2;
				endGame();

				// System.out.println("playerWIns");
			}
			if (dealertotal == playertotal) {
				FadeTransition ft = new FadeTransition(Duration.millis(1000), youPush);
				ft.setFromValue(0.0);
				ft.setToValue(1.0);
				RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);
				youPush.setVisible(true);
				SequentialTransition t = new SequentialTransition(transition3,
						new PauseTransition(Duration.millis(2000)), ft);
				t.play();
				endGame();
			}

		}
		if (dealertotal > 21 && dealeraceFlag == false) {
			FadeTransition ft = new FadeTransition(Duration.millis(3000), youWinnot21);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			RotateTransition transition3 = new RotateTransition(Duration.seconds(.5), ghostcard);
			youWinnot21.setVisible(true);
			SequentialTransition t = new SequentialTransition(transition3, new PauseTransition(Duration.millis(2000)),
					ft);
			t.play();
			bettingNumber *= 2;
			endGame();

		} else if (dealeraceFlag == true && dealertotal > 21) {
			dealertotal -= 10;
			dealeraceFlag = false;
			Stand();
		}
	}

	// handles the event that the game is over
	public void endGame() {
		removeallCards();
		betResult.setText("$" + String.valueOf(bettingNumber));
		betResult.setManaged(false);

	}

	// handles the beginning of the game including the first two cards dealt to
	// player and dealer
	public void startGame() throws IOException, InterruptedException { // handles startup of game.

		String playersCard = generateCard(5);
		dealplayerCard(playersCard);

		String dealersCard = generateCard(2);
		dealdealerCard(dealersCard);

		playersCard = generateCard(5);
		dealplayerCard(playersCard);

		dealersCard = generateCard(2);
		dealdealerCard(dealersCard);

		startButton.setDisable(true);
		startButton.setVisible(false);
		startButton.setManaged(false);
		enterBet.setVisible(false);

		String bettingNumberString = betAmount.getText().toString();
		if (bettingNumberString != "") {
			bettingNumber = Integer.parseInt(bettingNumberString);
		} else {
			bettingNumber = 100;
		}

		betAmount.setDisable(true);
		startLabel.setVisible(false);
		dealersCards.setVisible(true);
		playersCards.setVisible(true);
		hitButton.setVisible(true);
		hitButton.setDisable(false);
		standButton.setVisible(true);
		standButton.setDisable(false);
		doubledownButton.setVisible(true);
		doubledownButton.setDisable(false);
		yourTurn.setVisible(true);

		if (playertotal == 21 && dealertotal == 21) {
			endGame();
			displayOutcome(50, dealersCard);
		} else if (playertotal == 21) {
			endGame();
			displayOutcome(100, dealersCard);

		} else if (dealertotal == 21) {
			endGame();
			displayOutcome(0, dealersCard);

		}
		// System.out.println(playertotal);
		// System.out.println(dealertotal);
	}

	// generates a random card for whoever is recieving
	public String generateCard(int DorP) { // randomly generates a card and returns the string pathname to the
											// respective
		// picture
		String pathBase = "bin\\assets\\cards\\";
		Random rand = new Random();

		int randomNum = rand.nextInt(13) + 1;
		int randomintSuit = rand.nextInt(4) + 1;
		// System.out.println(randomintSuit + " " + randomNum);
		while (randomNum == lastNum && randomintSuit == lastchar) {
			randomNum = rand.nextInt(13) + 1;
			randomintSuit = rand.nextInt(4) + 1;
		}
		// System.out.println(randomintSuit + " " + randomNum + "again");
		char randomSuit = 'E';

		switch (randomintSuit) {
		case 1:
			randomSuit = 'C';
			if (c.containsKey(randomNum)) {
				c.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;
				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;
				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;
				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;
				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
					playerslastNum = randomNum;
					dealerslastNum = randomNum;
				}
				break;

			} else if (!c.containsKey(randomNum)) {

				while (!c.containsKey(randomNum)) {
					// System.out.println("Generating random number");
					randomNum = rand.nextInt(13) + 1;
				}
				c.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;
				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;
				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;
				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;
				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
				}
				playerslastNum = randomNum;
				dealerslastNum = randomNum;

			}

			break;
		case 2:
			randomSuit = 'D';
			if (d.containsKey(randomNum)) {
				d.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;

				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
					playerslastNum = randomNum;
					dealerslastNum = randomNum;

				}
				break;
			} else if (!c.containsKey(randomNum)) {

				while (!c.containsKey(randomNum)) {
					// System.out.println("Generating random number");
					randomNum = rand.nextInt(13) + 1;
				}
				d.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;

				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
					playerslastNum = randomNum;
					dealerslastNum = randomNum;

				}
			}

			break;
		case 3:
			randomSuit = 'H';
			if (h.containsKey(randomNum)) {
				h.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;

				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
					playerslastNum = randomNum;
					dealerslastNum = randomNum;

				}
				break;
			} else if (!c.containsKey(randomNum)) {

				while (!h.containsKey(randomNum)) {
					// System.out.println("Generating random number");
					randomNum = rand.nextInt(13) + 1;
				}
				h.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;

				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
					playerslastNum = randomNum;
					dealerslastNum = randomNum;

				}
			}

			break;
		case 4:
			randomSuit = 'S';
			if (s.containsKey(randomNum)) {
				s.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;

				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
					playerslastNum = randomNum;
					dealerslastNum = randomNum;

				}
				break;
			} else if (!c.containsKey(randomNum)) {

				while (!s.containsKey(randomNum)) {
					// System.out.println("Generating random number");
					randomNum = rand.nextInt(13) + 1;
				}
				s.remove(randomNum);
				if (randomNum == 1) {
					if (DorP == 5)
						playeraceFlag = true;
					if (DorP == 2)
						dealeraceFlag = true;
					pathBase += "A" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 11;
					dealerslastNum = 11;

				} else if (randomNum == 11) {
					pathBase += "J" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 12) {
					pathBase += "Q" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else if (randomNum == 13) {
					pathBase += "K" + String.valueOf(randomSuit) + ".png";
					playerslastNum = 10;
					dealerslastNum = 10;

				} else {
					pathBase += String.valueOf(randomNum) + String.valueOf(randomSuit) + ".png";
					playerslastNum = randomNum;
					dealerslastNum = randomNum;

				}
			}

			break;
		default:
			// System.out.println("entered default");
			break;

		}
		lastNum = randomNum;
		lastchar = randomintSuit;
		if (pathBase.isEmpty())
			pathBase = generateCard(DorP);
		if (DorP == 5)
			playertotal += playerslastNum;
		if (DorP == 2)
			dealertotal += dealerslastNum;
		return pathBase;
		/*
		 * if (randomNum == 2 || randomNum == 3 || randomNum == 4 || randomNum == 5 ||
		 * randomNum == 6 || randomNum == 7 || randomNum == 8 || randomNum == 9 ||
		 * randomNum == 10) { pathBase += String.valueOf(randomNum) +
		 * String.valueOf(randomSuit) + ".png";
		 * 
		 * return pathBase; } else if(randomNum == 1) { pathBase += "A" +
		 * String.valueOf(randomSuit) + ".png"; return pathBase; }
		 */

	}

	// generates an entire deck of cards split into 4 hashmaps for each suit
	public void populateCards() { // populates the deck of cards into maps of the 4 suits
		c.put(1, 'C');
		d.put(1, 'D');
		h.put(1, 'H');
		s.put(1, 'S');
		c.put(2, 'C');
		d.put(2, 'D');
		h.put(2, 'H');
		s.put(2, 'S');
		c.put(3, 'C');
		d.put(3, 'D');
		h.put(3, 'H');
		s.put(3, 'S');
		c.put(4, 'C');
		d.put(4, 'D');
		h.put(4, 'H');
		s.put(4, 'S');

		c.put(5, 'C');
		d.put(5, 'D');
		h.put(5, 'H');
		s.put(5, 'S');
		d.put(6, 'C');
		d.put(6, 'D');
		h.put(6, 'H');
		s.put(6, 'S');
		c.put(7, 'C');
		d.put(7, 'D');
		h.put(7, 'H');
		s.put(7, 'S');
		c.put(8, 'C');
		d.put(8, 'D');
		h.put(8, 'H');
		s.put(8, 'S');

		c.put(9, 'C');
		d.put(9, 'D');
		h.put(9, 'H');
		s.put(9, 'S');
		c.put(10, 'C');
		d.put(10, 'D');
		h.put(10, 'H');
		s.put(10, 'S');
		c.put(11, 'C');
		d.put(11, 'D');
		h.put(11, 'H');
		s.put(11, 'S');
		c.put(12, 'C');
		d.put(12, 'D');
		h.put(12, 'H');
		s.put(12, 'S');
		c.put(13, 'C');
		d.put(13, 'D');
		h.put(13, 'H');
		s.put(13, 'S');
	}

	// initializes the startup procedure of the game
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		populateCards();
		dealersCards.setVisible(false);
		playersCards.setVisible(false);

		basePath = new File("").getAbsolutePath() + "\\";

		youWin.setVisible(false);
		youLose.setVisible(false);
		youWinnot21.setVisible(false);
		youLosenot21.setVisible(false);
		yourTurn.setVisible(false);
		youBust.setVisible(false);
		youPush.setVisible(false);
		playAgain.setVisible(false);

		playagainButton.setVisible(false);
		playagainButton.setDisable(true);
		nextButton.setVisible(false);
		nextButton.setDisable(true);
		hitButton.setVisible(false);
		hitButton.setDisable(true);
		standButton.setVisible(false);
		standButton.setDisable(true);
		doubledownButton.setVisible(false);
		doubledownButton.setDisable(true);
		playeraceFlag = false;
		dealeraceFlag = false;

		InputStream stream1;
		try {
			stream1 = new FileInputStream(basePath + "bin\\assets\\cards\\red_back.png");
			// System.out.println(basePath + "bin\\assets\\cards\\red_back.png");
			Image image1 = new Image(stream1);
			displayCard.setImage(image1);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream stream3;
		try {
			stream3 = new FileInputStream(basePath + "bin\\assets\\ui\\60-6100_lg2_2000x.jpg");
			Image image = new Image(stream3);
			backround.setImage(image);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		InputStream stream2;
		try {
			stream2 = new FileInputStream(basePath + "bin\\assets\\cards\\red_back.png");
			Image image2 = new Image(stream2);
			dealercard2.setImage(image2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void returnHome() {
		Stage stage = (Stage) returnButton.getScene().getWindow();

		FourCards main1 = new FourCards();
		main1.start(stage);
	}

}