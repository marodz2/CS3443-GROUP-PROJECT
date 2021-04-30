/*	Anna Moser - gfx077
 * How to play War (from bicyclecards.com): The deck is divided evenly, with each player receiving 26 cards. 
 * 	Each player turns up a card at the same time and the player with the higher card takes both cards and 
 * 	puts them at the bottom of the stack. If the cards are the same rank it is War. Each player turns one card 
 * 	face down and one card face up. The player with the higher card takes both piles (and if the cards are equal 
 * 	this is repeated. The game ends when one player has won all the cards   
 */

package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Node;
//This class handles the War gamemode and displays whats happening on the console

public class WarController implements Initializable {

	@FXML
	private TextField ProgramCount;
	@FXML
	private TextField PlayerCount;
	@FXML
	private ImageView ProgramDisplay;
	@FXML
	private ImageView PlayerDisplay;

	List<Integer> playerCards = new ArrayList<Integer>();
	List<Integer> programCards = new ArrayList<Integer>();
	String instructions = "Game Description: The deck is divided evenly, with each player receiving 26 cards. Each player turns up a card at the same time and the player with the higher card takes both cards and puts them at the bottom of the stack. If the cards are the same rank it is War. Each player turns one card face down and one card face up. The player with the higher card takes both piles (and if the cards are equal this is repeated. The game ends when one player has won all the cards. Hit 'Next' to play a card";
	Image image;

	@FXML
	void gameDescription(ActionEvent event) {
		Alert s = new Alert(Alert.AlertType.INFORMATION, instructions);
		s.show();
	}

	// gets the next card from both player and program, compares them, and updates
	// hand. Prints data for error checking
	@FXML
	void NextButton(ActionEvent event) {
		int player;
		int program;
		List<Integer> pot = new ArrayList<Integer>();

		// make sure there are cards in both decks
		if (programCards.isEmpty() || playerCards.isEmpty()) {
			Alert e = new Alert(Alert.AlertType.INFORMATION, "Game is over, hit Start button to begin a new game");
			e.show();
			return;
		}

		player = playerCards.get(0);
		program = programCards.get(0);

		System.out.println("Beginning count, player:" + playerCards.size() + " program:" + programCards.size());
		showPlayerCard(player);
		showProgramCard(program);

		if (player > program) {
			playerCards.add(player);
			playerCards.add(program);
			playerCards.remove(0);
			programCards.remove(0);
			System.out.println("Player won this round");
		} else if (program > player) {
			programCards.add(player);
			programCards.add(program);
			playerCards.remove(0);
			programCards.remove(0);
			System.out.println("Program won this round");
		} else {
			Alert w = new Alert(Alert.AlertType.INFORMATION, "War!");
			w.show();
			while (program == player && programCards.size() > 2 && playerCards.size() > 2) {
				pot.add(player);
				pot.add(program);
				pot.add(playerCards.get(1));
				pot.add(programCards.get(1));
				pot.add(playerCards.get(2));
				pot.add(programCards.get(2));
				System.out.println("Number of cards in pot is at: " + pot.size());

				program = programCards.get(2);
				player = playerCards.get(2);
				showPlayerCard(player);
				showProgramCard(program);

				playerCards.remove(0);
				programCards.remove(0);
				playerCards.remove(1);
				programCards.remove(1);
				playerCards.remove(2);
				programCards.remove(2);
			}
			if (player > program) {
				playerCards.addAll(pot);
				System.out.println("Player won the pot");
			} else {
				programCards.addAll(pot);
				System.out.println("Program won the pot");
			}
		}

		PlayerCount.setText("Number of cards: " + playerCards.size());
		ProgramCount.setText("Number of cards: " + programCards.size());

		if (playerCards.isEmpty()) {
			Alert w = new Alert(Alert.AlertType.INFORMATION, "Game over, you lost. Better luck next time!");
			w.show();
		}
		if (programCards.isEmpty()) {
			Alert w = new Alert(Alert.AlertType.INFORMATION, "Game over, you lost. Better luck next time!");
			w.show();
		}
	}

