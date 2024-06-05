package hangman;

import javax.swing.*;
import java.awt.*;

public class HangmanPanel extends JPanel{

    GameLogic game;

    public HangmanPanel(){}
    public void setGame(GameLogic game){
        this.game=game;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.cyan);

        g.drawLine(50,50,50,200);
        g.drawLine(50,50,150,50);

        g.setColor(new Color(88, 41, 4));

        if (game.getTries()<=3)
            g.drawOval(120, 50, 50, 50); //head
        if (game.getTries()<=2)
            g.drawLine(150, 100, 150, 175); // body
        if(game.getTries()<=1){
            g.drawLine(150, 100, 120, 130); // Left arm
            g.drawLine(150, 100, 180, 130); // Right arm
        }
        if(game.getTries()==0){
            g.drawLine(150, 175, 120, 200); // Left leg
            g.drawLine(150, 175, 180, 200); // Right leg
        }
    }

}
