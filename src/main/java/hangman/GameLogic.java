package hangman;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

public class GameLogic {
    private static final int TOTAL_TRIES = 4;
    private int tries = TOTAL_TRIES ;
    private String word;
    private ArrayList<String> hiddenWord ;
    private ArrayList<Character> givenChars ;

    public GameLogic(String word){
        hiddenWord = new ArrayList<>();
        givenChars = new ArrayList<>();
        this.word = word;
    }
    public void initializeGame() throws FileNotFoundException {}

    public String getHiddenWord() {
        String hidden = "";
        for(String ch:hiddenWord){
            hidden = hidden.concat(ch);
        }
        return hidden;
    }
    public int getTries(){
        return tries;
    }

    public void hiddenWordCharacters(){
        for(int i=0; i<word.length(); i++) {
            hiddenWord.add("*");
        }
    }
    public boolean alreadyGivenChars(char letter){
        int count = 0;
        for(char ch:givenChars){
            if(ch == letter)
                count ++;
        }
        if(count>=2){
            Iterator<Character> iterator = givenChars.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().equals(letter)) {
                    iterator.remove();
                    break;
                }
            }
            return true;
        }
        return false;
    }
    public boolean searchForLetter(char letter){
        boolean found = true;
        givenChars.add(letter);
        word = word.toUpperCase();
        int count = 0 ;
        for(int i=0; i<word.length(); i++){
            if(letter == word.charAt(i)){
                hiddenWord.set(i,String.valueOf(letter));
                count++;
            }
        }
        if(count==0){
            found = false;
            tries --;
        }
        return found ;
    }
    public boolean checkForTries(){
        return tries!=0 ;
    }
    public boolean winGame(){
        if (hiddenWord.contains("*"))
            return false;
        String hidden = hiddenWord.toString();
        hidden = hidden.replace("[","").replace("]","").replace(",","").replace(" ","");
        return hidden.equals(word);
    }
}

