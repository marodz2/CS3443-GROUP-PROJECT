package main.controller;
import java.util.ArrayList;
import java.util.List;
import main.model.CardUI;

//Alex Rodriguez (crm392) CS 3443 Group Project
//

public class Bot extends Player {
	
	//Card Setup for 1 Card Draw Game Setup
    public CardUI pass_1_card() {

        List<CardUI> my_list = get_my_list();
        for (int i = 0; i < my_list.size(); i++) {
            if (my_list.get(i).getPickable()) {
                CardUI temp = new CardUI(get_my_list_item(i));

                remove_my_list_item(i);

                return temp;
            }
        }

        // DEBUG SHENANIGANS FOR NULL POINTER
        // System.out.println("NULL POINTER ERROR, DREW NULL CARD " + name());
        return null;
    }

    public List<CardUI> pass_3_card() {
        List<CardUI> pass_list = new ArrayList<CardUI>();

    	//Card Setup for 3 Card Draw Game Setup
        pass_list.add(get_my_list_item(0));
        pass_list.add(get_my_list_item(1));
        pass_list.add(get_my_list_item(2));

        /*Console Output
        System.out.println("Bot " + name() + " pass 3 card");
        for (CardUI cardUI : pass_list) {
            System.out.println("  " + cardUI.toString());
        }*/

        // removes cards
        remove_my_list_item(0);
        remove_my_list_item(0);
        remove_my_list_item(0);

        return pass_list;
    }
}
