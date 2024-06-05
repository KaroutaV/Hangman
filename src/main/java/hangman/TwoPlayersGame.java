package hangman;

import javax.swing.*;

public class TwoPlayersGame extends GameLogic{
    public TwoPlayersGame() {
        super(insertWord());
        hiddenWordCharacters();
    }

    public static String insertWord(){
        return JOptionPane.showInputDialog(null,
                "Please enter a word:", "Input Dialog", JOptionPane.PLAIN_MESSAGE);
    }
}