	// updates PlayerDisplay image and prints for error checking
	void showPlayerCard(int playerCard) {
		String card;
		/*
		 * Image image = null;
		 * 
		 * switch(playerCard){ case 2: image = new Image ("/assets/cards/2C.png");
		 * break; case 3: image = new Image ("/assets/cards/3H.png"); break; case 4:
		 * image = new Image ("/assets/cards/4D.png"); break; case 5: image = new Image
		 * ("/assets/cards/5H.png"); break; case 6: image = new Image
		 * ("/assets/cards/6S.png"); break; case 7: image = new Image
		 * ("/assets/cards/7C.png"); break; case 8: image = new Image
		 * ("/assets/cards/8D.png"); break; case 9: image = new Image
		 * ("/assets/cards/9H.png"); break; case 10: image = new Image
		 * ("/assets/cards/10S.png"); break; case 11: image = new Image
		 * ("/assets/cards/JC.png"); break; case 12: image = new Image
		 * ("/assets//cards/QD.png"); break; case 13: image = new Image
		 * ("/assets/cards/KH.png"); break; case 14: image = new Image
		 * ("/assets/cards/AS.png"); break; } PlayerDisplay.setImage(image);
		 * PlayerDisplay.setFitHeight(100); PlayerDisplay.setFitWidth(100);
		 * PlayerDisplay.setVisible(true);
		 */
		// below is printing for error checking
		if (playerCard == 11) {
			card = "J";
		} else if (playerCard == 12) {
			card = "Q";
		} else if (playerCard == 13) {
			card = "K";
		} else if (playerCard == 14) {
			card = "A";
		} else {
			card = String.valueOf(playerCard);
		}
		System.out.println("Player card is: " + card);
	}

	void showProgramCard(int programCard) {
		String card;
		/*
		 * Image image = null;
		 * 
		 * switch(playerCard){ case 2: image = new Image ("/assets/cards/2C.png");
		 * break; case 3: image = new Image ("/assets/cards/3H.png"); break; case 4:
		 * image = new Image ("/assets/cards/4D.png"); break; case 5: image = new Image
		 * ("/assets/cards/5H.png"); break; case 6: image = new Image
		 * ("/assets/cards/6S.png"); break; case 7: image = new Image
		 * ("/assets/cards/7C.png"); break; case 8: image = new Image
		 * ("/assets/cards/8D.png"); break; case 9: image = new Image
		 * ("/assets/cards/9H.png"); break; case 10: image = new Image
		 * ("/assets/cards/10S.png"); break; case 11: image = new Image
		 * ("/assets/cards/JC.png"); break; case 12: image = new Image
		 * ("/assets//cards/QD.png"); break; case 13: image = new Image
		 * ("/assets/cards/KH.png"); break; case 14: image = new Image
		 * ("/assets/cards/AS.png"); break; } ProgramDisplay.setImage(image);
		 * ProgramDisplay.setFitHeight(100); ProgramDisplay.setFitWidth(100);
		 * ProgramDisplay.setVisible(true);
		 */
		// below is printing for error checking
		if (programCard == 11) {
			card = "J";
		} else if (programCard == 12) {
			card = "Q";
		} else if (programCard == 13) {
			card = "K";
		} else if (programCard == 14) {
			card = "A";
		} else {
			card = String.valueOf(programCard);
		}
		System.out.println("Progam card is: " + card);
	}

	// return to main menu
	@FXML
	void MenuButton(ActionEvent event) throws IOException {
		AnchorPane mainMenu = (AnchorPane) FXMLLoader.load(getClass().getResource("project_view_menu.fxml"));
		Scene mainMenuScene = new Scene(mainMenu, 600, 400);

		// gets the stage info
		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
		window.setScene(mainMenuScene);
		window.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int i, j;
		List<Integer> deck = new ArrayList<Integer>();
		String path = new File("").getAbsolutePath();

		InputStream stream;
		try {
			stream = new FileInputStream(path + "\\src\\assets\\non-play_cards\\blue_back.png");
			Image image = new Image(stream);

			PlayerDisplay.setImage(image);
			stream = new FileInputStream(path + "\\src\\assets\\non-play_cards\\blue_back.png");
			Image image2 = new Image(stream);
			ProgramDisplay.setImage(image2);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * //Beginning card display image = new
		 * Image(path+"\\resources\\assets\\non-play_cards\\red_back.png");
		 * ProgramDisplay.setImage(image); ProgramDisplay.setFitHeight(100);
		 * ProgramDisplay.setFitWidth(100); ProgramDisplay.setVisible(true);
		 * 
		 * PlayerDisplay.setImage(image); PlayerDisplay.setFitHeight(100);
		 * PlayerDisplay.setFitWidth(100); PlayerDisplay.setVisible(true);
		 */
		for (i = 2; i < 15; i++) {
			for (j = 0; j < 4; j++) {
				deck.add(i);
			}
		}
		Collections.shuffle(deck);

		for (i = 0; i < 26; i++) {
			playerCards.add(deck.get(i));
		}
		for (i = 26; i < 52; i++) {
			programCards.add(deck.get(i));
		}
		PlayerCount.setText("Number of cards: " + playerCards.size());
		ProgramCount.setText("Number of cards: " + programCards.size());
	}

}
