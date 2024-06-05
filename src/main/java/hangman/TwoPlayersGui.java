package hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class TwoPlayersGui extends BaseFrame implements ActionListener {
    DrawPanel drawPanel;
    TwoPlayersGame game;

    public TwoPlayersGui() throws FileNotFoundException {
        initialize();
        startGame();
    }
    public void initialize() {

        for (int i = 0; i < characterButtons.length; i++) {
            characterButtons[i].addActionListener(this);
        }


        drawPanel = new DrawPanel();
        drawPanel.setBounds(50,50,250,230);

        frame.add(drawPanel);
    }
    public void startGame(){
        game = new TwoPlayersGame();
        game.initializeGame();
        textField.setText(game.getHiddenWord());
    }

    public void winDialog() throws FileNotFoundException {
        int result = super.showWinOptions();
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else if (result == JOptionPane.NO_OPTION) {
            MainFrame main = new MainFrame();
            frame.dispose();
        }
    }
    public void restartGame() throws FileNotFoundException {
        startGame();
        drawPanel.clear();
        wrongLetters.setText("");

    }
    public void loseDialog() throws FileNotFoundException {
       int result = super.showLoseOptions();
        if (result == JOptionPane.YES_OPTION) {
            restartGame();
        } else if (result == JOptionPane.NO_OPTION) {
            MainFrame main = new MainFrame();
            frame.dispose();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 26; i++) {
            if(e.getSource()==characterButtons[i]){
                if(game.searchForLetter(characterButtons[i].getText().charAt(0))){
                    textField.setText(game.getHiddenWord());
                }else{
                    if(!game.alreadyGivenChars(characterButtons[i].getText().charAt(0)))
                        wrongLetters.setText(wrongLetters.getText().concat(characterButtons[i].getText().concat(",")));
                }

                if(game.winGame()){
                    try {
                        winDialog();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                if(!game.checkForTries()){
                    try {
                        loseDialog();
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                break;
            }
        }
    }
}
