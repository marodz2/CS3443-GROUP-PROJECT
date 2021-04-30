package main.controller;

import java.util.ArrayList;
import java.util.List;

import main.constant.GAME_MODE;
import main.model.Card;
import main.model.CardUI;
import main.model.Deck52;

//Alex Rodriguez (crm392) CS 3443 Group Project


// is user
public class gameLogicHearts {

	//Sets a max score limit for hearts
    private int max_score = 100;

    //Creates card list for play
    private List<CardUI> play_list;

    //creates player user
    private User userA;
    
    //creates AI players
    private Bot botB, botC, botD;

    //
    private Boolean is_heart_broken;
    
    //first heart setup
    private Boolean is_first_round;
    
    //Guide for direction where cards get passed to, plus AI player location
    // 0 = left, 1 = right, 2 = across, 3 = nothing
    private int pass_card_to;
    
    //sets next player to play a card
    private String who_next;

    //sets draw mode between 1 draw or 3 (3 is default)
    private GAME_MODE pick_card_mode;

    //sets score and total
    private int scoreA, totalA, scoreB, totalB, scoreC, totalC, scoreD, totalD;

    //Sets Game for Player to wait for next move
    public Boolean waiting_user() {
        if (who_next.equals(userA.name())) {
            return true;
        } else {
            return false;
        }

    }

    //sets draw mode between 1 draw or 3 (3 is default)
    public GAME_MODE pick_card_mode() {
        return pick_card_mode;
    }

    //calculates scores
    public List<Integer> get_all_score() {
        List<Integer> list_result = new ArrayList<Integer>();
        list_result.add(scoreA);
        list_result.add(totalA);
        list_result.add(scoreB);
        list_result.add(totalB);
        list_result.add(scoreC);
        list_result.add(totalC);
        list_result.add(scoreD);
        list_result.add(totalD);

        return list_result;
    }

    public List<CardUI> get_play_list() {
        return play_list;
    }

    public List<CardUI> get_user_list() {
        if (pick_card_mode() == GAME_MODE.PASS_3_CARD) {
            userA.set_all_pickable();
        } else {
            userA.update_rule(is_first_round, is_heart_broken);
        }

        return userA.get_my_list();
    }

    public String who_next() {
        return this.who_next;
    }

    public String pass_card_to() {
        if (pass_card_to == 0) {
            return "Left";
        } else if (pass_card_to == 1) {
            return "Right";
        } else if (pass_card_to == 2) {
            return "Across";
        } else {
            return "";
        }
    }

    public Boolean complete_round() {

        if (play_list.size() > 4) {
        	//console debug thingy for round completion
            System.out.println(this.getClass().getName() + " complete_round play_list > 4");
        }

        if (play_list.size() == 4) {
            // is first round
            if (is_first_round) {
                is_first_round = false;
            }
            return true;
        } else {
            return false;
        }
    }

    //Method for point calculation
    public void calc_point() {

        // calculate ... point ...
        int point = 0;
        for (CardUI cardUI : play_list) {
            if (cardUI.isHearts()) {
                point += 1;
            }

            if (cardUI.isQueenOfSpades()) {
                point += 13;
            }
        }


        // Algorithm to determine who gets the point
        CardUI max_card = play_list.get(0);
        for (CardUI cardUI : play_list) {
            if (max_card.isSmaller(cardUI)) {
                max_card = cardUI;
            }
        }

        // checks for broken heart
        if (is_heart_broken) {

        } else {
            for (CardUI cardUI : play_list) {
                if (cardUI.isHearts()) {
                    is_heart_broken = true;
                }
            }
        }

        // sets point value
        if (max_card.getOwner().equals(botB.name())) {
            scoreB += point;
        } else if (max_card.getOwner().equals(botC.name())) {
            scoreC += point;
        } else if (max_card.getOwner().equals(botD.name())) {
            scoreD += point;
        } else {
            scoreA += point;
        }

        // set who's next
        who_next = max_card.getOwner();
        System.out.println("start new round = " + who_next);

        // reset play_list
        // call new_round()
    }

