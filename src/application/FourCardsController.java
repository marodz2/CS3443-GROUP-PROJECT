package application;

import java.io.IOException;
import main.*;
import main.view.playHearts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import model.FCAppModel;

public class FourCardsController {

	@FXML
	private AnchorPane rootPane;
	@FXML
	private AnchorPane settingsPane;
	@FXML
	private Button returnhome;
	@FXML
	private Button blackjackButton;

	public void displayCredits(ActionEvent event) throws IOException {
		// Node node = (Node) event.getSource();
		// Stage stage = (Stage) node.getScene().getWindow();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("project_view_credits.fxml"));
		rootPane.getChildren().setAll(root);
		// model.FCAppModel.updateScene(stage, root);
	}

	public void displaySettings(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("project_view_settings.fxml"));
		// rootPane.getChildren().setAll(root);
		Scene scene = new Scene(root, 600, 400);
		stage.setScene(scene);

		// model.FCAppModel.updateScene(stage, root);
	}

	public void displayBlackjack(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("Blackjack.fxml"));
		Scene scene = new Scene(root, 1409, 890);
		stage.setX(550);
		stage.setY(250);
		stage.setScene(scene);

		// root.setMinSize(1409, 909);
		// rootPane.getChildren().setAll(root);

		// model.FCAppModel.updateScene(stage, root);
	}

	public void displayHearts(ActionEvent event) throws IOException {
		// Node node = (Node) event.getSource();
		// Stage stage = (Stage) node.getScene().getWindow();
		// AnchorPane root = (AnchorPane)
		// FXMLLoader.load(getClass().getResource("Hearts.fxml"));
		// Scene scene = new Scene(root, 1409, 890);
		// stage.setX(550);
		// stage.setY(250);
		// stage.setScene(scene);

		playHearts obj = new playHearts();
		String[] s = null;
		obj.main(s);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	public void displayWar(ActionEvent event) throws IOException {

		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("War.fxml"));
		Scene scene = new Scene(root, 600, 400);
		stage.setX(550);
		stage.setY(250);
		stage.setScene(scene);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

	@FXML
	public void displayMain(ActionEvent event) throws IOException {
		// Node node = (Node) event.getSource();
		Stage stage = (Stage) returnhome.getScene().getWindow();
		// returnhome.getScene().getWindow().hide();

		FourCards main1 = new FourCards();
		main1.start(stage);

		// model.FCAppModel.updateScene(stage, root, false);
	}

	@FXML
	public void displayConcentrationGame(ActionEvent event) throws IOException {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("concentration_main.fxml"));
		// rootPane.getChildren().setAll(root);
		Scene scene = new Scene(root, 1416, 624);
		stage.setX(550);
		stage.setY(250);
		stage.setScene(scene);

		// model.FCAppModel.updateScene(stage, root, true);
	}
}
