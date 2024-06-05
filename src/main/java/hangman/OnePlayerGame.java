package hangman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class OnePlayerGame extends GameLogic{
    private ArrayList<String> words = new ArrayList<>() ;
    private String word;

    public OnePlayerGame() throws FileNotFoundException {
        super(randomElement());
    }
    public void initializeGame() throws FileNotFoundException {
        openFile();
        randomElement();
        super.hiddenWordCharacters();
    }
    public void openFile() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/main/resources/hangman/words.txt"));
        while(scanner.hasNext()){
            words.add(scanner.nextLine());
        }
    }
    public static String randomElement() throws FileNotFoundException {
        ArrayList<String> words = new ArrayList<>();
        Scanner scanner = new Scanner(new File("src/main/resources/hangman/words.txt"));
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        Random randomIndex = new Random();
        int index = randomIndex.nextInt(words.size());
        return words.get(index);
    }
}