    public void pineappleOnPizzaIsRad() {

        play_list = new ArrayList<CardUI>();

        userA = new User();
        botB = new Bot();
        botC = new Bot();
        botD = new Bot();

        userA.name("A");
        botB.name("B");
        botC.name("C");
        botD.name("D");

        totalA = 0;
        totalB = 0;
        totalC = 0;
        totalD = 0;

        // pass to left
        pass_card_to = 0;
    }

    public void new_game() {
        is_heart_broken = false;
        is_first_round = true;

        //
        if (pass_card_to != 3) {
            pick_card_mode = GAME_MODE.PASS_3_CARD;
        } else {
            pick_card_mode = GAME_MODE.PASS_1_CARD;
        }

        scoreA = 0;
        scoreB = 0;
        scoreC = 0;
        scoreD = 0;

        Deck52 deck52 = new Deck52();
        deck52.init52card();

        List<Card> tempList = new ArrayList<Card>();
        List<CardUI> tempListUI = new ArrayList<CardUI>();

        // user
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck1());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "A"));
        }
        userA.add_my_list(tempListUI);

        // botB
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck2());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "B"));
        }
        botB.add_my_list(tempListUI);

        // botC
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck3());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "C"));
        }
        botC.add_my_list(tempListUI);

        // botD
        tempList.clear();
        tempListUI.clear();
        tempList.addAll(deck52.getDeck4());
        for (Card item : tempList) {
            tempListUI.add(new CardUI(item, "D"));
        }
        botD.add_my_list(tempListUI);
    }

    public void new_round() {
        play_list.clear();
        userA.reset_play_list();
        botB.reset_play_list();
        botC.reset_play_list();
        botD.reset_play_list();
    }

    // pass_card from user and start game
    public void pass_3_card(List<Integer> pass_card_index) {

        if (pick_card_mode == GAME_MODE.PASS_3_CARD) {
            pick_card_mode = GAME_MODE.PASS_1_CARD;
        }

        List<CardUI> a, b, c, d;
        a = new ArrayList<CardUI>();
        b = new ArrayList<CardUI>();
        c = new ArrayList<CardUI>();
        d = new ArrayList<CardUI>();

        // check input form UI
        if (pass_card_index.size() != 3) {
            System.out.println("##########ERROR BossOffline start_game pass_card_index size != 3");
        }

        // add all
        for (int index = pass_card_index.size() - 1; index >= 0; index--) {
            System.out.println("VK " + pass_card_index.get(index));
            a.add(userA.get_my_list_item(pass_card_index.get(index)));
            userA.remove_my_list_item(pass_card_index.get(index));
        }
        b.addAll(botB.pass_3_card());
        c.addAll(botC.pass_3_card());
        d.addAll(botD.pass_3_card());

        // pass
        if (pass_card_to == 0) {
            // left
            userA.receive_3_card(d);
            botB.receive_3_card(a);
            botC.receive_3_card(b);
            botD.receive_3_card(c);

        } else if (pass_card_to == 1) {
            // right
            userA.receive_3_card(b);
            botB.receive_3_card(c);
            botC.receive_3_card(d);
            botD.receive_3_card(a);
        } else if (pass_card_to == 2) {
            userA.receive_3_card(c);
            botC.receive_3_card(a);
            botB.receive_3_card(d);
            botD.receive_3_card(b);
        }
    }

    // start game
    public void start_game() {
        if (botB.is_start_first()) {
            who_next = botB.name();
        } else if (botC.is_start_first()) {
            who_next = botC.name();
        } else if (botD.is_start_first()) {
            who_next = botD.name();
        } else {
            who_next = "A";
        }

        System.out.println("who_next start = " + who_next);
    }

    //Round Playing Logic
    public void play_round(int index) {

        if (who_next.equals(userA.name())) {
            who_next = botB.name();
            CardUI cardUI = userA.get_my_list_item(index);
            userA.remove_my_list_item(index);

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            botB.add_play_list(cardUI);
            botC.add_play_list(cardUI);
            botD.add_play_list(cardUI);
            //Debug Console for next player
            System.out.println("who_next = B");
            return;
        }

        if (who_next.equals(botB.name())) {
            who_next = botC.name();
            botB.update_rule(is_first_round, is_heart_broken);
            CardUI cardUI = botB.pass_1_card();

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            botB.add_play_list(cardUI);
            botC.add_play_list(cardUI);
            botD.add_play_list(cardUI);
            //Debug Console for next player
            System.out.println("who_next = C");
            return;
        }

        if (who_next.equals(botC.name())) {
            who_next = botD.name();
            botC.update_rule(is_first_round, is_heart_broken);
            CardUI cardUI = botC.pass_1_card();

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            botB.add_play_list(cardUI);
            botC.add_play_list(cardUI);
            botD.add_play_list(cardUI);
            //Debug Console for next player
            System.out.println("who_next = D");
            return;
        }

        if (who_next.equals(botD.name())) {
            who_next = userA.name();

            botD.update_rule(is_first_round, is_heart_broken);
            CardUI cardUI = botD.pass_1_card();

            play_list.add(cardUI);
            userA.add_play_list(cardUI);
            botB.add_play_list(cardUI);
            botC.add_play_list(cardUI);
            botD.add_play_list(cardUI);

            //Debug Console for next player
            System.out.println("who_next = A");
            return;
        }
    }

    public Boolean is_end_game() {
        if (userA.get_my_list().size() == 0) {
            return true;
        }

        return false;
    }

    public void end_game() {
    	//Console Debug
        System.out.println("end_game()");
        pass_card_to = pass_card_to + 1;
        //Console Debug
        System.out.println("pass_card_to " + pass_card_to);

        // check shoot the moon
        if (scoreA == 26) {
            totalB += 26;
            totalC += 26;
            totalD += 26;
        } else if (scoreB == 26) {
            totalA += 26;
            totalC += 26;
            totalD += 26;
        } else if (scoreC == 26) {
            totalA += 26;
            totalB += 26;
            totalD += 26;
        } else if (scoreD == 26) {
            totalA += 26;
            totalB += 26;
            totalC += 26;
        } else {
            totalA += scoreA;
            totalB += scoreB;
            totalC += scoreC;
            totalD += scoreD;
        }

        scoreA = 0;
        scoreB = 0;
        scoreC = 0;
        scoreD = 0;
    }

    public Boolean is_won_game() {

        if (totalA > max_score || totalB > max_score || totalC > max_score || totalD > max_score) {
            return true;
        }
        return false;
    }

    public String who_won_game() {
        String result = "Game Results:" + "\n";

        int min_score = 1000;
        if (min_score > totalA) {
            min_score = totalA;
        }
        if (min_score > totalB) {
            min_score = totalB;
        }
        if (min_score > totalC) {
            min_score = totalC;
        }
        if (min_score > totalD) {
            min_score = totalD;
        }

        
        //Shows Results Towards Player
        System.out.println("min_score = " + min_score);
        result = result + "You   : " + totalA + ((totalA == min_score) ? " ***(>O_O)>WINNER<(O_O<)*** " : "") + "\n";
        result = result + "Austin : " + totalB + ((totalB == min_score) ? " ***(>O_O)>WINNER<(O_O<)*** " : "") + "\n";
        result = result + "Anna : " + totalC + ((totalC == min_score) ? " ***(>O_O)>WINNER<(O_O<)*** " : "") + "\n";
        result = result + "Avery : " + totalD + ((totalD == min_score) ? " ***(>O_O)>WINNER<(O_O<)*** " : "") + "\n";

        return result;
    }
}
