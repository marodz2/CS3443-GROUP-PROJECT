package main.model;

import main.constant.SUITS;
import main.constant.CARD_VALUE;

//Alex Rodriguez (crm392) CS 3443 Group Project


public class Card {
    private SUITS suit;
    private CARD_VALUE value;

    public Card() {
        super();
    }

    public Card(SUITS suit, CARD_VALUE value) {
        super();
        this.suit = suit;
        this.value = value;
    }

    public int getVirtualValue() {
        return this.suit.ordinal() * 13 + this.value.ordinal();
    }
    

    public Boolean isHearts() {
        if (this.suit == SUITS.HEARTS) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isTwoOfClubs() {
        if (this.suit == SUITS.CLUBS && this.value == CARD_VALUE.TWO) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isQueenOfSpades() {
        if (this.suit == SUITS.SPADES && this.value == CARD_VALUE.QUEEN) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean isSmaller(Card other) {
        if (!this.suit.equals(other.suit)) {
            return false;
        }

        return (this.value.ordinal() < other.getValue().ordinal());
    }

    public Boolean isGreater(Card other) {
        if (!this.suit.equals(other.suit)) {
            return false;
        }

        return (this.value.ordinal() > other.getValue().ordinal());
    }

    public CARD_VALUE getValue() {
        return value;
    }

    public void setValue(CARD_VALUE value) {
        this.value = value;
    }

    public SUITS getSuit() {
        return suit;
    }

    public void setSuit(SUITS suit) {
        this.suit = suit;
    }

    @Override
    public String toString() {
        return "Card [value=" + value + ", suit=" + suit + "]";
    }

}
