package hangman;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class BaseFrame extends JFrame{
    protected JFrame frame;
    protected JTextField textField;
    protected JTextField wrongLetters;
    protected JButton[] characterButtons = new JButton[26];
    JPanel panel;
    Font myFont = new Font("Arial", Font.BOLD,20);
    char[] alphabet = new char[26];

    public BaseFrame(){
        initializeFrame();
    }

    public void initializeFrame(){
        char letter = 'A';
        for (int i = 0; i < 26; i++) {
            alphabet[i] = letter;
            letter++;
        }

        frame = new JFrame("HangMan (1 Player)");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,550);
        frame.setLayout(null);

        textField = new JTextField();
        textField.setBounds(320,50,200,50);
        textField.setFont(myFont);
        textField.setEditable(false);

        wrongLetters = new JTextField();
        wrongLetters.setBounds(320,110,200,50);
        wrongLetters.setFont(myFont);
        wrongLetters.setEditable(false);

        for(int i=0;i<characterButtons.length;i++){
            characterButtons[i]= new JButton(String.valueOf(alphabet[i]));
            characterButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(50,300,470,130);
        panel.setLayout(new GridLayout(3,10,5,10));
        for(int i=0;i<characterButtons.length;i++){
            panel.add(characterButtons[i]);
        }

        frame.add(panel);
        frame.add(textField);
        frame.add(wrongLetters);
        frame.setVisible(true);
    }

    public int showWinOptions() throws FileNotFoundException {
        Object[] options = {"Replay",
                "Main Menu"};
        int result;
        return result = JOptionPane.showOptionDialog(null,
                "You Win!",
                "Winner",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
    }
    public int showLoseOptions() throws FileNotFoundException{
        int result;
        Object[] options = {"Replay",
                "Main Menu"};
        return result =  JOptionPane.showOptionDialog(null,
                "You Lose!",
                "Loser",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);
    }
}
