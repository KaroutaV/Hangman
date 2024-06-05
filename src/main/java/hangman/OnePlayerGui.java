package hangman;

import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;

public class OnePlayerGui extends BaseFrame implements ActionListener{
    HangmanPanel hangPanel;
    GameLogic game;

    public OnePlayerGui() throws FileNotFoundException {
        super();
        initialize();
        startGame();
    }
    public void initialize(){

        for(int i=0;i<characterButtons.length;i++){
            characterButtons[i].addActionListener(this);
        }

        hangPanel = new HangmanPanel();
        hangPanel.setBounds(50,50,250,230);

        frame.add(hangPanel);
    }

    public void startGame() throws FileNotFoundException {
        game = new OnePlayerGame();
        game.initializeGame();
        textField.setText(game.getHiddenWord());
        hangPanel.setGame(game);
    }
    public void restartGame() throws FileNotFoundException {
        startGame();
        wrongLetters.setText("");
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

                hangPanel.repaint();

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
                hangPanel.repaint();
                break;
            }
        }
    }
}

