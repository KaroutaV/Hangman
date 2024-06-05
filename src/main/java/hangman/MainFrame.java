package hangman;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class MainFrame extends JFrame {
    JFrame mainFrame;
    JButton onePlayerButton;
    JButton twoPlayersButton;
    BackgroundPanel backgroundPanel;
    JLabel titleLabel ;

    public MainFrame(){
        mainFrame = new JFrame("Hangman");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600,550);
        mainFrame.setLayout(new BorderLayout());

        backgroundPanel = new BackgroundPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, mainFrame.getWidth(), mainFrame.getHeight());
        mainFrame.setContentPane(backgroundPanel);

        titleLabel = new JLabel("Hangman");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 45));
        titleLabel.setBounds(190, 20, 200, 50);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        backgroundPanel.add(titleLabel);

        onePlayerButton = new JButton("1 Player");
        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onePlayer();
            }
        });
        onePlayerButton.setBounds(400, 250, 150, 50);

        twoPlayersButton = new JButton("2 Players");
        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twoPlayers();
            }
        });
        twoPlayersButton.setBounds(400, 310, 150, 50);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(onePlayerButton);
        buttonPanel.add(twoPlayersButton);

        backgroundPanel.add(onePlayerButton);
        backgroundPanel.add(twoPlayersButton);

        mainFrame.setContentPane(backgroundPanel);
        mainFrame.setVisible(true);
    }
    public void onePlayer(){
        try {
            OnePlayerGui onePlayerGame = new OnePlayerGui();
            mainFrame.dispose();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
    public void twoPlayers(){
        try {
            TwoPlayersGui twoPlayersGame = new TwoPlayersGui();
            mainFrame.dispose();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}
class BackgroundPanel extends JPanel {
    private BufferedImage backgroundImage;

    public BackgroundPanel() {
        try {
            // Load the background image
            backgroundImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/hangman/1.gif")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
