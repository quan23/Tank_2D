package View;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    private static Clip clip;

    public static void playSound(String filePath) {
        try {
            File soundFile = new File(filePath);
            if (!soundFile.exists()) {
                System.out.println("File not found: " + filePath);
                return;
            }
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);

            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();

            //System.out.println("Playing sound from: " + soundFile.getAbsolutePath());
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println("Error playing sound: " + e.getMessage());
        }
    }

    public static void stopSound(String filePath) {
    if (clip != null) {
        if (clip.isRunning()) {
            clip.stop(); // Stop the sound if it is currently playing
        }
        clip.setFramePosition(0); // Optionally reset the clip to the beginning
    }
}
}
