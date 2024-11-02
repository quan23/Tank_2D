package View;

import Controller.GameControl;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameMenu extends JFrame {

    private JButton startGame;
    private JButton mapEditor;
    private Clip clip;

    public GameMenu() {
        this.setTitle("Tank 2D Game Menu");
        this.setSize(912, 672);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        playBackgroundMusic("bestsoundever.wav"); 

        PanelBackGround mainPanel = new PanelBackGround("background.png");
        mainPanel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Tank 2D", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1, 10, 10));
        buttonPanel.setOpaque(false);

        startGame = new JButton("Start Game");
        startGame.setFont(new Font("Arial", Font.BOLD, 24));
        startGame.setForeground(Color.BLACK);
        startGame.setFocusPainted(false);
        startGame.setOpaque(false);
        startGame.setContentAreaFilled(false);  
        startGame.setBorderPainted(false);
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        mapEditor = new JButton("Map Editor");
        mapEditor.setFont(new Font("Arial", Font.BOLD, 24));
        mapEditor.setForeground(Color.BLACK);
        mapEditor.setFocusPainted(false);
        mapEditor.setOpaque(false);
                mapEditor.setBorderPainted(false);

        mapEditor.setContentAreaFilled(false); 

        mapEditor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMapEditor();
            }
        });

        buttonPanel.add(startGame);
        buttonPanel.add(mapEditor);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        this.add(mainPanel);
    }

    private void startGame() {
        this.dispose();
        stopBackgroundMusic();
        GameControl gameControl = new GameControl();
        gameControl.startGame();
        
    }

    private void openMapEditor() {
                stopBackgroundMusic();

        this.dispose();
        MapEditor mapEditor = new MapEditor();
        mapEditor.setVisible(true);
    }
    
    
    private void playBackgroundMusic(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop(); 
            clip.close(); 
            clip = null;
        }
    }
}
