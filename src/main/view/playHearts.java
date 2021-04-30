package main.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SpringLayout;
import javax.swing.UIManager;

import main.constant.GAME_MODE;
import main.controller.gameLogicHearts;
import main.model.CardUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.awt.Component;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

//Alex Rodriguez (crm392) CS 3443 Group Project

public class playHearts {

    //Sets Card Width and height
    private int card_width = 75;
    private int card_height = 108;

    //Parameters for UI
    private List<JButton> listButton = new ArrayList<JButton>();
    private SpringLayout springLayout;
    private JFrame frameHeartsGame;
    private JButton player;
    private JLabel labelNotification;
    
    //Parameters for game reset
    private int picked_card_count;
    private Boolean waiting_user;
    private String notification;


    //Player 1
    private JLabel labelName1;
    private JButton buttonCardA;
    private JLabel labelScoreA;
    
    //Player 2
    private JLabel labelNameB;
    private JButton buttonCardB;
    private JLabel labelScoreB;
    
    //Player 3
    private JLabel labelNameC;
    private JButton buttonCardC;
    private JLabel labelScoreC;
    
    //Player 4
    private JLabel labelNameD;
    private JButton buttonCardD;
    private JLabel labelScoreD;



    private gameLogicHearts logic;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Throwable e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    playHearts window = new playHearts();
                    window.frameHeartsGame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public playHearts() {
        initialize();
    }

