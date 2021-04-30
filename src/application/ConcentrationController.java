/* Programmed by Avery Justus (hcu285)
 * This card game concentration randomly generates a 13x4 grid of playing cards
 * and the object of the game is to match as many pairs as you can. This is a
 * two player game where player1 starts first and upon getting a match or not, it is
 * player2's turn.
 */

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//The class that handles the Concentration game mode 
public class ConcentrationController implements Initializable {
	private ImageView RC, tempRC; // Images for real cards and temp to save previous
	private ImageView PH, tempPH; // Images for placeholder cards and temp to save previous
	private char rank = '?', prevRank = '&'; // Characters used to compare a match
	private int id, tempID, P1score = 0, P2score = 0, cardsRemoved = 0; // Track ID and player scores
	private boolean firstMove = true, P1turn = true; // 2 moves per turn and sets whose turn it is

	// FXML Objects to be used
	@FXML
	private GridPane cardGrid;
	@FXML
	private Label playerCards;
	@FXML
	private Label p1Cards;
	@FXML
	private Label turnLabel;

	@FXML
	private AnchorPane rootPane;
	@FXML
	private Button returnhome;

	@FXML // This method displays the main menu scene
	void returnHome(ActionEvent event) throws IOException {

		Stage stage = (Stage) returnhome.getScene().getWindow();
		FourCards main1 = new FourCards();
		main1.start(stage);

	}

	@Override // Method runs when concentration game scene is opened
	public void initialize(URL location, ResourceBundle resources) {
		// Set scores to default values
		p1Cards.setText("P1 cards: " + P1score);
		playerCards.setText("P2 cards: " + P2score);
		turnLabel.setText("Turn: P1"); // P1's turn first by default

		// Instantiates a new deck and shuffles it
		Deck deck = new Deck();
		deck.shuffle();

		// For generating grid of cards
		ImageView realCard, placeholder;
		int z = 0;
		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 4; j++) {
				// Fetches cards from fileList and creates new card images in the grid
				realCard = new ImageView(
						new Image("/assets/cards/" + deck.getFiles()[deck.getCards().get(z)].getName()));
				realCard.setFitHeight(100);
				realCard.setFitWidth(100);
				realCard.setVisible(true);
				realCard.setId("card" + Integer.toString(z));
				cardGrid.add(realCard, i, j);

				// Fetches "back" (placeholder) cards and overlays them over real cards
				placeholder = new ImageView(new Image("/assets/non-play_cards/red_back.png"));
				placeholder.setFitHeight(100);
				placeholder.setFitWidth(100);
				placeholder.setId(Integer.toString(z));
				cardGrid.add(placeholder, i, j);

				// Handles mouse click on placeholder cards
				placeholder.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						// Gets ID number of placeholder along with rank and reference to real card
						ImageView PH = (ImageView) event.getSource();
						id = Integer.parseInt(PH.getId());
						rank = deck.getFiles()[deck.getCards().get(id)].getName().charAt(0);
						String rc = "#card" + id;
						RC = (ImageView) cardGrid.lookup(rc);

						PH.setVisible(false); // Shows real card (under placeholder)

						new Thread(() -> { // New thread to wait half a second before cards disappear on 2nd turn
							try {
								Thread.sleep(500); // Half a second wait
							} catch (InterruptedException ex) {
							}

							// Conditional only runs on second move
							if (firstMove == false) {

								// Checks for a match in rank
								if (rank == prevRank) {
									// Updates a player's score accordingly
									if (P1turn)
										P1score++;
									else
										P2score++;

									// If match, remove top 2 placeholder card
									PH.setVisible(false);
									tempPH.setVisible(false);

									// If match, remove bottom 2 real card
									RC.setVisible(false);
									tempRC.setVisible(false);

									cardsRemoved++;

								} else { // If no match, set 2 placeholder cards back to visible
									PH.setVisible(true);
									tempPH.setVisible(true);
								}

								firstMove = true; // Set back to first move

								// Swap player turns
								if (P1turn) {
									P1turn = false;
								} else {
									P1turn = true;
								}
							} else {
								firstMove = false; // Second move (click another card for match)
							}

							// This saves all previous values temporarily to be checked on next run
							prevRank = rank;
							tempPH = PH;
							tempRC = RC;
							tempID = id;

							// Update score after a match is processed
							Platform.runLater(() -> p1Cards.setText("P1 cards: " + P1score));
							Platform.runLater(() -> playerCards.setText("P2 cards: " + P2score));
							if (P1turn)
								Platform.runLater(() -> turnLabel.setText("Turn: P1"));
							else
								Platform.runLater(() -> turnLabel.setText("Turn: P2"));

							if (cardsRemoved >= 26) {
								if (P1score > P2score)
									Platform.runLater(() -> turnLabel.setText("P1 WINS"));
								else
									Platform.runLater(() -> turnLabel.setText("P2 WINS"));
							}
							try {
								Thread.sleep(2000);
							} catch (InterruptedException ex) {
								ex.printStackTrace();
							}
						}).start();
					}
				});
				z++; // For incrementing through the card array
			}
		}
	}

}
