package application;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
	private ArrayList<Integer> cards = new ArrayList<Integer>();
	private List<String> cardList;
	private File[] files;
	final private String path = new File("").getAbsolutePath();
	final private File f = new File(path + "\\src\\assets\\cards");

	// Instantiates deck object
	public Deck() {
		for (int i = 0; i < 52; i++)
			cards.add(i);
		files = f.listFiles();
	}

	// This shuffles the deck
	public void shuffle() {
		Collections.shuffle(cards);
	}

	// This prints the deck for testing
	public void printDeck() {
		for (int i = 0; i < 52; i++) {
			System.out.println(this.cards.get(i));
		}
	}

	// SETTERS & GETTERS
	public ArrayList<Integer> getCards() {
		return cards;
	}

	public void setCards(ArrayList<Integer> cards) {
		this.cards = cards;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

}