    private void initialize() {
        frameHeartsGame = new JFrame();
        frameHeartsGame.setTitle("Hearts Game ");
        frameHeartsGame.getContentPane().setFont(new Font("System", Font.BOLD, 15));
        frameHeartsGame.getContentPane().setBackground(SystemColor.desktop);
        frameHeartsGame.setBackground(SystemColor.desktop);
        frameHeartsGame.setResizable(false);
        frameHeartsGame.setBounds(50, 50, 800, 650);
        frameHeartsGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        springLayout = new SpringLayout();
        frameHeartsGame.getContentPane().setLayout(springLayout);

        notification = "Click icon below to start game";

        initA();
        initB();
        initC();
        initD();

        initNoti();
        drawNoti(null);

        logic = new gameLogicHearts();
        logic.pineappleOnPizzaIsRad();

        player.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                notification = "";
                newGame();
            }
        });

    }

    //Player Player
    private void initA() {

        player = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, player, 400, SpringLayout.NORTH,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, player, 10, SpringLayout.WEST,
                frameHeartsGame.getContentPane());
        player.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(playHearts.class.getResource("/resource/avatar_robot.png"));
            player.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");
        }

        frameHeartsGame.getContentPane().add(player);

        labelName1 = new JLabel("You");
        springLayout.putConstraint(SpringLayout.NORTH, labelName1, 5, SpringLayout.SOUTH, player);
        springLayout.putConstraint(SpringLayout.WEST, labelName1, 0, SpringLayout.WEST, player);
        springLayout.putConstraint(SpringLayout.EAST, labelName1, 0, SpringLayout.EAST, player);
        labelName1.setHorizontalAlignment(SwingConstants.CENTER);
        labelName1.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelName1.setForeground(Color.CYAN);
        labelName1.setFont(new Font("System", Font.PLAIN, 15));
        frameHeartsGame.getContentPane().add(labelName1);

        labelScoreA = new JLabel();
        labelScoreA.setText("0/0");
        labelScoreA.setHorizontalAlignment(SwingConstants.CENTER);
        labelScoreA.setBorder(new LineBorder(Color.ORANGE, 3, true));
        labelScoreA.setForeground(new Color(255, 255, 255));
        labelScoreA.setFont(new Font("System", Font.PLAIN, 17));
        labelScoreA.setBackground(Color.GRAY);
        springLayout.putConstraint(SpringLayout.WEST, labelScoreA, 0, SpringLayout.WEST, player);
        springLayout.putConstraint(SpringLayout.NORTH, labelScoreA, 5, SpringLayout.SOUTH, labelName1);
        springLayout.putConstraint(SpringLayout.EAST, labelScoreA, 0, SpringLayout.EAST, player);
        frameHeartsGame.getContentPane().add(labelScoreA);

        buttonCardA = new JButton();

        int cardA_top = 280;
        int cardA_left = 350;

        springLayout.putConstraint(SpringLayout.WEST, buttonCardA, cardA_left, SpringLayout.WEST,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.EAST, buttonCardA, cardA_left + card_width, SpringLayout.WEST,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.NORTH, buttonCardA, cardA_top, SpringLayout.NORTH,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, buttonCardA, cardA_top + card_height, SpringLayout.NORTH,
                frameHeartsGame.getContentPane());

        buttonCardA.setVisible(false);

        frameHeartsGame.getContentPane().add(buttonCardA);
    }

    
    //Computer Player Austin
    private void initB() {

        JButton btnAvatarB = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, btnAvatarB, 170, SpringLayout.NORTH,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnAvatarB, 150, SpringLayout.WEST,
                frameHeartsGame.getContentPane());
        btnAvatarB.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(playHearts.class.getResource("/resource/avatar_robot.png"));
            btnAvatarB.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");

        }

        frameHeartsGame.getContentPane().add(btnAvatarB);

        labelNameB = new JLabel("Austin");
        springLayout.putConstraint(SpringLayout.EAST, labelNameB, 0, SpringLayout.EAST, btnAvatarB);
        springLayout.putConstraint(SpringLayout.WEST, labelNameB, 0, SpringLayout.WEST, btnAvatarB);
        springLayout.putConstraint(SpringLayout.NORTH, labelNameB, 5, SpringLayout.SOUTH, btnAvatarB);
        labelNameB.setHorizontalAlignment(SwingConstants.CENTER);
        labelNameB.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNameB.setForeground(Color.CYAN);
        labelNameB.setFont(new Font("System", Font.PLAIN, 15));
        frameHeartsGame.getContentPane().add(labelNameB);

        labelScoreB = new JLabel();
        labelScoreB.setHorizontalAlignment(SwingConstants.CENTER);
        labelScoreB.setBorder(new LineBorder(Color.ORANGE, 3, true));
        labelScoreB.setForeground(new Color(255, 153, 255));
        labelScoreB.setFont(new Font("System", Font.PLAIN, 17));
        labelScoreB.setText("0/0");
        labelScoreB.setBackground(Color.GRAY);
        springLayout.putConstraint(SpringLayout.WEST, labelScoreB, 0, SpringLayout.WEST, btnAvatarB);
        springLayout.putConstraint(SpringLayout.EAST, labelScoreB, 0, SpringLayout.EAST, btnAvatarB);
        springLayout.putConstraint(SpringLayout.NORTH, labelScoreB, 5, SpringLayout.SOUTH, labelNameB);
        frameHeartsGame.getContentPane().add(labelScoreB);

        buttonCardB = new JButton();

        int cardB_top = 5;
        int cardB_left = 20;

        springLayout.putConstraint(SpringLayout.WEST, buttonCardB, cardB_left, SpringLayout.EAST, btnAvatarB);
        springLayout.putConstraint(SpringLayout.EAST, buttonCardB, cardB_left + card_width, SpringLayout.EAST, btnAvatarB);
        springLayout.putConstraint(SpringLayout.NORTH, buttonCardB, cardB_top, SpringLayout.NORTH, btnAvatarB);
        springLayout.putConstraint(SpringLayout.SOUTH, buttonCardB, cardB_top + card_height, SpringLayout.NORTH,
                btnAvatarB);

        buttonCardB.setVisible(false);

        frameHeartsGame.getContentPane().add(buttonCardB);
    }
    
    
    //Computer Player Anna
    private void initC() {

        JButton btnAvatarC = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, btnAvatarC, 10, SpringLayout.NORTH,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, btnAvatarC, 300, SpringLayout.WEST,
                frameHeartsGame.getContentPane());
        btnAvatarC.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(playHearts.class.getResource("/resource/avatar_robot.png"));
            btnAvatarC.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");

        }

        frameHeartsGame.getContentPane().add(btnAvatarC);
        labelScoreC = new JLabel();
        labelScoreC.setHorizontalAlignment(SwingConstants.CENTER);
        labelScoreC.setBorder(new LineBorder(Color.ORANGE, 3, true));
        labelScoreC.setForeground(new Color(255, 153, 255));
        labelScoreC.setFont(new Font("System", Font.PLAIN, 17));
        labelScoreC.setText("0/0");
        labelScoreC.setBackground(Color.GRAY);
        springLayout.putConstraint(SpringLayout.WEST, labelScoreC, 10, SpringLayout.EAST, btnAvatarC);
        springLayout.putConstraint(SpringLayout.SOUTH, labelScoreC, 0, SpringLayout.SOUTH, btnAvatarC);
        springLayout.putConstraint(SpringLayout.EAST, labelScoreC, 100, SpringLayout.EAST, btnAvatarC);
        frameHeartsGame.getContentPane().add(labelScoreC);

        labelNameC = new JLabel("Anna");
        springLayout.putConstraint(SpringLayout.NORTH, labelScoreC, 5, SpringLayout.SOUTH, labelNameC);
        springLayout.putConstraint(SpringLayout.NORTH, labelNameC, 5, SpringLayout.NORTH, btnAvatarC);
        springLayout.putConstraint(SpringLayout.WEST, labelNameC, 0, SpringLayout.WEST, labelScoreC);
        labelNameC.setHorizontalAlignment(SwingConstants.CENTER);
        labelNameC.setAlignmentX(Component.CENTER_ALIGNMENT);
        springLayout.putConstraint(SpringLayout.EAST, labelNameC, 0, SpringLayout.EAST, labelScoreC);
        labelNameC.setForeground(Color.CYAN);
        labelNameC.setFont(new Font("System", Font.PLAIN, 15));
        frameHeartsGame.getContentPane().add(labelNameC);

        buttonCardC = new JButton();

        int cardC_top = 10;
        int cardC_left = 50;

        springLayout.putConstraint(SpringLayout.WEST, buttonCardC, cardC_left, SpringLayout.WEST, btnAvatarC);
        springLayout.putConstraint(SpringLayout.EAST, buttonCardC, cardC_left + card_width, SpringLayout.WEST, btnAvatarC);
        springLayout.putConstraint(SpringLayout.NORTH, buttonCardC, cardC_top, SpringLayout.SOUTH, btnAvatarC);
        springLayout.putConstraint(SpringLayout.SOUTH, buttonCardC, cardC_top + card_height, SpringLayout.SOUTH,
                btnAvatarC);
        buttonCardC.setVisible(false);

        frameHeartsGame.getContentPane().add(buttonCardC);
    }

    
    //Computer Player Avery
    private void initD() {

        buttonCardD = new JButton();

        int cardD_top = 175;
        int cardD_left = 450;

        springLayout.putConstraint(SpringLayout.NORTH, buttonCardD, cardD_top, SpringLayout.NORTH,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.WEST, buttonCardD, cardD_left, SpringLayout.WEST,
                frameHeartsGame.getContentPane());

        springLayout.putConstraint(SpringLayout.EAST, buttonCardD, cardD_left + card_width, SpringLayout.WEST,
                frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, buttonCardD, cardD_top + card_height, SpringLayout.NORTH,
                frameHeartsGame.getContentPane());

        buttonCardD.setVisible(false);

        frameHeartsGame.getContentPane().add(buttonCardD);

        JButton btnAvatarD = new JButton();
        springLayout.putConstraint(SpringLayout.NORTH, btnAvatarD, -5, SpringLayout.NORTH, buttonCardD);
        springLayout.putConstraint(SpringLayout.WEST, btnAvatarD, 20, SpringLayout.EAST, buttonCardD);
        btnAvatarD.setFocusPainted(false);
        try {
            ImageIcon img = new ImageIcon(playHearts.class.getResource("/resource/avatar_robot.png"));
            btnAvatarD.setIcon(new ImageIcon(img.getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH)));
        } catch (NullPointerException e) {
            System.out.println("Image not found");

        }

        frameHeartsGame.getContentPane().add(btnAvatarD);

        labelNameD = new JLabel("Avery");
        springLayout.putConstraint(SpringLayout.NORTH, labelNameD, 5, SpringLayout.SOUTH, btnAvatarD);
        springLayout.putConstraint(SpringLayout.WEST, labelNameD, 0, SpringLayout.WEST, btnAvatarD);
        springLayout.putConstraint(SpringLayout.EAST, labelNameD, 0, SpringLayout.EAST, btnAvatarD);
        labelNameD.setHorizontalAlignment(SwingConstants.CENTER);
        labelNameD.setAlignmentX(Component.CENTER_ALIGNMENT);
        labelNameD.setForeground(Color.CYAN);
        labelNameD.setFont(new Font("System", Font.PLAIN, 15));
        frameHeartsGame.getContentPane().add(labelNameD);

        labelScoreD = new JLabel();
        labelScoreD.setHorizontalAlignment(SwingConstants.CENTER);
        labelScoreD.setBorder(new LineBorder(Color.ORANGE, 3, true));
        labelScoreD.setForeground(new Color(255, 153, 255));
        labelScoreD.setFont(new Font("System", Font.PLAIN, 17));
        labelScoreD.setText("0/0");
        labelScoreD.setBackground(Color.GRAY);
        springLayout.putConstraint(SpringLayout.NORTH, labelScoreD, 5, SpringLayout.SOUTH, labelNameD);
        springLayout.putConstraint(SpringLayout.WEST, labelScoreD, 0, SpringLayout.WEST, btnAvatarD);
        springLayout.putConstraint(SpringLayout.EAST, labelScoreD, 0, SpringLayout.EAST, btnAvatarD);
        frameHeartsGame.getContentPane().add(labelScoreD);
    }

    private void initNoti() {
        labelNotification = new JLabel("");
        springLayout.putConstraint(SpringLayout.WEST, labelNotification, 15, SpringLayout.WEST, frameHeartsGame.getContentPane());
        springLayout.putConstraint(SpringLayout.SOUTH, labelNotification, -10, SpringLayout.NORTH, player);
        labelNotification.setForeground(new Color(224, 255, 255));
        labelNotification.setFont(new Font("System", Font.PLAIN, 15));
        frameHeartsGame.getContentPane().add(labelNotification);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////

    private void draw() {

        drawNoti(null);

        List<Integer> list_score = logic.get_all_score();

        labelScoreA.setText(list_score.get(0) + "/" + list_score.get(1));
        labelScoreB.setText(list_score.get(2) + "/" + list_score.get(3));
        labelScoreC.setText(list_score.get(4) + "/" + list_score.get(5));
        labelScoreD.setText(list_score.get(6) + "/" + list_score.get(7));

        buttonCardA.setVisible(false);
        buttonCardB.setVisible(false);
        buttonCardC.setVisible(false);
        buttonCardD.setVisible(false);

        List<CardUI> play_list = logic.get_play_list();
        // System.out.println("play_list.size " + play_list.size());

        try {
            for (int i = 0; i < play_list.size(); i++) {
                CardUI curCardUI = play_list.get(i);

                // try {
                // curCardUI.getOwner().equals("A");
                // } catch (NullPointerException exception) {
                // System.out.println("#####ERROR#####");
                // System.out.println("play_list.get(0)" + play_list.get(i));
                // System.out.println("play_list.size" + play_list.size());
                // }

                if (curCardUI.getOwner().equals("A")) {
                    // card A
                    try {
                        ImageIcon img = new ImageIcon(playHearts.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        buttonCardA.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    buttonCardA.setVisible(true);
                } else if (curCardUI.getOwner().equals("B")) {
                    // card B
                    try {
                        ImageIcon img = new ImageIcon(playHearts.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        buttonCardB.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    buttonCardB.setVisible(true);
                } else if (curCardUI.getOwner().equals("C")) {
                    // card B
                    try {
                        ImageIcon img = new ImageIcon(playHearts.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        buttonCardC.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    buttonCardC.setVisible(true);
                } else {
                    // card D
                    try {
                        ImageIcon img = new ImageIcon(playHearts.class.getResource(
                                "/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                        buttonCardD.setIcon(new ImageIcon(img.getImage().getScaledInstance(card_width, card_height,
                                java.awt.Image.SCALE_SMOOTH)));
                    } catch (NullPointerException e) {
                        System.out.println("Image not found");
                    }
                    buttonCardD.setVisible(true);
                }
            }
        } catch (NullPointerException exception) {
            exception.printStackTrace();
        }
    }

    private void drawCard() {
        int card_left = 120;
        int card_top = 400;

        float card_index = -1;

        // remove all UI card
        for (JButton item : listButton) {
            frameHeartsGame.getContentPane().remove(item);
        }
        listButton.clear();

        List<CardUI> listCardUI = logic.get_user_list();

        for (int i = 0; i < listCardUI.size(); i++) {

            card_index++;
            if (i == 7) {
                card_top = card_top + card_height;
                card_index = 0.5f;
            }

            CardUI curCardUI = listCardUI.get(i);

            JButton btnCard = new JButton();
            btnCard.setFocusPainted(false);
            btnCard.setName(i + "_" + "font");

            springLayout.putConstraint(SpringLayout.WEST, btnCard, (int) (card_left + card_index * card_width),
                    SpringLayout.WEST, frameHeartsGame.getContentPane());
            springLayout.putConstraint(SpringLayout.EAST, btnCard, (int) (card_left + (card_index + 1) * card_width),
                    SpringLayout.WEST, frameHeartsGame.getContentPane());
            springLayout.putConstraint(SpringLayout.NORTH, btnCard, card_top, SpringLayout.NORTH,
                    frameHeartsGame.getContentPane());
            springLayout.putConstraint(SpringLayout.SOUTH, btnCard, card_top + card_height, SpringLayout.NORTH,
                    frameHeartsGame.getContentPane());
            try {
                ImageIcon img = new ImageIcon(playHearts.class
                        .getResource("/resource/card/" + curCardUI.getValue() + "_" + curCardUI.getSuit() + ".png"));
                btnCard.setIcon(new ImageIcon(
                        img.getImage().getScaledInstance(card_width, card_height, java.awt.Image.SCALE_SMOOTH)));

            } catch (NullPointerException e) {
                System.out.println("Image not found");
                btnCard.setText("Not found");
            }

            if (curCardUI.getPickable() != true) {
                btnCard.setEnabled(false);
            }

            btnCard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // System.out.println(btnCard.getName());

                    // if not waiting_user -> block UI
                    if (waiting_user) {
                        String[] data = btnCard.getName().split("_");
                        int index = Integer.parseInt(data[0]);
                        String face = data[1];

                        if (logic.pick_card_mode() == GAME_MODE.PASS_3_CARD) {

                            // if is font, flip back
                            if (face.equals("font")) {
                                btnCard.setName(index + "_back");
                                picked_card_count++;
                                ImageIcon _img = new ImageIcon(playHearts.class.getResource("/resource/card_back.png"));
                                btnCard.setIcon(new ImageIcon(_img.getImage().getScaledInstance(card_width, card_height,
                                        java.awt.Image.SCALE_SMOOTH)));

                                if (picked_card_count == 3) {
                                    passThreeCard();
                                }

                            } else {
                                btnCard.setName(index + "_font");
                                picked_card_count--;

                                CardUI changCardUI = listCardUI.get(index);

                                ImageIcon _img = new ImageIcon(playHearts.class.getResource("/resource/card/"
                                        + changCardUI.getValue() + "_" + changCardUI.getSuit() + ".png"));
                                btnCard.setIcon(new ImageIcon(_img.getImage().getScaledInstance(card_width, card_height,
                                        java.awt.Image.SCALE_SMOOTH)));
                            }
                        } else {
                            // pass card ...
                            passOneCard(index);
                        }
                    }
                }
            });

            listButton.add(btnCard);
            frameHeartsGame.getContentPane().add(btnCard);
        }

        frameHeartsGame.validate();
        frameHeartsGame.repaint();
    } // end method

    private void drawNoti(String noti) {
        if (noti != null) {
            notification = noti;
        }
        labelNotification.setText(notification);
    }

    private void passThreeCard() {
        List<Integer> picked_card_index_list = new ArrayList<Integer>();

        // get data from UI name
        int size = listButton.size();
        for (int i = 0; i < size; i++) {
            if (listButton.get(i).getName().contains("_back")) {
                picked_card_index_list.add(i);
            }
        }

        // pass card
        logic.pass_3_card(picked_card_index_list);

        // create game thread
        startGame();
    }

    private void passOneCard(int index) {

        System.out.println("Pass " + index);

        waiting_user = false;
        logic.play_round(index);

        todoThread();
    }

    private void newGame() {

        logic.new_game();
        logic.new_round();

        //
        drawCard();

        // check if is pass 3 card
        if (logic.pick_card_mode() == GAME_MODE.PASS_1_CARD) {
            // start game immediately
            startGame();
        } else {
            // waiting user pick 3 card to start game
            picked_card_count = 0;

            drawNoti("Pass 3 card to " + logic.pass_card_to());
            waiting_user = true;
        }
    }

    private void startGame() {

        logic.start_game();

        drawCard();

        waiting_user = false;

        if (logic.waiting_user()) {
            waiting_user = true;
        }

        todoThread();
    }

    private void todoThread() {
        while (true) {

            if (logic.complete_round()) {
                System.out.println("Complete Round");
                logic.calc_point();

                draw();
                drawCard();

                // pause

                if (logic.is_end_game()) {
                    logic.end_game();

                    if (logic.is_end_game()) {
                        JOptionPane.showMessageDialog(new JFrame(), logic.who_won_game(), "Info",
                                JOptionPane.INFORMATION_MESSAGE);

                        logic.pineappleOnPizzaIsRad();
                        drawNoti("Click on the Icon to Restart Game!");
                        return;
                    }

                    drawNoti("Click on the Icon to Restart Game!");
                    drawCard();
                    draw();

                    return;
                } else {
                    NewRoundProcess newRoundProcess = new NewRoundProcess();
                    newRoundProcess.execute();
                }

                return;
            }

            if (waiting_user) {
                // do nothing when waiting user
                drawNoti("Your turn");
                break;
            } else {
                drawNoti("...");
                logic.play_round(-1);

                if (logic.waiting_user()) {
                    draw();
                    drawCard();
                    waiting_user = true;
                }
            }

        }
    }

    class NewRoundProcess extends SwingWorker<Void, Void> {

        @Override
        protected void done() {
            super.done();
            System.out.println("NewRoundProcess is done");
            logic.new_round();

            waiting_user = false;

            if (logic.waiting_user()) {
                draw();
                drawCard();
                waiting_user = true;
            }

            todoThread();
        }

        @Override
        protected Void doInBackground() throws Exception {
            Thread.sleep(3000);
            return null;
        }

    }

    class MyProcess extends SwingWorker<Void, Void> {

        @Override
        protected void done() {
            super.done();
            System.out.println("MyProcess is done");
        }

        @Override
        protected Void doInBackground() throws Exception {

            Thread.sleep(2000);
            return null;
        }

    }

}
//i did it! :)